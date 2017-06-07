/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.studentPage;

import com.logic.Logic;
import com.model.Course;
import com.model.Test;
import com.model.Testresult;
import com.model.UserHasTest;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author ramonachantaf
 */
public class FXMLStudentPageController implements Initializable {
    
    Logic logic = Logic.getInstanceOf();
    @FXML
    private Label profileLabel;
    @FXML
    private Button logOut;
    @FXML
    private ListView courses;
    @FXML
    private ListView testsToDo;
    @FXML
    private ListView testsDone;
    @FXML
    private Button btnStart,showTest;

    /**
     * Initializes the controller class.
     */
    
    
    ObservableList<Course> courseList = FXCollections.observableArrayList();
    ObservableList<Test> testToDoList = FXCollections.observableArrayList();
    ObservableList<Test> testDoneList = FXCollections.observableArrayList();
    
    @FXML
    private void listEvent(MouseEvent event) throws IOException {
        if (!(courses.getSelectionModel().getSelectedIndex() == -1)) {
            testToDoList.clear();
            testDoneList.clear();
            for (UserHasTest userTest : logic.getUser().getUserHasTestList()) {
                if (userTest.getCourseId().getName().equals(courses.getSelectionModel().getSelectedItem().toString())) {
                    if (userTest.getIsDone() == 0) {
                        testToDoList.add(userTest.getTestId());
                        
                        testsToDo.setItems(testToDoList);
                    } else {
                        testDoneList.add(userTest.getTestId());
                        logic.setPickedTest(userTest.getTestId());
                        testsDone.setItems(testDoneList);

                    }
                }
            }
        }

    }
    
     @FXML
    private void logOutAction(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/testverktygfrontend/FXMLDocument.fxml"));
            Scene one = new Scene(root);
            Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stg.setScene(one);
            stg.show();
    
    }
    
    
     @FXML
    private void getButtonAction(MouseEvent event) throws IOException {
         if (testsToDo.getSelectionModel().getSelectedIndex() != -1) {

         } 
             //gör att knappen blir klickbar
             btnStart.setDisable(false);
             //Hämtar det objektet man klickar på i listview
             Test a = (Test) testsToDo.getSelectionModel().getSelectedItem();
             logic.setPickedTest(a);
            
         
    }
      @FXML
    private void showButtonResult(MouseEvent event) throws IOException {
         if (testsDone.getSelectionModel().getSelectedIndex() != -1) {

         } 
             //gör att knappen blir klickbar
             showTest.setDisable(false);
             //Hämtar det objektet man klickar på i listview
//             Test b = (Test) testsDone.getSelectionModel().getSelectedItem();
//             logic.setResultFromTest(b);
//          UserHasTest userGrade = (UserHasTest) testsDone.getSelectionModel().getSelectedItem();
//          logic.setStudentGrade(userGrade);
    }
     @FXML
    private void startTestAction(ActionEvent event) throws IOException {
        // logic.setPickedTest((Test) testsToDo.getSelectionModel().getSelectedItem());
        Stage stage;
        Parent root;
        stage = new Stage();
        stage.setTitle("Starta testet");
        stage.setResizable(false);
        root = FXMLLoader.load(getClass().getResource("StartTestPopUp.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnStart.getScene().getWindow());
        stage.setOnCloseRequest((WindowEvent we) -> {
            stage.close();
        });
        stage.showAndWait();
    
    }
    
    //visar resultat på ett redan gjord test
     @FXML
    private void showResultAction(ActionEvent event) throws IOException {
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/testverktygfrontend/doingTest/FXMLShowTestResult.fxml")));
        stg.setScene(sc);
        stg.show();
    
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Sätter userName label till studentens mail
        profileLabel.setText(logic.getUser().getEMail());
        
        //Hämtar listan på kurser som en student har och visar den i Listviewn
        for (Course c : logic.getUser().getCourseList()) {
            courseList.add(c);
            courses.setItems(courseList);
        }
      
        btnStart.setDisable(true);
        showTest.setDisable(true);
    }    
      
    
}
