/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.Properties;

import com.model.*;
import java.io.Serializable;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class UserProp implements Serializable {

    private int id;
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final IntegerProperty authorization = new SimpleIntegerProperty();
    private List<CourseProp> courseList;
    private List<UserHasTestProp> userHasTestList;
    private List<StudentanswerProp> studentanswerList;

    public UserProp() {
    }

    public UserProp(int id, List<CourseProp> courseList, List<UserHasTestProp> userHasTestList, List<StudentanswerProp> studentanswerList) {
        this.id = id;
        this.courseList = courseList;
        this.userHasTestList = userHasTestList;
        this.studentanswerList = studentanswerList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorization() {
        return authorization.get();
    }

    public void setAuthorization(int value) {
        authorization.set(value);
    }

    public IntegerProperty authorizationProperty() {
        return authorization;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String value) {
        password.set(value);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public List<CourseProp> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseProp> courseList) {
        this.courseList = courseList;
    }

    public List<UserHasTestProp> getUserHasTestList() {
        return userHasTestList;
    }

    public void setUserHasTestList(List<UserHasTestProp> userHasTestList) {
        this.userHasTestList = userHasTestList;
    }

    public List<StudentanswerProp> getStudentanswerList() {
        return studentanswerList;
    }

    public void setStudentanswerList(List<StudentanswerProp> studentanswerList) {
        this.studentanswerList = studentanswerList;
    }

}
