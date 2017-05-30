/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend;

import com.logic.Logic;
import com.model.Course;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ramonachantaf
 */
public class FXMLStudentPageController implements Initializable {
    
    Logic logic = Logic.getInstanceOf();
    @FXML
    private Label userName;
    @FXML
    private Button logOut;
    @FXML
    private ListView courses;
    @FXML
    private ListView testsToDo;
    @FXML
    private ListView testsDone;

    /**
     * Initializes the controller class.
     */
    
    
    ObservableList<String> courseList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Sätter userName label till studentens mail
        userName.setText(logic.getUser().getEMail());
        
        //Hämtar listan på kurser som en student har och visar den i Listviewn
        for (Course c : logic.getUser().getCourseList()) {
            courseList.add(c.getName());
            courses.setItems(courseList);
        }
    }    
      
    
}
