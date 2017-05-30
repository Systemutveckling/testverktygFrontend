/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend;

import com.logic.Logic;
import java.net.URL;
import java.util.ResourceBundle;
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
    private ListView<?> courses;
    @FXML
    private ListView<?> testsToDo;
    @FXML
    private ListView<?> testsDone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Sätter userName label till studentens mail
        userName.setText(logic.getUser().getEMail());
        
    }    
      
    
}
