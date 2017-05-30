/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.Properties;

import com.model.Question;
import com.model.UserHasTest;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class TestProp {

    private int id;
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final IntegerProperty timeLimit = new SimpleIntegerProperty();
    private final StringProperty seeResult = new SimpleStringProperty();
    private List<UserHasTestProp> userHasTestList;

    private List<QuestionProp> questionList;

    public TestProp() {
    }

    public TestProp(int id, List<UserHasTestProp> userHasTestList, List<QuestionProp> questionList) {
        this.id = id;
        this.userHasTestList = userHasTestList;
        this.questionList = questionList;
    }

    public String getSeeResult() {
        return seeResult.get();
    }

    public void setSeeResult(String value) {
        seeResult.set(value);
    }

    public StringProperty seeResultProperty() {
        return seeResult;
    }

    public int getTimeLimit() {
        return timeLimit.get();
    }

    public void setTimeLimit(int value) {
        timeLimit.set(value);
    }

    public IntegerProperty timeLimitProperty() {
        return timeLimit;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserHasTestProp> getUserHasTestList() {
        return userHasTestList;
    }

    public void setUserHasTestList(List<UserHasTestProp> userHasTestList) {
        this.userHasTestList = userHasTestList;
    }

    public List<QuestionProp> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionProp> questionList) {
        this.questionList = questionList;
    }

}
