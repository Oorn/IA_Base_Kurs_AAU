package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.TreeSet;

public class SingleChoiceQuestion extends AbstractQuestion{
    public SingleChoiceQuestion() {
        question = "";
        offeredAnswers = new String[0];
        correctAnswer = -1;
        tags = new TreeSet<>();
        currentAnswer = -1;
    }

    SingleChoiceQuestion(String question, String[] offeredAnswers, Integer correctAnswer, TreeSet<String> tags){
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswer = correctAnswer;
        this.tags = tags;
        currentAnswer = -1;
    }
    @JsonProperty
    String[] offeredAnswers;
    @JsonProperty
    Integer correctAnswer;
    @JsonProperty
    Integer currentAnswer;
    @Override
    Boolean validateAnswer() {
        return (currentAnswer == correctAnswer);
    }

    @Override
    Float validateAnswerPartial() {
        if (validateAnswer())
            return 1.0f;
        return 0.0f;
    }

    @Override
    String displayString() {
        return question + "; correct answer: " + offeredAnswers[correctAnswer] + "; user answer: " + offeredAnswers[currentAnswer];
    }
}
