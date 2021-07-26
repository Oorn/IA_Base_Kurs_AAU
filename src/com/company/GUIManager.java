package com.company;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

//by Andrey, responsible for all program interactions with its GUI
public class GUIManager {
    MyWindowFrame mainWindow;
    GUIManager() {
        mainWindow = new MyWindowFrame();
    }
    void initialize() {
        //must be called on initial creation
        mainWindow.init();
    }
    void displayMainMenu(Runnable newGamePress, Runnable highScoresPress) {
        //displays main menu
        //newGamePress is run when New Game button is pressed
        //highScoresPress is run when High Scores button is pressed
        //cannot callback more than once
        mainWindow.displayMainMenu(newGamePress, highScoresPress);
    }
    void displayInputName(Consumer<String> okPress, Runnable cancelPress) {
        //asks user to input name
        //okPress is called when Ok button is pressed, with inputted name as parameter
        //cancelPress is called when Cancel button is pressed
        //cannot callback more than once
        mainWindow.displayInputName(okPress, cancelPress);
    }
    void displayTextQuestion(TextQuestion question, Consumer<TextQuestion> commitPress, Runnable cancelPress) {
        //displays question to user and waits for the answer
        //commitPress is offered question object with currentAnswer fields filled, when Commit button is pressed
        //cancelPress is called when Cancel button is pressed
        //cannot callback more than once
        mainWindow.displayTextQuestion(question, commitPress, cancelPress);
    }
    void displayMultiChoiceQuestion(MultiChoiceQuestion question, Consumer<MultiChoiceQuestion> commitPress, Runnable cancelPress) {
        //displays question to user and waits for the answer
        //commitPress is offered question object with currentAnswer fields filled, when Commit button is pressed
        //cancelPress is called when Cancel button is pressed
        //cannot callback more than once
        mainWindow.displayMultiQuestion(question, commitPress, cancelPress);
    }
    void displayHighScores(TestResult[] results, Consumer<TestResult> detailsPress, Runnable cancelPress) {
        //displays high score list of already sorted results array
        //detailsPress is offered TestResult selected by user, when Details button is pressed
        //cancelPress is called when Cancel button is pressed
        //cannot callback more than once
        //todo
    }
    void displayTestResult(TestResult result, Runnable backPress) {
        //displays detailed breakdown of result
        //backPress is called when Back button is pressed
        //cannot callback more than once
        //todo
    }
    void close() {
        //closes the window
        mainWindow.dispatchEvent(new WindowEvent(mainWindow, WindowEvent.WINDOW_CLOSING));
    }
}
