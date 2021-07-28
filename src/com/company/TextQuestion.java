package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.TreeSet;

public class TextQuestion extends AbstractQuestion {
    public TextQuestion() {
        question = "";
        correctAnswer = "";
        tags = new TreeSet<>();
        currentAnswer = "";
    }
    TextQuestion(String question, String correctAnswer, TreeSet<String> tags) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.currentAnswer = "";
        this.tags = tags;
        //todo
    }


    @JsonProperty
    String correctAnswer;
    @JsonProperty
    String currentAnswer;
    @JsonProperty
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
        return question;
    }

    //@Override
    //public String[] getOfferedAnswers() {
    //    return new String[0];
    //}

    @Override
    public void setCurrentAnswers(int nextInt) {

    }

}
