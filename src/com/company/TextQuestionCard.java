package com.company;

import javax.swing.*;
import java.awt.*;

public class TextQuestionCard extends UpperPanelCard{

    private JTextArea questionText;
    private JPanel separatorPanel;
    private JLabel separatorLabel;
    private JTextArea answerInput;
    private TextQuestion state;

    public TextQuestionCard(String name, MyWindowFrame parent) {
        super(name, parent);
    }
    public void init() {
        questionText = new JTextArea("Should never be seen");
        prepareJText(questionText);
        separatorPanel = new JPanel();
        separatorLabel = new JLabel("INPUT:");
        answerInput = new JTextArea();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.5;
        add(questionText, c);

        c.gridy = 1;
        c.weighty = 0.3;
        add(separatorPanel ,c);
        separatorPanel.setVisible(true);

        c.anchor = GridBagConstraints.SOUTH;
        c.fill = GridBagConstraints.NONE;
        separatorPanel.setLayout(new GridBagLayout());
        separatorPanel.add(separatorLabel, c);


        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 2;
        c.weighty = 0.2;
        add(answerInput ,c);
        answerInput.setVisible(true);

        setVisible(true);
    }
    public void setState(TextQuestion q) {
        state = q;
    }
    public TextQuestion getState() {
        return state;
    }

    public void updateFromState() {
        if (state == null)
            return;
        answerInput.setText(state.currentAnswer);
        questionText.setText(state.question);
    }
    public void writeToState() {
        state.currentAnswer = answerInput.getText();
    }

}
