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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLPreviewTestController implements Initializable {

    Logic l = Logic.getInstanceOf();
    Map<Integer, List<TextField>> textFieldList = new HashMap<>();

    @FXML
    private Label lblTestName;
    @FXML
    private ScrollPane scrollPaneQandA;
    @FXML
    private Button closePreviewTest;
    @FXML
    private AnchorPane QandAContainer;
    @FXML
    private VBox vBoxQandA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lblTestName.setText(l.getCreatedTempTest().getName());

        int questionIndex = 1;
        int answerIndex = 1;

        for (Question q : l.getCreatedTempTest().getQuestionList()) {

            List<TextField> tempList = new ArrayList<>();

            Label lblQuestionIndex = new Label("Fråga " + questionIndex);
            vBoxQandA.getChildren().add(lblQuestionIndex);

            TextField tfQuestion = new TextField(q.getQuestion());
            vBoxQandA.getChildren().add(tfQuestion);
            tempList.add(tfQuestion);

            Label lblImageUrl = new Label("Bild(url)");
            vBoxQandA.getChildren().add(lblImageUrl);
            VBox.setMargin(lblImageUrl, new Insets(10, 0, 0, 0));

            TextField tfImgUrl = new TextField(q.getImgUrl());
            vBoxQandA.getChildren().add(tfImgUrl);
            tempList.add(tfImgUrl);

            Label lblAnswer = new Label("Svarsalternativ");
            lblAnswer.setAlignment(Pos.TOP_LEFT);
            vBoxQandA.getChildren().add(lblAnswer);
            VBox.setMargin(lblAnswer, new Insets(10, 0, 0, 0));

            answerIndex = 1;
            for (Answer a : q.getAnswerList()) {

                TextField tfAnswer = new TextField(a.getAnswer());
                tfAnswer.setId("answerTextField");

                VBox.setMargin(tfAnswer, new Insets(0, 0, 10, 50));
                vBoxQandA.getChildren().add(tfAnswer);
                tempList.add(tfAnswer);
                answerIndex++;
            }

            textFieldList.put((questionIndex - 1), tempList);
            questionIndex++;

        }
    }

    private void saveChangesToTest() {

        int indexCounter = 0;

        for (Question q : l.getCreatedTempTest().getQuestionList()) {
            List<TextField> listWithTextFields = textFieldList.get(indexCounter);
            for (int i = 0; i < listWithTextFields.size(); i++) {
                switch (i) {
                    case 0:
                        q.setQuestion(listWithTextFields.get(i).getText());
                        break;
                    case 1:
                        q.setImgUrl(listWithTextFields.get(i).getText());
                        break;
                    default:
                        q.getAnswerList().get(i - 2).setAnswer(listWithTextFields.get(i).getText());
                        break;
                }
            }

            indexCounter++;
        }
    }

    @FXML
    private void closePreviewTest(ActionEvent event) {
        saveChangesToTest();
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stg.close();
    }

}
