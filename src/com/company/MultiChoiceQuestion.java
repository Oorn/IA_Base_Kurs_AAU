package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.TreeSet;

public class MultiChoiceQuestion extends AbstractQuestion {
    public MultiChoiceQuestion() {
        question = "";
        offeredAnswers = new String[0];
        correctAnswers = new TreeSet<>();
        tags = new TreeSet<>();
        currentAnswers = new TreeSet<>();
    }

    MultiChoiceQuestion(String question, String[] offeredAnswers, TreeSet<Integer> correctAnswers, TreeSet<String> tags){
        this.question = question;
        this.offeredAnswers = offeredAnswers;
        this.correctAnswers = correctAnswers;
        this.tags = tags;
        currentAnswers = new TreeSet<>();
    }
    @JsonProperty
    String[] offeredAnswers;
    @JsonProperty
    TreeSet<Integer> correctAnswers;
    @JsonProperty
    TreeSet<Integer> currentAnswers;

    @Override
    public Boolean validateAnswer() {
        return (validateAnswerPartial() == 1.0f);
    }

    @Override
    public Float validateAnswerPartial() {
        int matches = 0;
        int mismatches = 0;
        matches += (int) correctAnswers.stream().filter(c -> currentAnswers.contains(c)).count();
        mismatches += (int) correctAnswers.stream().filter(c -> !currentAnswers.contains(c)).count();
        mismatches += (int) currentAnswers.stream().filter(c -> !correctAnswers.contains(c)).count();
        if (mismatches == 0)
            return 1.0f;
        if (matches >= mismatches)
            return 0.5f;
        return 0.0f;
    }

    @Override
    public String displayString() {
        StringBuilder res = new StringBuilder(question);

        res.append("\ncorrect answers: [");
        correctAnswers.forEach((i) -> {
            res.append(offeredAnswers[i]);
            res.append(", ");
        });
        res.append(']');
        if (correctAnswers.isEmpty())
            res.append(", ");
        res.delete(res.length() - 3, res.length() - 1);

        res.append("\nuser answers: [");
        currentAnswers.forEach((i) -> {
            res.append(offeredAnswers[i]);
            res.append(", ");
        });
        res.append(']');
        if (currentAnswers.isEmpty())
            res.append(", ");
        res.delete(res.length() - 3, res.length() - 1);

        return res.toString();
    }

    @Override
    public AbstractQuestion copy() {
        MultiChoiceQuestion copy = new MultiChoiceQuestion();
        if (question != null)
            copy.question = question;

        if (tags != null)
            copy.tags.addAll(tags);

        if (offeredAnswers != null)
            copy.offeredAnswers = Arrays.copyOf(offeredAnswers, offeredAnswers.length);

        if (correctAnswers != null)
            copy.correctAnswers.addAll(correctAnswers);

        if (currentAnswers != null)
            copy.currentAnswers.addAll(currentAnswers);

        return copy;
    }
}
