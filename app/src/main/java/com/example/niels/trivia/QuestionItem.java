package com.example.niels.trivia;

public class QuestionItem {
    private int questionID;
    private String question;
    private String Answer;
    private int value;

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getQuestionID() {
        return questionID;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return Answer;
    }

    public int getValue() {
        return value;
    }
}
