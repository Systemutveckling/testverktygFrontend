/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.model.User;
import com.model.UserHasTest;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class Course implements Serializable {

    private Integer id;

    private String name;
    
    private List<User> userList;
    
    private List<UserHasTest> userHasTestList;

    public Course() {
    }

    public Course(Integer id) {
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
    }

    
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    
    public List<UserHasTest> getUserHasTestList() {
        return userHasTestList;
    }

    public void setUserHasTestList(List<UserHasTest> userHasTestList) {
        this.userHasTestList = userHasTestList;
    }
}
