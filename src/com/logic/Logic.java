/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.model.Test;
import com.model.User;
import com.serverconnection.Server;

/**
 *
 * @author hampus
 */
public class Logic {

    private static Logic p = null;

    private Server server = new Server();

    User user;
    private Test createdTempTest;
    private Test test;

    public static Logic getInstanceOf() {
        if (p == null) {
            p = new Logic();
        }
        return p;
    }

    private Logic() {
        user = new User();
    }

    public User login(String username, String password) {

        User loggedInUser = server.login(username, password);

        this.user = loggedInUser;

        return loggedInUser;
    }

    public User getUser() {
        return this.user;
    }
    
    public void saveCreatedTestToDb(Test createdTempTest){
        server.saveCreatedTestToDb(createdTempTest);
    }

    public Test getCreatedTempTest() {
        return createdTempTest;
    }

    public void setCreatedTempTest(Test createdTempTest) {
        this.createdTempTest = createdTempTest;
    }
    
    

    public void setPickedTest(Test test){
        this.test = test;
    }
    
    public Test getPickedTest(){
        return test;
    }
}
