package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.TreeSet;


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        property = "questionType")
@JsonSubTypes({
        @JsonSubTypes.Type(MultiChoiceQuestion.class),
        @JsonSubTypes.Type(SingleChoiceQuestion.class),
        @JsonSubTypes.Type(TextQuestion.class)
})
public abstract class AbstractQuestion {
    @JsonProperty
    String question;
    @JsonProperty
    TreeSet<String> tags;

    abstract Boolean validateAnswer();
    abstract Float validateAnswerPartial();
    abstract String displayString();

}
