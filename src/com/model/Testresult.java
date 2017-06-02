/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;


/**
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */

public class Testresult implements Serializable {

    private int userId;
 
    private int testId;

    private String question;

    private String answer;
    
    private Short isCorrect;

    public Testresult() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
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

    public Short getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Short isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        String s;
        if(isCorrect == 0){
            
        }
        
        return  question + "\n" + answer;
    }

  
    
   

}
