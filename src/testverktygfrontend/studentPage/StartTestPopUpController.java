/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.studentPage;

import com.logic.Logic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        
        testName.setText("'' "+logic.getPickedTest().getName()+" ''");
        testTime.setText(logic.getPickedTest().getTimeLimit().toString());
    }    
    
}
