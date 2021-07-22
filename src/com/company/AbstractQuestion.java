package com.company;

import java.util.TreeSet;

public abstract class AbstractQuestion {
    String Question;
    abstract Boolean ValidateAnswer();
    TreeSet<String> tags;
}
