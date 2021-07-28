package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SingleChoiceQuestionCard extends UpperPanelCard{
    private JTextArea questionText;
    private JRadioButton[] radioArray;
    private SingleChoiceQuestion state;
    private JPanel radioContainer;
    private ButtonGroup radioGroup;

    public SingleChoiceQuestionCard(String name, MyWindowFrame parent) {
        super(name, parent);
    }

    @Override
    public void init() {
        questionText = new JTextArea("Should never be seen");
        prepareJText(questionText);
        radioContainer = new JPanel();
        radioArray = new JRadioButton[0];
        radioGroup = new ButtonGroup();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.5;
        add(questionText, c);
        questionText.setVisible(true);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 0.5;
        add(radioContainer ,c);
        radioContainer.setVisible(true);

        setVisible(true);
    }
    private void setRadioNumber (int num) {
        int RADIO_PER_ROW = 3;
        if (radioArray.length == num)
            return;//old checkbox configuration is still valid

        Arrays.stream(radioArray).forEach((r) -> radioGroup.remove(r));

        remove(radioContainer);
        radioContainer = new JPanel();
        radioContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.5;
        add(radioContainer, c);
        c.gridy = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;

        radioArray = new JRadioButton[num];
        for (int i = 0; i < num; i++) {
            c.gridy = i / RADIO_PER_ROW;
            c.gridx = i % RADIO_PER_ROW;
            radioArray[i] = new JRadioButton("InitialRadio"+i);
            radioContainer.add(radioArray[i], c);
            radioGroup.add(radioArray[i]);
        }
    }
    public void setState(SingleChoiceQuestion q) {
        state = q;
    }
    public SingleChoiceQuestion getState() {
        return state;
    }

    public void updateFromState() {
        if (state == null)
            return;
        questionText.setText(state.question);

        setRadioNumber(state.offeredAnswers.length);
        for (int i = 0; i < state.offeredAnswers.length; i++)
            radioArray[i].setText(state.offeredAnswers[i]);
        radioGroup.clearSelection();
        if (state.currentAnswer != -1)
            radioArray[state.currentAnswer].setSelected(true);

    }
    public void writeToState() {
        int res = -1;
        for (int i = 0; i < radioArray.length; i++) {
            if (radioArray[i].isSelected())
                res = i;
        }
        state.currentAnswer = res;

    }
}
