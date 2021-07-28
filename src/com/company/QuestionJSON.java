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

        AbstractQuestion [] aq = new AbstractQuestion[5];

        QuestionJSON test = new QuestionJSON();
        test.singleQArray = new SingleChoiceQuestion[4];
        test.textQArray = new TextQuestion[1];
        test.multiQArray = new MultiChoiceQuestion[1];



        SingleChoiceQuestion question = new SingleChoiceQuestion("Столица Испании?", answers1, 1);
        test.singleQArray[0] = question;
        SingleChoiceQuestion question2 = new SingleChoiceQuestion("Столица Португалии?", answers2, 3);
        test.singleQArray[1] = question2;
        SingleChoiceQuestion question3 = new SingleChoiceQuestion("Столица Египта?", answers3, 2);
        test.singleQArray[2] = question3;
        SingleChoiceQuestion question4 = new SingleChoiceQuestion("Столица Болгарии?", answers4, 4);
        test.singleQArray[3] = question4;
        TextQuestion question5 = new TextQuestion("textq1", "texta1", new TreeSet<>());
        test.textQArray[0] = question5;
        MultiChoiceQuestion question6 = new MultiChoiceQuestion("mq1", new String[] {"mqa1", "mqa2", "mqa3"}, new TreeSet<>(Arrays.asList(1,2)), new TreeSet<>());
        test.multiQArray[0] = question6;


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
