/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend;

import com.logic.Logic;
import com.model.User;
import com.serverconnection.Server;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 *
 * @author hampus
 */
public class FXMLDocumentController implements Initializable {
    
    Logic logic = Logic.getInstanceOf();
    @FXML
    private Button button;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private Label userLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label wrongCredentials;
    
    
    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        if(logic.login(username.getText(), password.getText()).getAuthorization() == 0){
            Parent root = FXMLLoader.load(getClass().getResource("studentPage/FXMLStudentPage.fxml"));
            Scene one = new Scene(root);
            Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logic.setStudentStage(stg);
            stg.setScene(one);
            stg.show();
            System.out.println("Elev!");
        } else if(logic.login(username.getText(), password.getText()).getAuthorization() == 1) {
            Parent root = FXMLLoader.load(getClass().getResource("teacherPage/FXMLTeacher.fxml"));
            Scene one = new Scene(root);
            Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stg.setScene(one);
            stg.show();
            System.out.println("Lärare!");
        } else{
            wrongCredentials.setText("Fel lösernord eller användarnamn");
        }  
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
}
