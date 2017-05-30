/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.Properties;

import com.model.Studentanswer;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class AnswerProp {

    private int id;
    private final StringProperty answer = new SimpleStringProperty();
    private final BooleanProperty isCorrect = new SimpleBooleanProperty();
    private QuestionProp questionId;
    private List<StudentanswerProp> studentanswerList;

    public AnswerProp() {
    }

    public AnswerProp(int id, QuestionProp questionId, List<StudentanswerProp> studentanswerList) {
        this.id = id;
        this.questionId = questionId;
        this.studentanswerList = studentanswerList;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionProp getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionProp questionId) {
        this.questionId = questionId;
    }

    public List<StudentanswerProp> getStudentanswerList() {
        return studentanswerList;
    }

    public void setStudentanswerList(List<StudentanswerProp> studentanswerList) {
        this.studentanswerList = studentanswerList;
    }

}
