package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Arrays;

public class ResultsDetailedCard extends UpperPanelCard{
    public ResultsDetailedCard(String name, MyWindowFrame parent) {
        super(name, parent);
    }
    JList<String> questionList;
    JPanel intermediatePanel;
    JScrollPane tableScroll;

    JTextArea header;
    JTextArea footer;

    TestResult state;
    private boolean isUpToDate;

    DefaultListModel<String> listModel;
    @Override
    public void init() {
        listModel = new DefaultListModel<>();
        questionList = new JList<>(listModel);
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        questionList.setVisible(true);

        intermediatePanel = new JPanel();
        intermediatePanel.setLayout(new BorderLayout());
        intermediatePanel.add(questionList, BorderLayout.CENTER);
        intermediatePanel.setVisible(true);

        tableScroll = new JScrollPane(intermediatePanel);
        tableScroll.setVisible(true);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.2;

        header = new JTextArea("Should never be seen");
        prepareJText(header);
        add(header, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        c.weighty = 0.5;
        add(tableScroll, c);

        footer = new JTextArea("Should never be seen");
        prepareJText(footer);
        c.gridy = 2;
        c.weighty = 0.3;
        add(footer, c);

        setVisible(true);
        isUpToDate = false;

        questionList.addListSelectionListener((e) -> {
            if (e.getValueIsAdjusting())
                return;
            if (questionList.getSelectedIndex() < 0)
                return;
            footer.setText(state.questionsAndAnswers[
                    questionList.getSelectedIndex()]
                    .displayString());
        });

    }
    public void setState(TestResult state) {
        if (this.state == state)
            return;
        this.state = state;
        isUpToDate = false;
    }
    public void updateFromState() {
        if (isUpToDate)
            return;

        header.setText("Name: " + state.name + "\nScore: " + state.score + "\nComment: " + state.comment);
        footer.setText("");

        questionList.clearSelection();

        listModel.clear();
        Arrays.stream(state.questionsAndAnswers).forEach((q)-> listModel.addElement(q.question));

        isUpToDate = true;

    }



}
