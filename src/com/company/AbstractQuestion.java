package com.company;

import java.util.TreeSet;

public abstract class AbstractQuestion {
    String question;
    abstract Boolean ValidateAnswer();
    TreeSet<String> tags;
}
