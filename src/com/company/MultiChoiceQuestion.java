package com.company;

import netscape.javascript.JSObject;

import java.util.TreeSet;

public class MultiChoiceQuestion extends AbstractQuestion {
    MultiChoiceQuestion(String question, String[] offeredAnswers, TreeSet<Integer> correctAnswers, TreeSet<String> tags){
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswers = correctAnswers;
        this.tags = tags;
        currentAnswers = new TreeSet<>();
        //todo
    }
    String[] offeredAnswers;
    TreeSet<Integer> correctAnswers;
    TreeSet<Integer> currentAnswers;


    @Override
    Boolean ValidateAnswer() {
        //todo
        return null;
    }

    @Override
    String displayString() {
        StringBuilder res = new StringBuilder(question);

        res.append("; correct answers: ");
        correctAnswers.forEach((i) -> {
            res.append(offeredAnswers[i]);
            res.append(", ");
        });
        if (correctAnswers.isEmpty())
            res.append("none, ");
        res.delete(res.length() - 2, res.length());

        res.append("; user answers: ");
        currentAnswers.forEach((i) -> {
            res.append(offeredAnswers[i]);
            res.append(", ");
        });
        if (currentAnswers.isEmpty())
            res.append("none, ");
        res.delete(res.length() - 2, res.length());

        return res.toString();
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
