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
}
