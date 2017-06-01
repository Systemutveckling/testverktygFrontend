/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.doingTest;

import com.logic.Logic;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ramonachantaf
 */
public class FXMLShowTestResultController implements Initializable {

    Logic logic = Logic.getInstanceOf();
    @FXML
    private Label showText;
    @FXML
    private Button showResult;
    @FXML
    private Label show;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(logic.getPickedTest().getSeeResult()==0){
             System.out.println("---"+logic.getPickedTest().getSeeResult());
            show.setText(logic.getPickedTest().getSeeResult().toString());
            showText.setVisible(false);
             showResult.setVisible(true);
           
            
        }else{
             showResult.setVisible(false);
          showText.setVisible(true);
            
        }
    }    
    
}
