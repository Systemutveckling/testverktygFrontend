/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.Properties;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class StudentanswerProp {

    private int id;
    private AnswerProp answerId;

    private QuestionProp questionId;

    private UserProp userId;

    public StudentanswerProp() {
    }

    public StudentanswerProp(int id, AnswerProp answerId, QuestionProp questionId, UserProp userId) {
        this.id = id;
        this.answerId = answerId;
        this.questionId = questionId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnswerProp getAnswerId() {
        return answerId;
    }

    public void setAnswerId(AnswerProp answerId) {
        this.answerId = answerId;
    }

    public QuestionProp getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionProp questionId) {
        this.questionId = questionId;
    }

    public UserProp getUserId() {
        return userId;
    }

    public void setUserId(UserProp userId) {
        this.userId = userId;
    }

}
