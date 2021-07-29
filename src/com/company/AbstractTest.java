package com.company;

public abstract class AbstractTest {
    public AbstractQuestion currentQuestion = null;


    public abstract boolean questionFirst();
    //sets currentQuestion to first question of the test, should always return true

    public abstract boolean questionNext();
    //sets currentQuestion to next relative question, returns false if there is no next question

    public abstract boolean questionPrevious();
    //sets currentQuestion to previous relative question, returns false if there is no previous question

    public abstract void start();
    public abstract void complete();

    public abstract TestResult generateResult();
    //generates TestResult from initial test construction and changes made to various currentQuestion elements
}
