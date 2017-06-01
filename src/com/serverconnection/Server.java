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
import com.model.UserHasTest;
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

    public List<UserHasTest> getUserTests(int userId) {
        List<UserHasTest> tests = client.target("http://localhost:8080/testverktygbackend/webapi/users/students/"+userId+"/tests")
                .request(MediaType.APPLICATION_JSON).get(new GenericType<List<UserHasTest>>() {
        });
        return tests;
    }

    public User login(String email, String password) {

        User user = new User();
        user.setEMail(email);
        user.setPassword(password);

        User s = client.target("http://localhost:8080/testverktygbackend/webapi/users/login")
                .request(MediaType.APPLICATION_JSON).post(Entity.json(user), User.class);

        if (s.getEMail() == null) {
            User fakeUser = new User();
            fakeUser.setAuthorization(100);
            return fakeUser;
        } else {
            return s;
        }

    }

    public void saveCreatedTestToDb(Test test) {

        // För att kunna spara rätt i databasen så delar jag upp testobjektet i delar
        Test testToDb = new Test();
        testToDb.setName(test.getName());
        testToDb.setDescription(test.getDescription());
        testToDb.setTimeLimit(test.getTimeLimit());
        testToDb.setSeeResult(test.getSeeResult());
     
        // Sparar testet till databasen. Får tillbaka objektet med rätt id
        Test testFromDb = client.target("http://localhost:8080/testverktygbackend/webapi/tests")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(testToDb, "application/json;charset=utf-8"), Test.class);

        for (Question q : test.getQuestionList()) {
            Question questionToDb = new Question();
            questionToDb.setQuestion(q.getQuestion());
            questionToDb.setImgUrl(q.getImgUrl());
            questionToDb.setTestId(testFromDb);

            // Sparar frågor till databasen. Får tillbaka objekt med rätt id
            Question questionFromDb = client.target("http://localhost:8080/testverktygbackend/webapi/tests")
                    .path(String.valueOf(testFromDb.getId()))
                    .path("questions")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(questionToDb, "application/json;charset=utf-8"), Question.class);

            for (Answer a : q.getAnswerList()) {
                Answer answerToDb = new Answer();
                answerToDb.setAnswer(a.getAnswer());
                answerToDb.setIsCorrect(a.getIsCorrect());
                answerToDb.setQuestionId(questionToDb);
                
                 // Sparar svarsalternativ till databasen. Får tillbaka objekt med rätt id
                Answer answerFromDb = client.target("http://localhost:8080/testverktygbackend/webapi/tests")
                        .path(String.valueOf(testFromDb.getId()))
                        .path("questions")
                        .path(String.valueOf(questionFromDb.getId()))
                        .path("answers")
                        .request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(answerToDb, "application/json;charset=utf-8"), Answer.class);

            }

        }

        /*
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
        }*/
    }

}
