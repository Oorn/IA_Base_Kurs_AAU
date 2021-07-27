package com.company;

public class TestResult {
    String name;
    int score;
    String comment;
    AbstractQuestion[] questionsAndAnswers;

    public TestResult(String name, int score, String comment, AbstractQuestion[] questionsAndAnswers) {
        this.name = name;
        this.score = score;
        this.comment = comment;
        this.questionsAndAnswers = questionsAndAnswers;
    }
    public Object[] getTableEntry() {
        Object[] res = new Object[3];
        res[0] = name;
        res[1] = score;
        res[2] = comment;
        return res;
    }
}
