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
    }
    @JsonProperty
    String correctAnswer;
    @JsonProperty
    String currentAnswer;

    @Override
    Boolean validateAnswer() {
        return correctAnswer.equals(currentAnswer);
    }

    @Override
    Float validateAnswerPartial() {
        if (validateAnswer())
            return 1.0f;
        return 0.0f;
    }

    @Override
    String displayString() {
        return question + "; correct answer: " + correctAnswer + "; user answer: " + currentAnswer;
    }

}
