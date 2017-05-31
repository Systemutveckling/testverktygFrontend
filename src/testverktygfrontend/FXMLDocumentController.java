/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend;

import com.logic.Logic;
import com.model.Course;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


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
    private TextField password;
    
    
    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        if(logic.login(username.getText(), password.getText()).getAuthorization() == 0){
            System.out.println("Elev!");
        } else if(logic.login(username.getText(), password.getText()).getAuthorization() == 1) {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLTeacher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        }  
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
