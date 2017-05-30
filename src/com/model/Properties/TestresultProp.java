/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.Properties;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class TestresultProp {

    private int userId;

    private int testId;
    private final StringProperty question = new SimpleStringProperty();
    private final StringProperty answer = new SimpleStringProperty();
    private final BooleanProperty isCorrect = new SimpleBooleanProperty();

    public TestresultProp() {
    }

    public TestresultProp(int userId, int testId) {
        this.userId = userId;
        this.testId = testId;
    }

    public boolean isIsCorrect() {
        return isCorrect.get();
    }

    public void setIsCorrect(boolean value) {
        isCorrect.set(value);
    }

    public BooleanProperty isCorrectProperty() {
        return isCorrect;
    }

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(String value) {
        answer.set(value);
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String value) {
        question.set(value);
    }

    public StringProperty questionProperty() {
        return question;
    }

}
