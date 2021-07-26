package com.company;

import javax.swing.*;
import java.awt.*;

public class TextQuestionCard extends UpperPanelCard{
    private JLabel questionText;
    private JTextField answerInput;
    private TextQuestion state;
    public TextQuestionCard(String name, MyWindowFrame parent) {
        super(name, parent);
    }
    public void init() {
        questionText = new JLabel("InitialText");
        answerInput = new JTextField();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.7;
        add(questionText, c);
        questionText.setVisible(true);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 0.3;
        add(answerInput ,c);
        answerInput.setVisible(true);
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
