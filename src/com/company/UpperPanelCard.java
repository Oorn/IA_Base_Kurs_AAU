package com.company;

import javax.swing.*;

public abstract class UpperPanelCard extends JPanel {
    String name;
    MyWindowFrame parent;

    public UpperPanelCard(String name, MyWindowFrame parent) {
        this.name = name;
        this.parent = parent;
    }
    abstract public void init();
    static protected void prepareJText(JTextArea a) {
        a.setEditable(false);
        a.setLineWrap(true);
        a.setWrapStyleWord(true);
        a.setVisible(true);
    }

}
