/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.User;
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class UserHasTest implements Serializable {

    private Integer id;

    private String grade;

    private Short isDone;

    private Course courseId;

    private Test testId;

    private User userId;
    private final StringProperty gradeProp = new SimpleStringProperty();

    public String getGradeProp() {
        return gradeProp.get();
    }

    public void setGradeProp(String value) {
        gradeProp.set(value);
    }

    public StringProperty gradePropProperty() {
        return gradeProp;
    }

    public UserHasTest() {
    }

    public UserHasTest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
        setGradeProp(grade);
    }

    public Short getIsDone() {
        return isDone;
    }

    public void setIsDone(Short isDone) {
        this.isDone = isDone;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    /*public User getUserId() {
        return userId;
    }*/
    public void setUserId(User userId) {
        this.userId = userId;
    }

   
    
   
}
