/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

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
public class User implements Serializable {
    
    private Integer id;
    
    private String eMail;
    
    private String password;
    
    private Integer authorization;
    
    private List<Course> courseList;
    
    private List<UserHasTest> userHasTestList;
    
    private List<Studentanswer> studentanswerList;
    private final StringProperty emailProp = new SimpleStringProperty();
    private final IntegerProperty authorizationProp = new SimpleIntegerProperty();

    public User() {
    }
    
    public User(Integer id) {
        this.id = id;
    }

    public int getAuthorizationProp() {
        return authorizationProp.get();
    }
    
    public void setAuthorizationProp(int value) {
        authorizationProp.set(value);
    }
    
    public IntegerProperty authorizationPropProperty() {
        return authorizationProp;
    }
    
    public String getEmailProp() {
        return emailProp.get();
    }
    
    public void setEmailProp(String value) {
        emailProp.set(value);
    }
    
    public StringProperty emailPropProperty() {
        return emailProp;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEMail() {
        return eMail;
    }
    
    public void setEMail(String eMail) {
        this.eMail = eMail;
        setEmailProp(eMail);
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getAuthorization() {
        return authorization;
    }
    
    public void setAuthorization(Integer authorization) {
        this.authorization = authorization;
        setAuthorizationProp(authorization);
    }

   
    public List<Course> getCourseList() {
        return courseList;
    }
    
    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
    
    public List<UserHasTest> getUserHasTestList() {
        return userHasTestList;
    }
    
    public void setUserHasTestList(List<UserHasTest> userHasTestList) {
        this.userHasTestList = userHasTestList;
    }
    
    @XmlTransient
    public List<Studentanswer> getStudentanswerList() {
        return studentanswerList;
    }
    
    public void setStudentanswerList(List<Studentanswer> studentanswerList) {
        this.studentanswerList = studentanswerList;
    }
    
}
