package com.company;

import java.util.Arrays;

public class SingleChoiceQuestion extends AbstractQuestion {

    String question;
    String[] offeredAnswers;
    int correctAnswer;
    int currentAnswer;

    public SingleChoiceQuestion(String question, String[] offeredAnswers, int correctAnswer){
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswer = correctAnswer;
    }

    public SingleChoiceQuestion () {}


    Boolean ValidateAnswer() {
        if (this.currentAnswer == this.correctAnswer) {
            return true;
        } else return false;
    }

    String displayString() {
        return null;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "SingleChoiceQuestion{" +
                "question='" + question + '\'' +
                ", offeredAnswers=" + Arrays.toString(offeredAnswers) +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOfferedAnswers() {
        return offeredAnswers;
    }

    public void setOfferedAnswers(String[] offeredAnswers) {
        this.offeredAnswers = offeredAnswers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getCurrentAnswers() {
        return currentAnswer;
    }

    public void setCurrentAnswers(int currentAnswers) {
        this.currentAnswer = currentAnswers;
    }
}
