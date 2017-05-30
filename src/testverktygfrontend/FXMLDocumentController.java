package testverktygfrontend;

import com.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    
    Client client;
    
    @FXML
    private Label label;
    
    @FXML
    private Button btnStart;
    @FXML
    private Button btnAvbryt;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        Stage stage; 
        Parent root;

        if(event.getSource()== btnStart){
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Starting test");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnStart.getScene().getWindow());
        stage.showAndWait();
        }else {
            stage=(Stage)btnAvbryt.getScene().getWindow();
            stage.close();
        }
    }
    
    //Gör en scen som med controller, agerar som popup använder lambda
    //@Override
    /*public void start(final Stage primaryStage) {
    Button btn = new Button();
    btn.setText("Open Dialog");
        btn.setOnAction((ActionEvent event) -> {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.NONE);
            dialog.initOwner(primaryStage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Vill du starta provet?"));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        });
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        client = ClientBuilder.newClient();
        
        List<User> user =  client.target("http://localhost:8080/testverktygbackend/webapi/users")
            .request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>> (){});
       
            System.out.println(user.get(0).getEMail());
 
            
    }    
    
}
