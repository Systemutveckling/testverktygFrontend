/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend;

import com.model.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author hampus
 */
public class FXMLDocumentController implements Initializable {
    
    Client client;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
            
            
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        client = ClientBuilder.newClient();
        
        List<User> user =  client.target("http://localhost:8080/testverktygbackend/webapi/users")
            .request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>> (){});
       
            System.out.println(user.get(0).getEMail());
 
            
    }    
    
}
