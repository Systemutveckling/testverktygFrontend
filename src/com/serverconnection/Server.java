/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serverconnection;

import com.model.Answer;
import com.model.Question;
import com.model.Test;
import com.model.User;
import java.util.ArrayList;
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

    public Server() {
        client = ClientBuilder.newClient();
    }

    public List<User> getUsers() {
        List<User> user = client.target("http://localhost:8080/testverktygbackend/webapi/users")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>>() {
        });

        return user;
    }

    public void saveCreatedTestToDb(Test test,List qandaList) {

        /*List qandaList = new ArrayList<>();

        Question q = new Question();
        q.setQuestion("Sven Tumba?");

        Answer a = new Answer();
        a.setAnswer("Pelle");
        a.setIsCorrect(Short.valueOf("1"));
        Answer a1 = new Answer();
        a1.setAnswer("Kalle");
        a1.setIsCorrect(Short.valueOf("0"));
        Answer a2 = new Answer();
        a2.setAnswer("Nisse");
        a2.setIsCorrect(Short.valueOf("0"));
        
        Question q1 = new Question();
        q1.setQuestion("Vad heter jag?");
        
        Answer b = new Answer();
        b.setAnswer("Terminator");
        b.setIsCorrect(Short.valueOf("1"));
        Answer b1 = new Answer();
        b1.setAnswer("Persson");
        b1.setIsCorrect(Short.valueOf("0"));
        Answer b2 = new Answer();
        b2.setAnswer("Svensson");
        b2.setIsCorrect(Short.valueOf("0"));
                        
        qandaList.add(q);
        qandaList.add(a);
        qandaList.add(a1);
        qandaList.add(a2);
        
        qandaList.add(q1);
        qandaList.add(b);
        qandaList.add(b1);
        qandaList.add(b2);
        
        
        Test t = new Test();
        t.setName("Ett annat test");
        t.setDescription("Just precis");
        t.setTimeLimit(200);
        t.setSeeResult(Short.valueOf("1"));*/

        Test dbTest = client.target("http://localhost:8080/testverktygBackend/webapi/tests")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(test, "application/json;charset=utf-8"), Test.class);

        Question dbQuestion = new Question();
        
        Answer dbAnswer = new Answer();
        for (int i = 0; i < qandaList.size(); i++) {
            if (qandaList.get(i) instanceof Question) {
                dbQuestion = client.target("http://localhost:8080/testverktygBackend/webapi/tests")
                        .path(String.valueOf(dbTest.getId()))
                        .path("questions")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(qandaList.get(i), "application/json;charset=utf-8"), Question.class);
            } else {
                dbAnswer = client.target("http://localhost:8080/testverktygBackend/webapi/tests")
                        .path(String.valueOf(dbTest.getId()))
                        .path("questions")
                        .path(String.valueOf(dbQuestion.getId()))
                        .path("answers")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(qandaList.get(i), "application/json;charset=utf-8"), Answer.class);
            }
        }
    }
}
