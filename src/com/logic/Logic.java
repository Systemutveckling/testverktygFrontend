/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.model.Test;
import com.serverconnection.Server;
import java.util.List;

/**
 *
 * @author hampus
 */
public class Logic {
    private static Logic p = null;
    
    private Server server = new Server();
    
    public static Logic getInstanceOf() {
        if (p == null) {
            p = new Logic();
        }
        return p;
    }

    private Logic() {

    }
    
    public void getFirstUser(){
       
        System.out.println(server.getUsers().get(0).getEMail());
    }
    
    public void saveCreatedTestToDb(Test test,List qandaList){
        server.saveCreatedTestToDb(test, qandaList);
    }
    
}
