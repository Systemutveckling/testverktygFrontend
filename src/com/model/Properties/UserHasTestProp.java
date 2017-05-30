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
public class UserHasTestProp {

    private int id;
    private final StringProperty grade = new SimpleStringProperty();
    private final BooleanProperty isDone = new SimpleBooleanProperty();
    private CourseProp courseId;
    private TestProp testId;

    private UserProp userId;

    public UserHasTestProp() {
    }

    public UserHasTestProp(int id, CourseProp courseId, TestProp testId, UserProp userId) {
        this.id = id;
        this.courseId = courseId;
        this.testId = testId;
        this.userId = userId;
    }

    public boolean isIsDone() {
        return isDone.get();
    }

    public void setIsDone(boolean value) {
        isDone.set(value);
    }

    public BooleanProperty isDoneProperty() {
        return isDone;
    }

    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String value) {
        grade.set(value);
    }

    public StringProperty gradeProperty() {
        return grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseProp getCourseId() {
        return courseId;
    }

    public void setCourseId(CourseProp courseId) {
        this.courseId = courseId;
    }

    public TestProp getTestId() {
        return testId;
    }

    public void setTestId(TestProp testId) {
        this.testId = testId;
    }

    public UserProp getUserId() {
        return userId;
    }

    public void setUserId(UserProp userId) {
        this.userId = userId;
    }

}
