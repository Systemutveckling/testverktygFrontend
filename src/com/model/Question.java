/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;


import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class Question implements Serializable {

    private Integer id;
 
    private String question;

    private String imgUrl;
    
    private List<Answer> answerList;
    
    private Test testId;
    
    private List<Studentanswer> studentanswerList;

    public Question() {
    }

    public Question(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

   
    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    /*public Test getTestId() {
        return testId;
    }*/

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    public List<Studentanswer> getStudentanswerList() {
        return studentanswerList;
    }

    public void setStudentanswerList(List<Studentanswer> studentanswerList) {
        this.studentanswerList = studentanswerList;
    }
   
}
