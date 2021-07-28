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

        setVisible(true);
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
        unblock();
    }



    public static void main(String[] args) {
        // testing of GUI
        GUIManager test = new GUIManager();
        test.initialize();
        //test.displayMainMenu(() -> test.displayInputName((s) -> test.displayTextQuestion(new TextQuestion(s, "", null), (tq) -> test.displayMultiChoiceQuestion(new MultiChoiceQuestion(tq.currentAnswer, new String[]{"Answer1", "Answer2"}, new TreeSet<>(), null), (mcq) -> test.close(), test::close), test::close), test::close), test::close);
        TestResult[] testRes = new TestResult[7];
        TestResult[] testRes2 = new TestResult[2];
        testRes[0] = new TestResult("name1", 1, "comment1", null);
        testRes[1] = new TestResult("name2", 2, "comment2", null);
        testRes[2] = new TestResult("name3", 3, "comment3", null);
        testRes[3] = new TestResult("name4", 4, "comment4", null);
        testRes[4] = new TestResult("name5", 5, "comment5", null);
        testRes[5] = new TestResult("name6", 6, "comment6", null);
        testRes[6] = new TestResult("name7", 7, "comment7", null);

        testRes2[0] = new TestResult("name1", 1, "comment1", null);
        testRes2[1] = new TestResult("name2", 2, "comment2", null);

        class testFunctionBase {
            Runnable closeTest = test::close;
            Consumer<TextQuestion> textQuestionOkTest;
            Consumer<TestResult> highScoresOkTest;
        }
        testFunctionBase testBase = new testFunctionBase();

        Runnable closeTest = test::close;
        Consumer<TextQuestion> textQuestionOkTest;
        testBase.highScoresOkTest = (tRes) -> test.displayTextQuestion(
                        new TextQuestion(tRes.name, "", null),
                        testBase.textQuestionOkTest,
                        closeTest);
        testBase.textQuestionOkTest = (tRes) -> test.displayHighScores(
                testRes2,
                testBase.highScoresOkTest,
                closeTest);


        //test.displayHighScores(testRes, testBase.highScoresOkTest, closeTest);

        AbstractQuestion[] testQuiz = new AbstractQuestion[7];
        TextQuestion tq;
        MultiChoiceQuestion mq;
        tq = new TextQuestion("text question 1" , "correct answer 1", null);
        tq.currentAnswer = "user answer 1";
        testQuiz[0] = tq;
        tq = new TextQuestion("text question 2" , "correct answer 2", null);
        tq.currentAnswer = "user answer 2";
        testQuiz[1] = tq;
        tq = new TextQuestion("text question 3" , "correct answer 3", null);
        tq.currentAnswer = "user answer 3";
        testQuiz[2] = tq;
        tq = new TextQuestion("text question 4" , "correct answer 4", null);
        tq.currentAnswer = "user answer 4";
        testQuiz[3] = tq;
        tq = new TextQuestion("text question 5" , "correct answer 5", null);
        tq.currentAnswer = "user answer 5";
        testQuiz[4] = tq;

        mq = new MultiChoiceQuestion("multi question 6", new String[] {"multi answer 1", "multi answer 2", "multi answer 3"}, new TreeSet<>(Arrays.asList(0,2)), null);
        mq.currentAnswers = new TreeSet<>(Arrays.asList(0,1));
        testQuiz[5] = mq;
        mq = new MultiChoiceQuestion("multi question 7", new String[] {"multi answer 1", "multi answer 2", "multi answer 3"}, new TreeSet<>(Arrays.asList(1,2)), null);
        mq.currentAnswers = new TreeSet<>(Arrays.asList(1,2));
        testQuiz[6] = mq;

        TestResult tr1 = null, tr2 = new TestResult("test name", 10, "test commentaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", testQuiz);
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        File file = new File("test.json");

        try {
            jsonMapper.writeValue(file, tr2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tr1 = jsonMapper.readValue(file, TestResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        test.displayTestResult(tr1, testBase.closeTest);

        SingleChoiceQuestion sq = new SingleChoiceQuestion("single question", new String[] {"answer1", "answer2"}, 0, null);
        SingleChoiceQuestion sq2 = new SingleChoiceQuestion("single question2", new String[] {"answer1", "answer2", "answer3"}, 1, null);
        //test.displayAbstractQuestionTypeless(sq, () -> { System.out.println(sq.displayString()); test.close(); },
        //        () -> test.displayAbstractQuestionTypeless(sq2, () -> { System.out.println(sq2.displayString()); test.close(); } , test::close));
    }
}
