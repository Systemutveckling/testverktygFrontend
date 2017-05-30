/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.Properties;

import com.model.User;
import com.model.UserHasTest;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class CourseProp {

    private int id;
    private final StringProperty name = new SimpleStringProperty();
    private List<UserProp> userList;

    private List<UserHasTestProp> userHasTestList;

    public CourseProp() {
    }

    public CourseProp(int id, List<UserProp> userList, List<UserHasTestProp> userHasTestList) {
        this.id = id;
        this.userList = userList;
        this.userHasTestList = userHasTestList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<UserProp> getUserList() {
        return userList;
    }

    public void setUserList(List<UserProp> userList) {
        this.userList = userList;
    }

    public List<UserHasTestProp> getUserHasTestList() {
        return userHasTestList;
    }

    public void setUserHasTestList(List<UserHasTestProp> userHasTestList) {
        this.userHasTestList = userHasTestList;
    }

}
