package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class QuestionGenerator {
    private static String dest = "questions.json";

    private static final int TEXT = 1;
    private static final int SINGLE = 2;
    private static final int MULTI = 3;


    public static void main(String[] args) {
        generateQuestions(dest);
    }
    public static void generateQuestions(String path) {
        AbstractQuestion exampleText = genQ(TEXT, "question", new String[]{"answer"}, new String[]{});
        AbstractQuestion exampleSingle = genQ(SINGLE, "question", new String[]{"answer"}, new String[] {"wring", "wrong", "wrang"});
        AbstractQuestion exampleMulti = genQ(MULTI, "question", new String[]{"answer", "answor", "answar"}, new String[] {"wring", "wrong", "wrang"});

        ArrayList<AbstractQuestion> a = new ArrayList<>();
        //data
        //a.add(exampleText);
        //a.add(exampleSingle);
        //a.add(exampleMulti);
        a.add(genQ(TEXT, "Назовите континент на котором находиться большего всего стран", new String[]{"Африка"}, new String[]{}));
        a.add(genQ(TEXT, "Всего 2 столицы в мире находятся ниже уровня моря. Амстердам одна из них, назовите вторую", new String[]{"Баку"}, new String[]{}));
        a.add(genQ(TEXT, "Назовите столицу Бразилии", new String[]{"Бразилиа"}, new String[]{}));
        a.add(genQ(TEXT, "Какая страна имеет самую самую длинную сумарную береговую линию?", new String[]{"Канада"}, new String[]{}));
        a.add(genQ(TEXT, "Назовите страну, в которой находится самый южный город в мире", new String[]{"Чили"}, new String[]{}));
        a.add(genQ(TEXT, "Какая страна в мире имеет больше всего полицейских на душу населения?", new String[]{"Ватикан"}, new String[]{}));


        a.add(genQ(SINGLE, "Какая из этих стран имеет наименьшее население?", new String[]{"Ватикан"}, new String[] {"Тувалу", "Сан Марино", "Лихтенштейн", "Монако"}));
        a.add(genQ(SINGLE, "Какой из этих островов разделен между наибольшим колличеством стран?", new String[]{"Борнео/Калимантан"}, new String[] {"Новая Гвинея", "Кипр", "Ирландия"}));
        a.add(genQ(SINGLE, "Какой из этих островов имеет наибольшую территорию?", new String[]{"Новая Гвинея"}, new String[] {"Мадагаскар", "Суматра", "Великобритания", "Хонсю"}));
        a.add(genQ(SINGLE, "Какая из этих гор самая высокая?", new String[]{"Джомолунгма"}, new String[] {"Аннапурна", "Кидиманджаро", "Монблан"}));
        a.add(genQ(SINGLE, "Какая из этих рек является самой полноводной?", new String[]{"Амазонка"}, new String[] {"Дунай", "Нил", "Ганг", "Конго"}));
        a.add(genQ(SINGLE, "Какая из этих рек протекает через наибольшее колличество стран?", new String[]{"Дунай"}, new String[] {"Нил", "Амазонка", "Ганг", "Волга", "Конго"}));
        a.add(genQ(SINGLE, "Какой из этих городов столица Канады?", new String[]{"Оттава"}, new String[] {"Торонто", "Монреаль", "Калгари", "Атланта"}));
        a.add(genQ(SINGLE, "Какая их этих стран НЕ имеет города, входящего в десятку самых населенных городов мира?", new String[]{"США"}, new String[] {"Япония", "Индия", "Бразилия", "Мексика"}));

        a.add(genQ(MULTI, "Какие из перечисленых стран больше не являются суверенными государствами?", new String[]{"Бенгалия", "Англия", "Родезия"}, new String[] {"Македония", "Мальта", "Остров Святой Елены"}));
        a.add(genQ(MULTI, "Какие из перечисленых стран являются монархиями?", new String[]{"Бахрейн", "Канада", "Япония"}, new String[] {"Греция", "Румыния", "Непал"}));
        a.add(genQ(MULTI, "Какие из перечисленых стран находятся в Азии?", new String[]{"Бахрейн", "Ливан", "Лаос"}, new String[] {"Парагвай", "Боливия", "Эквадор", "Перу"}));
        a.add(genQ(MULTI, "Какие из перечисленых стран находятся в Африке?", new String[]{"Ливия", "Ботсвана"}, new String[] {"Боливия", "Парагвай", "Сирия"}));
        a.add(genQ(MULTI, "Какие из перечисленых стран Европы НЕ имеют доступа к морю?", new String[]{"Армения", "Сербия", "Австрия"}, new String[] {"Польша", "Словения"}));
        a.add(genQ(MULTI, "Какие из перечисленых стран граничат с 3 океанами?", new String[]{"Канада", "Россия"}, new String[] {"США", "Япония", "ЮАР"}));
        //data


        File file = new File(path);
        AbstractQuestion[] questionsArray = new AbstractQuestion[a.size()];
        questionsArray = a.toArray(questionsArray);

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            jsonMapper.writeValue(file, questionsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            questionsArray = jsonMapper.readValue(file, AbstractQuestion[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static AbstractQuestion genQ(int type, String question, String[] correctAnswers, String[] wrongAnswers) {
        AbstractQuestion res;
        switch (type) {
            case TEXT:
                return new TextQuestion(question, correctAnswers[0], new TreeSet<>());

            case SINGLE:
                ArrayList<String> answers = new ArrayList<>();
                if (correctAnswers != null)
                    answers.addAll(Arrays.asList(correctAnswers));
                if (wrongAnswers != null)
                    answers.addAll(Arrays.asList(wrongAnswers));
                Collections.shuffle(answers);

                int iAnswer = -1;
                if (correctAnswers != null)
                    iAnswer = answers.indexOf(correctAnswers[0]);

                String[] answerArray = new String[answers.size()];
                answerArray = answers.toArray(answerArray);

                return new SingleChoiceQuestion(question, answerArray, iAnswer, new TreeSet<>());

            case MULTI:
                ArrayList<String> answersM = new ArrayList<>();
                if (correctAnswers != null)
                    answersM.addAll(Arrays.asList(correctAnswers));
                if (wrongAnswers != null)
                    answersM.addAll(Arrays.asList(wrongAnswers));
                Collections.shuffle(answersM);

                TreeSet<Integer> answerSet = new TreeSet<>();
                if (correctAnswers != null)
                    Arrays.stream(correctAnswers).forEach((o) -> answerSet.add(answersM.indexOf(o)));

                String[] answerArrayM = new String[answersM.size()];
                answerArrayM = answersM.toArray(answerArrayM);

                return new MultiChoiceQuestion(question, answerArrayM, answerSet, new TreeSet<>());
        }
        return null;
    }
}
