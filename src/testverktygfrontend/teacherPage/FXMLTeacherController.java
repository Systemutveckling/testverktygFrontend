/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend;

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
import javafx.scene.Scene;
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
    private Label användarnamn;

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
    private void seeCourse(MouseEvent event) throws IOException {
        
        if (!(courseListView.getSelectionModel().getSelectedIndex() == -1)) {
            tests.clear();
            students.clear();
            for (User c : logic.getUsers()) {
                for (Course d : c.getCourseList()) {
                    if (d.getName().equals(courseListView.getSelectionModel().getSelectedItem().toString()) && c.getAuthorization() == 0) {
                        students.add(c);
                        System.out.println(c.getEMail());
                    }
                }
            }

            studentList.setItems(students);
        }

    }

    @FXML
    private void seeTests(MouseEvent event) throws IOException {

        tests.clear();
        testsNotDone.clear();
        List<User> fakeStudentList = new ArrayList();

        for (User c : logic.getUsers()) {
            if (c.getAuthorization() == 0) {
                fakeStudentList.add(c);
            }
        }

        if (!(studentList.getSelectionModel().getSelectedIndex() == -1)) {

            for (UserHasTest d : fakeStudentList.get(studentList.getSelectionModel().getSelectedIndex()).getUserHasTestList()) {

                if (d.getIsDone() == 0) {
                    testsNotDone.add(d.getTestId());
                    
                    System.out.println(d.getGrade());
                } else if (d.getIsDone() == 1) {
                        tests.add(d.getTestId());
                    
                }
            }

            testNotDoneList.setItems(testsNotDone);

            testDoneList.setItems(tests);

        } 

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        användarnamn.setText(logic.getUser().getEMail());

        for (Course c : logic.getUser().getCourseList()) {
            
            courses.add(c);
        }

        courseListView.setItems(courses);
        
    }

    @FXML
    private void createTest(ActionEvent event) throws IOException {
        int index = courseListView.getSelectionModel().getSelectedIndex();
        logic.setChoosenCourseToCreateTestTo(courses.get(index));
        Stage s = new Stage();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("createtest/FXMLCreateTest.fxml")));
        s.setScene(sc);
        s.show();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")));
        stg.setScene(sc);
        stg.show();
    }

}
