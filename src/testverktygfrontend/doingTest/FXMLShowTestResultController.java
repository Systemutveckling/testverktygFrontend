/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.doingTest;

import com.logic.Logic;
import com.model.Test;
import com.model.Testresult;
import com.serverconnection.Server;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class FXMLShowTestResultController implements Initializable {

    Logic logic = Logic.getInstanceOf();
    Server server = new Server();
    @FXML
    private Label showText;
    @FXML
    private Button showResult;
    @FXML
    private Label show;
    @FXML
    private ListView resultView;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Testresult> resultList = FXCollections.observableArrayList();
    List<Testresult> result = server.getResultFromTest(logic.getUser().getId(), logic.getPickedTest().getId());
     
     @FXML
    private void showResult(ActionEvent event) throws IOException {
//          System.out.println(logic.getResultFromTest().getQuestion());
//           show.setText(result.get(0).getQuestion());
System.out.println(result.size());
              for (Testresult resultTest : result) {
               
                        resultList.add(resultTest);
                        
                    }
             
              resultView.setItems(resultList);
               resultView.setVisible(true);
                }
            
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(logic.getPickedTest().getSeeResult()==0){
             System.out.println("---"+logic.getPickedTest().getSeeResult());
//            show.setText(logic.getPickedTest().getSeeResult().toString());
            showText.setVisible(false);
             showResult.setVisible(true);
           
            
        }else{
             showResult.setVisible(false);
          showText.setVisible(true);
            
        }
    }    
    
}
