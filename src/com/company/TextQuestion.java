package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
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
    }
    @JsonProperty
    String correctAnswer;
    @JsonProperty
    String currentAnswer;

    @Override
    public Boolean validateAnswer() {
        return correctAnswer.equals(currentAnswer);
    }

    @Override
    public Float validateAnswerPartial() {
        if (validateAnswer())
            return 1.0f;
        return 0.0f;
    }

    @Override
    public String displayString() {
        return question + "\ncorrect answer: " + correctAnswer + "\nuser answer: " + currentAnswer;
    }

    @Override
    public AbstractQuestion copy() {
        TextQuestion copy = new TextQuestion();

        if (question != null)
            copy.question = question;

        if (tags != null)
            copy.tags.addAll(tags);

        if (correctAnswer != null)
            copy.correctAnswer = correctAnswer;

        if (currentAnswer != null)
            copy.currentAnswer = currentAnswer;

        return copy;
    }

}
