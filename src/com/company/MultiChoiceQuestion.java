package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import netscape.javascript.JSObject;

import java.util.TreeSet;

public class MultiChoiceQuestion extends AbstractQuestion {
    public MultiChoiceQuestion() {
        question = "";
        offeredAnswers = new String[0];
        correctAnswers = new TreeSet<>();
        tags = new TreeSet<>();
        currentAnswers = new TreeSet<>();
    }
    MultiChoiceQuestion(String question, String[] offeredAnswers, TreeSet<Integer> correctAnswers, TreeSet<String> tags){
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswers = correctAnswers;
        this.tags = tags;
        currentAnswers = new TreeSet<>();
        //todo
    }
    @JsonProperty
    String[] offeredAnswers;
    @JsonProperty
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property  = "Integer")
    TreeSet<Integer> correctAnswers;
    @JsonProperty
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property  = "Integer")
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
