package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.TreeSet;

public abstract class AbstractQuestion {


    @JsonProperty
    String question;
    @JsonProperty("tags")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property  = "String")
    TreeSet<String> tags;

    public AbstractQuestion() {
    }

    abstract Boolean ValidateAnswer();

    abstract String displayString();

    public abstract String getQuestion();

    //public abstract String[] getOfferedAnswers();

    //public abstract void setCurrentAnswers(int nextInt);

}


