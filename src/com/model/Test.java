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

public class Test implements Serializable {

 
    private Integer id;


    private String name;

  
    private String description;
    
    private Integer timeLimit;
    
    private Short seeResult;
    
    private List<UserHasTest> userHasTestList;
    
    private List<Question> questionList;
    private final StringProperty nameProp = new SimpleStringProperty();
    private final StringProperty descriptionProp = new SimpleStringProperty();
    private final IntegerProperty timeLimitProp = new SimpleIntegerProperty();

    public int getTimeLimitProp() {
        return timeLimitProp.get();
    }

    public void setTimeLimitProp(int value) {
        timeLimitProp.set(value);
    }

    public IntegerProperty timeLimitPropProperty() {
        return timeLimitProp;
    }
    
    public String getDescriptionProp() {
        return descriptionProp.get();
    }

    public void setDescriptionProp(String value) {
        descriptionProp.set(value);
    }

    public StringProperty descriptionPropProperty() {
        return descriptionProp;
    }
    
    public String getNameProp() {
        return nameProp.get();
    }

    public void setNameProp(String value) {
        nameProp.set(value);
    }

    public StringProperty namePropProperty() {
        return nameProp;
    }
    
    public Test() {
    }

    public Test(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setNameProp(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        setDescriptionProp(description);
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
        setTimeLimitProp(timeLimit);
    }

    public Short getSeeResult() {
        return seeResult;
    }

    public void setSeeResult(Short seeResult) {
        this.seeResult = seeResult;
    }

    
    public List<UserHasTest> getUserHasTestList() {
        return userHasTestList;
    }

    public void setUserHasTestList(List<UserHasTest> userHasTestList) {
        this.userHasTestList = userHasTestList;
    }

    
    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
    
}
