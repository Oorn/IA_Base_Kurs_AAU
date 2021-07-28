package com.company;

import java.util.TreeSet;

public abstract class AbstractQuestion {


    String question;
    TreeSet<String> tags;

    public AbstractQuestion() {
    }

    abstract Boolean ValidateAnswer();

    abstract String displayString();

    public abstract String getQuestion();

    public abstract String[] getOfferedAnswers();

    public abstract void setCurrentAnswers(int nextInt);
}


