package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class QuizGameMain {
    static String questionsAddress = "questions.json";
    static String resultsAddress = "results.json";



    int questionCount = 10;
    AbstractQuestion[] potentialQuestions;
    ArrayList<TestResult> resultsList;
    AbstractTest currentTest;
    int currentMenu;
    GUIManager gui;

    static final int MAIN_MENU = 1;
    static final int NAME_SELECT = 2;
    static final int TEST_QUESTION = 3;
    static final int RESULT_DETAILS = 4;
    static final int LEADERBOARD = 5;

    void mainMenu() {
        currentMenu = MAIN_MENU;
        gui.displayMainMenu(this::nameInput, this::displayLeaderboard);
    }
    void startTestWithName(String name) {
        currentTest = SimpleTest.generate(Arrays.asList(potentialQuestions), questionCount, name);
        currentTest.questionFirst();
        currentTest.start();
        displayQuestion();
    }
    void nameInput() {
        currentMenu = NAME_SELECT;
        gui.displayInputName(this::startTestWithName, this::mainMenu);
    }
    void nextQuestion() {
        if (currentTest.questionNext())
            displayQuestion();
        else
            completeTest();
    }
    void previousQuestion() {
        if (currentTest.questionPrevious())
            displayQuestion();
        else
            nameInput();
    }
    void displayQuestion() {
        currentMenu = TEST_QUESTION;
        gui.displayAbstractQuestionTypeless(currentTest.currentQuestion, this::nextQuestion, this::previousQuestion);
    }
    void completeTest() {
        currentTest.complete();
        TestResult currentResult = currentTest.generateResult();
        resultsList.add(currentResult);
        Collections.sort(resultsList);
        saveResults();
        displayResult(currentResult);
    }
    void displayResult(TestResult result) {
        currentMenu = RESULT_DETAILS;
        gui.displayTestResult(result, this::displayLeaderboard);
    }
    void displayLeaderboard() {
        TestResult[] resultsArray = new TestResult[resultsList.size()];
        resultsArray = resultsList.toArray(resultsArray);

        currentMenu = LEADERBOARD;
        gui.displayHighScores(resultsArray, this::displayResult, this::mainMenu);
    }
    void saveResults() {
        File resultsFile = new File(resultsAddress);
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        TestResult[] resultsArray = new TestResult[resultsList.size()];
        resultsArray = resultsList.toArray(resultsArray);

        try {
            jsonMapper.writeValue(resultsFile, resultsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void readResults() {
        File resultsFile = new File(resultsAddress);
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        TestResult[] resultsArray = new TestResult[0];

        try {
            resultsArray = jsonMapper.readValue(resultsFile, TestResult[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        resultsList = new ArrayList<>(Arrays.asList(resultsArray));
    }
    void readQuestions() {
        File questionsFile = new File(questionsAddress);
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            potentialQuestions = jsonMapper.readValue(questionsFile, AbstractQuestion[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void startGame() {
        gui = new GUIManager();
        gui.initialize();
        readQuestions();
        readResults();
        mainMenu();
    }


    public static void main(String[] args) {
        generateQuestionsIfMissing();
        generateResultsIfMissing();

        new QuizGameMain().startGame();
    }



    static void generateQuestionsIfMissing() {
        File questionsFile = new File(questionsAddress);
        if (questionsFile.isFile())
            return;
        QuestionGenerator.generateQuestions(questionsAddress);
    }
    static void generateResultsIfMissing() {
        File resultsFile = new File(resultsAddress);
        if (resultsFile.isFile())
            return;

        TestResult[] testResultArrayPlug = new TestResult[0];

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            jsonMapper.writeValue(resultsFile, testResultArrayPlug);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            testResultArrayPlug = jsonMapper.readValue(resultsFile, TestResult[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}