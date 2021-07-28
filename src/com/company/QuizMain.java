package com.company;

import java.util.*;


public class QuizMain {

    Object [] questions;
    private TreeSet<Integer> currentAnswers;
    private static int score;



    public static void main(String[] args) {


        QuestionJSON qs = new QuestionJSON();
        AbstractQuestion[] questions = qs.SCQreadFromJSON();
        TreeSet<Integer> set = new TreeSet<>();
        Scanner scanner = new Scanner(System.in);



        for (int i = 0; i < questions.length; i++) {


                System.out.println(questions[i].getQuestion()); //вывод вопроса
                //get breaks json? System.out.println(Arrays.toString(questions[i].getOfferedAnswers())); //вывод вариантов ответов
                System.out.println("Ваш ответ: ");
                //questions[i].setCurrentAnswers(scanner.nextInt()); //присваиваем ответ
                if (questions[i].ValidateAnswer()) { //сравниваем
                    score++; //начисляем балл, если true
                    System.out.println("Правильно!");
                } else System.out.println("Ошибка..");
        }
        System.out.println("Ваш результат: " + score);
    }

    public QuizMain(Object[] questions, TreeSet<Integer> currentAnswers, int score) {
        this.questions = questions;
        this.currentAnswers = currentAnswers;
        this.score = score;
    }
}
