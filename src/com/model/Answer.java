/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.List;


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
    }

    public Short getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Short isCorrect) {
        this.isCorrect = isCorrect;
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
