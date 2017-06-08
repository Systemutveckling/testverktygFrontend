/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.teacherPage;

import com.logic.Logic;
import com.model.Testresult;
import com.model.User;
import com.model.UserHasTest;
import com.serverconnection.Server;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramonachantaf
 */
public class FXMLShowStudentResultController implements Initializable {

    Logic logic = Logic.getInstanceOf();
    Server server = new Server();
    @FXML
    private VBox vBoxTop;
    @FXML
    private Label confirmation;
   
    @FXML
    private Label showText;
    @FXML
    private Label show;
    @FXML
    private Button myPage;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vBox;
    @FXML
    private HBox topHBox;
    @FXML
    private Label userLabelImg;
    @FXML
    private Label profileLabel;
    @FXML
    private Label studentLabel,studentName;
    @FXML
    private Label userName;
    @FXML
    private VBox resultBox;
    @FXML
    private Label testI;
    @FXML
    private Label testName;
    @FXML
    private Label totalPoang;
    @FXML
    private Label points;
    @FXML
    private Label resultLabel;
    @FXML
    private Label procentage;

    /**
     * Initializes the controller class.
     */
     ObservableList<User> students = FXCollections.observableArrayList();
       List<Testresult> result;
        List<UserHasTest> userTest;


    @FXML
    private void goBack(ActionEvent event) throws IOException {
        //Parent root = FXMLLoader.load(getClass().getResource("/testverktygfrontend/teacherPage/FXMLTeacher.fxml"));
        //Scene one = new Scene(root);
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //stg.setScene(one);
        stg.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
      
      if(logic.getUserStudent()==null){
           result = server.getResultFromTest(logic.getUser().getId(), logic.getPickedTest().getId());
             userTest = server.getUserTests(logic.getUser().getId());
               
    }else{
             result = server.getResultFromTest(logic.getUserStudent().getId(), logic.getPickedTest().getId());
             userTest = server.getUserTests(logic.getUserStudent().getId()); 
            
             studentName.setText(logic.getUserStudent().getEMail());
             
      }
        
       profileLabel.setText(logic.getUser().getEMail());
     
      testName.setText(logic.getPickedTest().getName());
    
        int count = 0;
        int questionCounter = 1;
        String correction = "";

        for (Testresult resultTest : result) {
            Label question = new Label();
            Label answer = new Label();
            Label empty = new Label();
            if (resultTest.getIsCorrect() == 1) {
                correction = "Rätt";
                answer.setTextFill(GREEN);
            } else {
                correction = "Fel";
                answer.setTextFill(RED);
            }

            question.setText("Fråga " + questionCounter + ". " + resultTest.getQuestion());
            answer.setText("Ditt svar: " + resultTest.getAnswer()+ "  "+correction);
            empty.setText("");
            answer.setFont(new Font("Quicksand", 13));
            question.setFont(new Font("Quicksand", 13));

            vBox.getChildren().add(question);
            vBox.getChildren().add(answer);
            vBox.getChildren().add(empty);

            if (resultTest.getIsCorrect() == 1) {
                count++;
            }
            questionCounter++;
        }
        points.setText(count + "/" + result.size());
        for (UserHasTest userHasTest : userTest) {
            
            if (logic.getPickedTest().getId().equals(userHasTest.getTestId().getId())) {
                procentage.setText(userHasTest.getGrade());                
            } 
        }

    
    }    

   
    
}
