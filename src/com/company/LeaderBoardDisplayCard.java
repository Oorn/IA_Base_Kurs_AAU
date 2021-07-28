package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class LeaderBoardDisplayCard extends UpperPanelCard{
    public LeaderBoardDisplayCard(String name, MyWindowFrame parent) {
        super(name, parent);
    }
    JTable resultsTable;
    JScrollPane intermediateScroll;
    JPanel intermediatePanel;
    JScrollPane tableScroll;

    TestResult[] state;

    String[] columnNames;
    private boolean isUpToDate;
    @Override
    public void init() {
        resultsTable = new JTable();
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setVisible(true);

        //this multiple panel scroll madness provides: proper centering, proper resizing to fit, proper minimal size, proper scroll, proper headers
        //it is surprisingly difficult to achieve with a single scroll
        intermediateScroll = new JScrollPane(resultsTable);
        intermediateScroll.setVisible(true);

        intermediatePanel = new JPanel();
        intermediatePanel.setLayout(new BorderLayout());
        intermediatePanel.add(intermediateScroll, BorderLayout.CENTER);
        intermediatePanel.setVisible(true);

        tableScroll = new JScrollPane(intermediatePanel);
        tableScroll.setVisible(true);


        setLayout(new BorderLayout());
        add(tableScroll, BorderLayout.CENTER);

        setVisible(true);

        columnNames = new String[]{"Name", "Score", "Comment"};
        isUpToDate = false;
    }
    public void setState(TestResult[] state) {
        if (this.state == state)
            return;
        this.state = state;
        isUpToDate = false;
    }
    public void updateFromState() {
        if (isUpToDate)
            return;
        resultsTable.clearSelection();
        resultsTable.setModel(new DefaultTableModel( Arrays
                .stream(state)
                .map(TestResult::generateTableEntry)
                .toArray(Object[][]::new) , columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        isUpToDate = true;
    }
    public TestResult getSelection() {
        int resInd = resultsTable.getSelectedRow();
        if (resInd == -1)
            return null;
        return state[resInd];
    }
}
