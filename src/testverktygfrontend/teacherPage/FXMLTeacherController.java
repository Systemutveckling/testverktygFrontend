/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.teacherPage;

import com.logic.Logic;
import com.model.Course;
import com.model.Test;
import com.model.User;
import com.model.UserHasTest;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hampus
 */
public class FXMLTeacherController implements Initializable {

    Logic logic = Logic.getInstanceOf();

    @FXML
    private Label profileLabel;

    ObservableList<Course> courses = FXCollections.observableArrayList();
    ObservableList<User> students = FXCollections.observableArrayList();
    ObservableList<Test> tests = FXCollections.observableArrayList();
    ObservableList<Test> testsNotDone = FXCollections.observableArrayList();

    @FXML
    private ListView courseListView;
    @FXML
    private ListView studentList;
    @FXML
    private ListView testDoneList;

    private int id = -1;
    @FXML
    private ListView testNotDoneList;
    @FXML
    private Button btnStart,showTest;
    @FXML
    private Button logOut;

    @FXML
    private void seeCourse(MouseEvent event) throws IOException {
        
        if (!(courseListView.getSelectionModel().getSelectedIndex() == -1)) {
            
            tests.clear();
            students.clear();
            testsNotDone.clear();
            for (User c : logic.getUsers()) {
                for (Course d : c.getCourseList()) {
                    if (d.getName().equals(courseListView.getSelectionModel().getSelectedItem().toString()) && c.getAuthorization() == 0) {
                        students.add(c);
                        System.out.println(c.getEMail());
                        logic.setCourse(d);
                    }
                }
            }

            studentList.setItems(students);
        }

    }

    @FXML
    private void seeTests(MouseEvent event) throws IOException {
        System.out.println("inne");
             showTest.setDisable(true);
            btnStart.setDisable(true);
        tests.clear();
        testsNotDone.clear();
        List<User> fakeStudentList = new ArrayList();
        System.out.println(logic.getCourse().getId());
        for (User c : logic.getStudentsInCourse(logic.getCourse().getId())) {
            
            if (c.getAuthorization() == 0) {
                System.out.println(c.getEMail());
                fakeStudentList.add(c);
            }
        }
    
        if (!(studentList.getSelectionModel().getSelectedIndex() == -1)) {

            for (UserHasTest d : fakeStudentList.get(studentList.getSelectionModel().getSelectedIndex()).getUserHasTestList()) {

                if (d.getIsDone() == 0 && d.getCourseId().getId() == logic.getCourse().getId()) {
                    testsNotDone.add(d.getTestId());

                } else if (d.getIsDone() == 1 && d.getCourseId().getId() == logic.getCourse().getId()) {
                    tests.add(d.getTestId());

                }
            }
                
            
            testNotDoneList.setItems(testsNotDone);

            testDoneList.setItems(tests);

        }
        
        
       
    }

    @FXML
    private void getTest(MouseEvent event) throws IOException {
        if (!(testDoneList.getSelectionModel().getSelectedIndex() == -1)) {
            showTest.setDisable(false);
            btnStart.setDisable(false);
            System.out.println(testDoneList.getSelectionModel().getSelectedItem());
            Test test = (Test) testDoneList.getSelectionModel().getSelectedItem();
            logic.setPickedTest(test);
            User userStudent = (User) studentList.getSelectionModel().getSelectedItem();
            logic.setUserStudent(userStudent);
        }
        
    }
    
    @FXML
    private void getTestUndone(MouseEvent event) throws IOException {
        if (!(testNotDoneList.getSelectionModel().getSelectedIndex() == -1)) {
            btnStart.setDisable(false);
            System.out.println(testNotDoneList.getSelectionModel().getSelectedItem());
            Test test = (Test) testNotDoneList.getSelectionModel().getSelectedItem();
            logic.setPickedTest(test);
            User userStudent = (User) studentList.getSelectionModel().getSelectedItem();
            logic.setUserStudent(userStudent);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTest.setDisable(true);
        btnStart.setDisable(true);
        profileLabel.setText(logic.getUser().getEMail());
    
        for (Course c : logic.getUser().getCourseList()) {

            courses.add(c);
        }

        courseListView.setItems(courses);
        logic.login(logic.getUser().getEMail(), logic.getUser().getPassword());
    }

    @FXML
    private void createTest(ActionEvent event) throws IOException {
        int index = courseListView.getSelectionModel().getSelectedIndex();
        logic.setChoosenCourseToCreateTestTo(courses.get(index));
        Stage s = new Stage();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/testverktygfrontend/createtest/FXMLCreateTest.fxml")));
        s.setScene(sc);
        s.show();
    }

    @FXML
    private void goToStatistics(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/testverktygfrontend/Statistic/StatisticsPage.fxml"));
        Scene s = new Scene(root);
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stg.setScene(s);
        stg.show();

    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/testverktygfrontend/FXMLDocument.fxml")));
        stg.setScene(sc);
        stg.show();
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/testverktygfrontend/FXMLDocument.fxml"));
        Scene one = new Scene(root);
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stg.setScene(one);
        stg.show();

    }
    
       @FXML
    private void showResultAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        //Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/testverktygfrontend/teacherPage/FXMLShowStudentResult.fxml")));
        stage.setScene(sc);
        stage.show();
    
    }

}
