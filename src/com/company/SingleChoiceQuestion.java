package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
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
    public Boolean validateAnswer() {
        return (currentAnswer == correctAnswer);
    }

    @Override
    public Float validateAnswerPartial() {
        if (validateAnswer())
            return 1.0f;
        return 0.0f;
    }

    @Override
    public String displayString() {
        return question + "; correct answer: " + offeredAnswers[correctAnswer] + "; user answer: " + offeredAnswers[currentAnswer];
    }

    @Override
    public AbstractQuestion copy() {
        SingleChoiceQuestion copy = new SingleChoiceQuestion();
        if (question != null)
            copy.question = question;
        if (tags != null)
            copy.tags.addAll(tags);

        if (offeredAnswers != null)
            copy.offeredAnswers = Arrays.copyOf(offeredAnswers, offeredAnswers.length);

        if (correctAnswer != null)
            copy.correctAnswer = correctAnswer;

        if (currentAnswer != null)
            copy.currentAnswer = currentAnswer;

        return copy;
    }
}
