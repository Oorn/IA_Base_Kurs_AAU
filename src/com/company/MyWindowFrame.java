package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    UpperPanelCard leaderBoardPanel; //todo
    UpperPanelCard testResultPanel; //todo

    private static final String BANNER_CARD = "Banner Card";
    //private static final String INPUT_NAME_CARD = "Input Name Card";
    private static final String TEXT_QUESTION_CARD = "Text Question Card";
    private static final String MULTI_QUESTION_CARD = "Multi Question Card";
    private static final String LEADERBOARD_CARD = "Leaderboard Card";
    private static final String TEST_RESULT_CARD = "Test Result Card";

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

        leaderBoardPanel = null;//todo

        testResultPanel = null; //todo
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
    public void displayMultiQuestion(MultiChoiceQuestion question, Consumer<MultiChoiceQuestion> commitPress, Runnable cancelPress) {
        destroyButtonListeners();
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
    void displayHighScores(TestResult[] results, Consumer<TestResult> detailsPress, Runnable cancelPress) {
        //todo
    }
    void displayTestResult(TestResult result, Runnable backPress) {

        //todo
    }


    public static void main(String[] args) {
        // testing of GUI
        GUIManager test = new GUIManager();
        test.initialize();
        test.displayMainMenu(() -> test.displayInputName((s) -> test.displayTextQuestion(new TextQuestion(s, "", null), (tq) -> test.displayMultiChoiceQuestion(new MultiChoiceQuestion(tq.currentAnswer, new String[]{"Answer1", "Answer2"}, new TreeSet<>(), null), (mcq) -> test.close(), test::close), test::close), test::close), test::close);
    }
}
