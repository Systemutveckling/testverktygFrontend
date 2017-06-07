/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.studentPage;

import com.logic.Logic;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramonachantaf
 */
public class StartTestPopUpController implements Initializable {

    Logic logic = Logic.getInstanceOf();
    @FXML
    private Label testName;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnAvbryt;
    @FXML
    private Label testTime;
    @FXML
    private Label startTestLabel;

    @FXML
    private Button result;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void cancelPop(ActionEvent event) {
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stg.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

            int time =  logic.getPickedTest().getTimeLimit();
            
            
           int hours = time / 3600;
           int minutes = (time % 3600) / 60;
            
        testName.setText("'' "+logic.getPickedTest().getName()+" ''");
        
        if(hours == 0){
        
        testTime.setText(String.valueOf(minutes + " minuter"));
        } else {
        
        testTime.setText(hours + " t " + minutes + " min");
        }
    }
    
    @FXML
    private void startTest(ActionEvent event) throws IOException {
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/testverktygfrontend/doingTest/FXMLDoingTest.fxml")));
        stg.setScene(sc);
        logic.getStudentStage().close();
        stg.show();
    }
    }



