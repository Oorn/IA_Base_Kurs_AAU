package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;

public class QuestionJSON {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static File file = new File("questions.json");

    @JsonProperty
    SingleChoiceQuestion[] singleQArray;
    @JsonProperty
    MultiChoiceQuestion[] multiQArray;
    @JsonProperty
    TextQuestion[] textQArray;


    public static void main(String[] args) {

        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        String [] answers1 = {"1. Мадрид", "2. Каир", "3. Порто", "4. София"};
        String [] answers2 = {"1. Мадрид", "2. Каир", "3. Порто", "4. София"};
        String [] answers3 = {"1. Мадрид", "2. Каир", "3. Порто", "4. София"};
        String [] answers4 = {"1. Мадрид", "2. Каир", "3. Порто", "4. София"};

        AbstractQuestion [] aq = new AbstractQuestion[12];

        QuestionJSON test = new QuestionJSON();
        test.singleQArray = new SingleChoiceQuestion[4];
        test.textQArray = new TextQuestion[4];
        test.multiQArray = new MultiChoiceQuestion[4];



        SingleChoiceQuestion question = new SingleChoiceQuestion("Столица Испании?", answers1, 1);
        test.singleQArray[0] = question;
        SingleChoiceQuestion question2 = new SingleChoiceQuestion("Столица Португалии?", answers2, 3);
        test.singleQArray[1] = question2;
        SingleChoiceQuestion question3 = new SingleChoiceQuestion("Столица Египта?", answers3, 2);
        test.singleQArray[2] = question3;
        SingleChoiceQuestion question4 = new SingleChoiceQuestion("Столица Болгарии?", answers4, 4);
        test.singleQArray[3] = question4;
//        TextQuestion question5 = new TextQuestion("textq1", "texta1", new TreeSet<>());
//        test.textQArray[0] = question5;
//        MultiChoiceQuestion question6 = new MultiChoiceQuestion("mq1", new String[] {"mqa1", "mqa2", "mqa3"}, new TreeSet<>(Arrays.asList(1,2)), new TreeSet<>());
//        test.multiQArray[0] = question6;
        MultiChoiceQuestion question5 = new MultiChoiceQuestion("Какие страны входят в Прибалтику?", new String[] {"1. Латвия", "2. Литва", "3. Эстония", "4. Рига"}, new TreeSet<>(Arrays.asList(1,2,3)), new TreeSet<>());
        test.multiQArray[0] = question5;
        MultiChoiceQuestion question6 = new MultiChoiceQuestion("Сколько рёбер у человека?", new String[] {"1. 24", "2. 12", "3. 18", "4. 12-пар"}, new TreeSet<>(Arrays.asList(1,4)), new TreeSet<>());
        test.multiQArray[1] = question6;
        MultiChoiceQuestion question7 = new MultiChoiceQuestion("Какое млекопитающее умеет летать?", new String[] {"1. Летучая мышь", "2. Белоголовый орлан", "3. Колуго", "4. Белка-летяга"}, new TreeSet<>(Arrays.asList(1,4)), new TreeSet<>());
        test.multiQArray[2] = question7;
        MultiChoiceQuestion question8 = new MultiChoiceQuestion("В каком направлении восходит солнце?", new String[] {"1. Север", "2. Юг", "3. Солнце остается на месте", "4. Восток"}, new TreeSet<>(Arrays.asList(3,4)), new TreeSet<>());
        test.multiQArray[3] = question8;
        TextQuestion question9 = new TextQuestion("Какого цвета нет в радуге?", "коричневого", new TreeSet<>());
        test.textQArray[0] = question9;
        TextQuestion question10 = new TextQuestion("Сколько республик входило в состав СССР?", "пятнадцать", new TreeSet<>());
        test.textQArray[1] = question10;
        TextQuestion question11 = new TextQuestion("Какое крупное парнокопытное животное обитает в Гренландии?", "овцебык", new TreeSet<>());
        test.textQArray[2] = question11;
        TextQuestion question12 = new TextQuestion("Какой город подарил свое название государственному гимну Французской республики?", "марсель", new TreeSet<>());
        test.textQArray[3] = question12;

//        SingleChoiceQuestion [] arraySCQ = {question, question2, question3, question4};

        try {
            objectMapper.writeValue(file, test);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //QuestionJSON qs = new QuestionJSON();
        //System.out.println(Arrays.toString(qs.SCQreadFromJSON()));

        QuestionJSON test2 =null;

        try {
            test = objectMapper.readValue(file, QuestionJSON.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(test.singleQArray));
        System.out.println(Arrays.toString(test.multiQArray));
        System.out.println(Arrays.toString(test.textQArray));




    }

    static void writeToJSON (AbstractQuestion[] array)  {

        try {
            objectMapper.writeValue(file, array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public AbstractQuestion [] SCQreadFromJSON () {

        AbstractQuestion [] arrayQ = new AbstractQuestion[0];
        try {
            arrayQ = objectMapper.readValue(file, SingleChoiceQuestion[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayQ;
    }

//    public AbstractQuestion [] TQreadFromJSON () {
//
//        AbstractQuestion [] arrayQ = new AbstractQuestion[0];
//        try {
//            arrayQ = objectMapper.readValue(file, TextQuestion[].class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return arrayQ;
//    }


}
