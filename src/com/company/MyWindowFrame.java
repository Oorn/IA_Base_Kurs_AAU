package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.function.Consumer;

public class MyWindowFrame extends JFrame {
    JPanel upperPanel;
    JPanel lowerPanel;
    JButton okButton;
    JButton cancelButton;

    BannerCard bannerPanel;
    //TextQuestionCard inputNamePanel;
    TextQuestionCard textQuestionPanel;
    MultiChoiceQuestionCard multiChoiceQuestionPanel;
    LeaderBoardDisplayCard leaderBoardPanel;
    ResultsDetailedCard testResultPanel;
    SingleChoiceQuestionCard singleChoiceQuestionPanel;

    private static final String BANNER_CARD = "Banner Card";
    //private static final String INPUT_NAME_CARD = "Input Name Card";
    private static final String TEXT_QUESTION_CARD = "Text Question Card";
    private static final String MULTI_QUESTION_CARD = "Multi Question Card";
    private static final String SINGLE_QUESTION_CARD = "Single Question Card";
    private static final String LEADERBOARD_CARD = "Leaderboard Card";
    private static final String RESULTS_DETAILED_CARD = "Test Result Card";

    boolean isBlocked;
    public MyWindowFrame() {}
    public void init() {
        setTitle("Quiz Game");

        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initPanels();
        initLowerPanelButtons();
        initUpperPanelCards();


        isBlocked = true;

    }
    private void initPanels() {
        upperPanel = new JPanel();
        //MultiChoiceQuestionCard t = new MultiChoiceQuestionCard("MultiQuestion", this);
        //t.init();
        //t.setState(new MultiChoiceQuestion("testQuestion", new String[]{"answer1", "answer2", "answer3", "answer4", "answer5"}, null, null));
        //t.updateFromState();
        //upperPanel = t;

        lowerPanel = new JPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.8;
        add(upperPanel, c);
        upperPanel.setVisible(true);

        c.gridy = 1;
        c.weighty = 0.2;
        add(lowerPanel, c);
        lowerPanel.setVisible(true);
    }
    private void initLowerPanelButtons() {
        lowerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 1;

        okButton = new JButton("Ok");
        okButton.setVisible(true);
        lowerPanel.add(okButton, c);


        c.gridx = 0;
        cancelButton = new JButton("Cancel");
        cancelButton.setVisible(true);
        lowerPanel.add(cancelButton, c);
    }
    private void initUpperPanelCards() {
        upperPanel.setLayout(new CardLayout());

        bannerPanel = new BannerCard(BANNER_CARD, this);
        bannerPanel.init();
        upperPanel.add(bannerPanel, BANNER_CARD);

        textQuestionPanel = new TextQuestionCard(TEXT_QUESTION_CARD, this);
        textQuestionPanel.init();
        upperPanel.add(textQuestionPanel, TEXT_QUESTION_CARD);

        multiChoiceQuestionPanel = new MultiChoiceQuestionCard(MULTI_QUESTION_CARD, this);
        multiChoiceQuestionPanel.init();
        upperPanel.add(multiChoiceQuestionPanel, MULTI_QUESTION_CARD);

        leaderBoardPanel = new LeaderBoardDisplayCard(LEADERBOARD_CARD, this);
        leaderBoardPanel.init();
        upperPanel.add(leaderBoardPanel, LEADERBOARD_CARD);

        testResultPanel = new ResultsDetailedCard(RESULTS_DETAILED_CARD, this);
        testResultPanel.init();
        upperPanel.add(testResultPanel, RESULTS_DETAILED_CARD);

        singleChoiceQuestionPanel = new SingleChoiceQuestionCard(SINGLE_QUESTION_CARD, this);
        singleChoiceQuestionPanel.init();
        upperPanel.add(singleChoiceQuestionPanel, SINGLE_QUESTION_CARD);
    }
    private void displayCard(String Card) {
        ((CardLayout)(upperPanel.getLayout())).show(upperPanel, Card);
    }
    private void destroyButtonListeners () {
        for( ActionListener al : okButton.getActionListeners() ) {
            okButton.removeActionListener( al );
        }
        for( ActionListener al : cancelButton.getActionListeners() ) {
            cancelButton.removeActionListener( al );
        }
    }
    public void unblock() {
        isBlocked = false;
    }
    public void displayMainMenu(Runnable newGamePress, Runnable highScoresPress) {
        destroyButtonListeners();
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        okButton.setText("High Scores");
        cancelButton.setText("New Game");
        displayCard(BANNER_CARD);
        okButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            highScoresPress.run();
        });
        cancelButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            newGamePress.run();
        });
        setVisible(true);
        unblock();
    }
    public void displayInputName(Consumer<String> okPress, Runnable cancelPress) {
        TextQuestion nameQuestion = new TextQuestion("Please input your Username", "", null);
        displayTextQuestion(nameQuestion, (tq) -> okPress.accept(tq.currentAnswer), cancelPress);

    }
    public void displayTextQuestion(TextQuestion question, Consumer<TextQuestion> commitPress, Runnable cancelPress) {
        destroyButtonListeners();
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        okButton.setText("Ok");
        cancelButton.setText("Cancel");

        textQuestionPanel.setState(question);
        textQuestionPanel.updateFromState();
        displayCard(TEXT_QUESTION_CARD);

        okButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            textQuestionPanel.writeToState();
            commitPress.accept(textQuestionPanel.getState());
        });

        cancelButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            cancelPress.run();
        });

        setVisible(true);
        unblock();
    }
    public void displaySingleQuestion(SingleChoiceQuestion question, Consumer<SingleChoiceQuestion> commitPress, Runnable cancelPress) {
        destroyButtonListeners();
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        okButton.setText("Ok");
        cancelButton.setText("Cancel");

        singleChoiceQuestionPanel.setState(question);
        singleChoiceQuestionPanel.updateFromState();
        displayCard(SINGLE_QUESTION_CARD);

        okButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            singleChoiceQuestionPanel.writeToState();
            commitPress.accept(singleChoiceQuestionPanel.getState());
        });

        cancelButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            cancelPress.run();
        });

        setVisible(true);
        unblock();
    }
    public void displayMultiQuestion(MultiChoiceQuestion question, Consumer<MultiChoiceQuestion> commitPress, Runnable cancelPress) {
        destroyButtonListeners();
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        okButton.setText("Ok");
        cancelButton.setText("Cancel");

        multiChoiceQuestionPanel.setState(question);
        multiChoiceQuestionPanel.updateFromState();
        displayCard(MULTI_QUESTION_CARD);

        okButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            multiChoiceQuestionPanel.writeToState();
            commitPress.accept(multiChoiceQuestionPanel.getState());
        });

        cancelButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            cancelPress.run();
        });

        setVisible(true);
        unblock();
    }
    public void displayHighScores(TestResult[] results, Consumer<TestResult> detailsPress, Runnable cancelPress) {
        destroyButtonListeners();
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        okButton.setText("Result Details");
        cancelButton.setText("Cancel");

        leaderBoardPanel.setState(results);
        leaderBoardPanel.updateFromState();
        displayCard(LEADERBOARD_CARD);

        okButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            TestResult res = leaderBoardPanel.getSelection();
            if (res == null) {
                isBlocked = false;
                return;
            }

            detailsPress.accept(res);
        });

        cancelButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            cancelPress.run();
        });

        setVisible(true);
        unblock();
    }
    void displayTestResult(TestResult result, Runnable backPress) {
        destroyButtonListeners();
        okButton.setVisible(false);
        cancelButton.setVisible(true);
        cancelButton.setText("Ok");

        testResultPanel.setState(result);
        testResultPanel.updateFromState();
        displayCard(RESULTS_DETAILED_CARD);

        cancelButton.addActionListener((e) -> {
            if (isBlocked)
                return;
            isBlocked = true;
            backPress.run();
        });
        setVisible(true);
        unblock();
    }
}
