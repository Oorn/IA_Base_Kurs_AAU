package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestResult {
    @JsonProperty
    String name;
    @JsonProperty
    int score;
    @JsonProperty
    String comment;
    @JsonProperty
    AbstractQuestion[] questionsAndAnswers;

    public TestResult() {
        name = "";
        score = -1;
        comment = "";
        questionsAndAnswers = new AbstractQuestion[0];
    }

    public TestResult(String name, int score, String comment, AbstractQuestion[] questionsAndAnswers) {
        this.name = name;
        this.score = score;
        this.comment = comment;
        this.questionsAndAnswers = questionsAndAnswers;
    }
    public Object[] generateTableEntry() {
        Object[] res = new Object[3];
        res[0] = name;
        res[1] = score;
        res[2] = comment;
        return res;
    }
}
