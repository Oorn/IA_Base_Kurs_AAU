package com.company;

import java.util.*;

public class SimpleTest extends AbstractTest{


    private AbstractQuestion[] questions;
    private int questionNumber;

    private String name;
    private long startTime;
    private long finishTime;
    private boolean isStarted, isComplete;
    private int currentQuestionIndex;

    private static final int MAX_SCORE = 100;

    private SimpleTest(){}
    public static SimpleTest generate(List<AbstractQuestion> potentialQuestions, int questionsToSelect, String userName) {
        SimpleTest res = new SimpleTest();

        ArrayList<AbstractQuestion> randomQuestions = new ArrayList<>(potentialQuestions);
        Collections.shuffle(randomQuestions);

        res.questionNumber = Math.min(questionsToSelect, randomQuestions.size());

        res.questions = new AbstractQuestion[res.questionNumber];
        for (int i = 0; i < res.questionNumber; i++)
            res.questions[i] = randomQuestions.get(i).copy();

        res.name = userName;
        res.startTime = res.finishTime = -1;
        res.isStarted = false;
        res.isComplete = false;
        res.currentQuestionIndex = -1;

        return res;

    }

    private boolean updateCurrentQuestion() {
        if ((currentQuestionIndex < 0) || (currentQuestionIndex >= questionNumber))
            return false;
        currentQuestion = questions[currentQuestionIndex];
        return true;
    }
    @Override
    public boolean questionFirst() {
        currentQuestionIndex = 0;
        return updateCurrentQuestion();
    }

    @Override
    public boolean questionNext() {
        currentQuestionIndex++;
        if (updateCurrentQuestion())
            return true;

        currentQuestionIndex--;
        return false;
    }

    @Override
    public boolean questionPrevious() {
        currentQuestionIndex--;
        if (updateCurrentQuestion())
            return true;

        currentQuestionIndex++;
        return false;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        isStarted = true;
    }

    @Override
    public void complete() {
        finishTime = System.currentTimeMillis();
        isComplete = true;
    }

    @Override
    public TestResult generateResult() {
        double relativeScoreAtom = 1.0d / questionNumber;
        double relativeScoreFinal = 0.0d;
        for (int i = 0; i < questionNumber; i++)
            relativeScoreFinal += relativeScoreAtom * questions[i].validateAnswerPartial();
        int finalScore = (int) (MAX_SCORE * relativeScoreFinal);

        String comment;
        if (isStarted && isComplete)
            comment = "Test time = " + ((finishTime - startTime) / 1000) + "seconds";
        else
            comment = "Test not timed";

        return new TestResult(name, finalScore, comment, questions);
    }

}
