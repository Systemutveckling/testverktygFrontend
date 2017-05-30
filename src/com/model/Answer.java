/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class Answer implements Serializable {

    private Integer id;

    private String answer;

    private Short isCorrect;

    private Question questionId;

    private List<Studentanswer> studentanswerList;
    private final StringProperty answerProp = new SimpleStringProperty();
    private final BooleanProperty isCorrectProp = new SimpleBooleanProperty();

    public boolean isIsCorrectProp() {
        return isCorrectProp.get();
    }

    public void setIsCorrectProp(boolean value) {
        isCorrectProp.set(value);
    }

    public BooleanProperty isCorrectPropProperty() {
        return isCorrectProp;
    }

    public String getAnswerProp() {
        return answerProp.get();
    }

    public void setAnswerProp(String value) {
        answerProp.set(value);
    }

    public StringProperty answerPropProperty() {
        return answerProp;
    }

    public Answer() {
    }

    public Answer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        setAnswerProp(answer);
    }

    public Short getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Short isCorrect) {
        this.isCorrect = isCorrect;
        if(isCorrect==1){
            setIsCorrectProp(true);
        }else{
            setIsCorrectProp(false);
        }
        
    }

    /*public Question getQuestionId() {
        return questionId;
    }*/
    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    public List<Studentanswer> getStudentanswerList() {
        return studentanswerList;
    }

    public void setStudentanswerList(List<Studentanswer> studentanswerList) {
        this.studentanswerList = studentanswerList;
    }

}
