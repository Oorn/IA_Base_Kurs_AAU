package com.company;

import java.util.TreeSet;

public class MultiChoiceQuestion extends AbstractQuestion {
    MultiChoiceQuestion(String question, String[] offeredAnswers, TreeSet<Integer> correctAnswers, TreeSet<String> tags){
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswers = correctAnswers;
        this.tags = tags;
        currentAnswers = new TreeSet<>();
        //todo
    }
    String[] offeredAnswers;
    TreeSet<Integer> correctAnswers;
    TreeSet<Integer> currentAnswers;

    @Override
    Boolean ValidateAnswer() {
        //todo
        return null;
    }
}
