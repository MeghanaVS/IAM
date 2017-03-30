package com.iam;

import org.apache.commons.lang3.StringUtils;

public class Question {

	private String question;
    private String answer;

    public Question() {
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean hasValue() {
       return !StringUtils.isBlank(question) && !StringUtils.isBlank(answer);
    }
}
