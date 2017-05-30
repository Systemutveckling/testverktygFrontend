/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serverconnection;

import com.model.User;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hampus
 */
public class Server {
    Client client;
    public Server(){
     client = ClientBuilder.newClient();
    }
    
    public List<User> getUsers(){
     List<User> user =  client.target("http://localhost:8080/testverktygbackend2/webapi/users")
            .request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>> (){});
            
            return user;
    }
    
     public User login(String email,String password){
     
        User user = new User();
        user.setEMail(email);
        user.setPassword(password);
        
        
        User s = client.target("http://localhost:8080/testverktygbackend2/webapi/users/login")
               .request(MediaType.APPLICATION_JSON).post(Entity.json(user), User.class);
                  
        if(s.getEMail() == null){
            User fakeUser = new User();
            fakeUser.setAuthorization(100);
            return  fakeUser;
        } else{
        return s; 
        }
     
            
    }
}
