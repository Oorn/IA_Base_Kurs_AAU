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
    String correctAnswer;
    String currentAnswer;

    @Override
    Boolean ValidateAnswer() {
        //todo
        return null;
    }

    @Override
    String displayString() {
        return question + "; correct answer: " + correctAnswer + "; user answer: " + currentAnswer;
    }

}
