package com.company;

import java.util.TreeSet;

public class TextQuestion extends AbstractQuestion {
    TextQuestion(String question, String correctAnswer, TreeSet<String> tags) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.currentAnswer = "";
        this.tags = tags;
        //todo
    }

    public TextQuestion() {
    }

    String correctAnswer;
    String currentAnswer;
    String [] offeredAnswers;

    @Override
    Boolean ValidateAnswer() {
        //todo
        return null;
    }

    @Override
    String displayString() {
        return question + "; correct answer: " + correctAnswer + "; user answer: " + currentAnswer;
    }

    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public String[] getOfferedAnswers() {
        return new String[0];
    }

    @Override
    public void setCurrentAnswers(int nextInt) {

    }

}
