/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.Properties;

import com.model.Answer;
import com.model.Studentanswer;
import com.model.Test;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class QuestionProp {

    private int id;
    private final StringProperty question = new SimpleStringProperty();
    private final StringProperty imgUrl = new SimpleStringProperty();
    private List<AnswerProp> answerList;

    private TestProp testId;

    private List<StudentanswerProp> studentanswerList;

    public QuestionProp() {
    }

    public QuestionProp(int id, List<AnswerProp> answerList, TestProp testId, List<StudentanswerProp> studentanswerList) {
        this.id = id;
        this.answerList = answerList;
        this.testId = testId;
        this.studentanswerList = studentanswerList;
    }

    public String getImgUrl() {
        return imgUrl.get();
    }

    public void setImgUrl(String value) {
        imgUrl.set(value);
    }

    public StringProperty imgUrlProperty() {
        return imgUrl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestProp getTestId() {
        return testId;
    }

    public void setTestId(TestProp testId) {
        this.testId = testId;
    }

    public List<AnswerProp> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerProp> answerList) {
        this.answerList = answerList;
    }

    public List<StudentanswerProp> getStudentanswerList() {
        return studentanswerList;
    }

    public void setStudentanswerList(List<StudentanswerProp> studentanswerList) {
        this.studentanswerList = studentanswerList;
    }

}
