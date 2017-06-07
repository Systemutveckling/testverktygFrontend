/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.doingTest;

import com.logic.Logic;
import com.model.Test;
import com.model.Testresult;
import com.model.UserHasTest;
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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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
public class FXMLShowTestResultController implements Initializable {
    
    Logic logic = Logic.getInstanceOf();
    Server server = new Server();
    @FXML
    private Label showText, procentage,profileLabel;
    @FXML
    private Button showResult, myPage;
    @FXML
    private Label show, testName, testI, points, totalPoang, resultLabel, labelAboveScroll;
    @FXML
    private ListView resultView;
    @FXML
    private VBox vBox, resultBox;
    @FXML
    private ScrollPane scroll;

    /**
     * Initializes the controller class.
     */
    List<Testresult> result = server.getResultFromTest(logic.getUserStudent().getId(), logic.getPickedTest().getId());
    List<UserHasTest> userTest = server.getUserTests(logic.getUserStudent().getId());
    
    public void showResult(ActionEvent event) throws IOException {
        scroll.setVisible(true);
        resultBox.setVisible(true);
        testName.setText(logic.getPickedTest().getName());
        labelAboveScroll.setVisible(true);
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
            answer.setText("Ditt svar: " + resultTest.getAnswer() + "\t" + correction);
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
                procentage.setText(userHasTest.getGrade()+"%");                
            } 
        }
        showResult.setDisable(true);    
    }
    
    @FXML
    private void goToMyPage(ActionEvent event) throws IOException {
        if(logic.getUser().getAuthorization()==0){
        Parent root = FXMLLoader.load(getClass().getResource("/testverktygfrontend/studentPage/FXMLStudentPage.fxml"));
        Scene one = new Scene(root);
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stg.setScene(one);
        stg.show();
        }else{
         Parent root = FXMLLoader.load(getClass().getResource("/testverktygfrontend/teacherPage/FXMLTeacher.fxml"));
        Scene one = new Scene(root);
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stg.setScene(one);
        stg.show();   
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profileLabel.setText(logic.getUser().getEMail());
        if (logic.getPickedTest().getSeeResult() == 1) {
            showText.setVisible(false);
            showResult.setVisible(true);
        } else {
            showResult.setVisible(false);
            showText.setVisible(true);
            
        }
    }
    
}
