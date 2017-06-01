/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.createtest.previewtest;

import com.logic.Logic;
import com.model.Answer;
import com.model.Question;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLPreviewTestController implements Initializable {

    Logic l = Logic.getInstanceOf();
    @FXML
    private VBox vBoxQandA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int questionIndex = 1;
        int answerIndex = 1;
        for (Question q : l.getCreatedTempTest().getQuestionList()) {
            Label lblQuestionIndex = new Label("Fråga " + questionIndex);
            vBoxQandA.getChildren().add(lblQuestionIndex);
            questionIndex++;

            Label lblQuestion = new Label(q.getQuestion());
            vBoxQandA.getChildren().add(lblQuestion);

            answerIndex = 1;
            for (Answer a : q.getAnswerList()) {
              
                Label lblAnswer = new Label(answerIndex + ". " + a.getAnswer());
                vBoxQandA.getChildren().add(lblAnswer);
                answerIndex++;
            }
        }
    }

}
