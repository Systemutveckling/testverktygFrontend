/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.model.Course;
import com.model.Studentanswer;
import com.model.Test;
import com.model.Testresult;
import com.model.User;
import com.model.UserHasTest;
import com.serverconnection.Server;
import java.util.List;
import javafx.stage.Stage;

/**
 *
 * @author hampus
 */
public class Logic {

    private static Logic p = null;

    private Server server = new Server();

    User user;
    User userStudent;
    private Stage studentStage;
    private Course choosenCourseToCreateTestTo;
    private Test createdTempTest;
    private Test test;
    private Course course;
    private UserHasTest userHasTest;
    private Testresult testresult;
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

    public List<User> getUsers() {
        return server.getUsers();
    }
    
    public void saveStudentAnswer(Studentanswer sa){
        server.saveStudentAnswer(sa);
    }
    
    public void updateStudentTestStatus(UserHasTest uht){
        server.updateStudentTestStatus(uht);
    }

    
    public int saveCreatedTestToDb(Test createdTempTest){
        return server.saveCreatedTestToDb(createdTempTest);
    }
    
    public void addCreatedTestToCourseAndUser(int courseId,int testId){
        server.addCreatedTestToCourseAndUser(courseId, testId);
    }

    public List<UserHasTest> getUserTests(int userId) {
        return server.getUserTests(userId);
    }

    public Test getCreatedTempTest() {
        return createdTempTest;
    }

    public void setCreatedTempTest(Test createdTempTest) {
        this.createdTempTest = createdTempTest;
    }

    public Course getChoosenCourseToCreateTestTo() {
        return choosenCourseToCreateTestTo;
    }

    public void setChoosenCourseToCreateTestTo(Course choosenCourseToCreateTestTo) {
        this.choosenCourseToCreateTestTo = choosenCourseToCreateTestTo;
    }
    
    public List<User> getStudentsInCourse(int courseId){
        return server.getStudentsInCourse(courseId);
    }
   

    public void setPickedTest(Test test) {
        this.test = test;
    }

    public Test getPickedTest() {
        return test;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    public void setUserEmail(User user){
        this.user=user;
    }
    public User getUserEmail(){
        return user;
    }
    
   public void setUserStudent(User userStudent){
       this.userStudent=userStudent;
   }
   public User getUserStudent(){
       return userStudent;
   }

    public Stage getStudentStage() {
        return studentStage;
    }

    public void setStudentStage(Stage studentStage) {
        this.studentStage = studentStage;
    }
   
   

}
