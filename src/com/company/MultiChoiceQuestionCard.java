package com.company;

import javax.swing.*;
import java.awt.*;

public class MultiChoiceQuestionCard extends UpperPanelCard{
    private JLabel questionText;
    private JCheckBox[] checkBoxArray;
    private MultiChoiceQuestion state;
    private JPanel checkBoxContainer;

    public MultiChoiceQuestionCard(String name, MyWindowFrame parent) {
        super(name, parent);
    }
    public void init() {
        questionText = new JLabel("InitialText");
        checkBoxContainer = new JPanel();
        checkBoxArray = new JCheckBox[0];

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.5;
        add(questionText, c);
        questionText.setVisible(true);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 0.5;
        add(checkBoxContainer ,c);
        checkBoxContainer.setVisible(true);
    }

    private void setCheckboxNumber (int num) {
        int CHECKBOXES_PER_ROW = 3;
        if (checkBoxArray.length == num)
            return;//old checkbox configuration is still valid

        remove(checkBoxContainer);
        checkBoxContainer = new JPanel();
        checkBoxContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.5;
        add(checkBoxContainer, c);
        c.gridy = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;

        checkBoxArray = new JCheckBox[num];
        for (int i = 0; i < num; i++) {
            c.gridy = i / CHECKBOXES_PER_ROW;
            c.gridx = i % CHECKBOXES_PER_ROW;
            checkBoxArray[i] = new JCheckBox("InitialBox"+i);
            checkBoxContainer.add(checkBoxArray[i], c);
        }
    }

    public void setState(MultiChoiceQuestion q) {
        state = q;
    }
    public MultiChoiceQuestion getState() {
        return state;
    }

    public void updateFromState() {
        if (state == null)
            return;
        questionText.setText(state.question);

        setCheckboxNumber(state.offeredAnswers.length);
        for (int i = 0; i < state.offeredAnswers.length; i++) {
            checkBoxArray[i].setText(state.offeredAnswers[i]);
            checkBoxArray[i].setSelected(false);
        }
        state.currentAnswers.forEach((i) -> checkBoxArray[i].setSelected(true));

    }
    public void writeToState() {
        state.currentAnswers.clear();
        for (int i = 0; i < checkBoxArray.length; i++)
            if (checkBoxArray[i].isSelected())
                state.currentAnswers.add(i);
    }
}
