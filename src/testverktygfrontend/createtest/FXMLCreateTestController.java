/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.createtest;

import com.logic.Logic;
import com.model.Answer;
import com.model.Question;
import com.model.Test;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLCreateTestController implements Initializable {

    private Logic l = Logic.getInstanceOf();

    private List<TextField> answerAlternativesList;
    private List<RadioButton> correctAnswerRadioButtonList;

    private List qandaList;
    private int questionCount;

    @FXML
    private TextField txtFieldTestName;
    @FXML
    private TextArea txtAreaTestDescription;
    @FXML
    private Slider sliderTimeLimit;
    @FXML
    private Label lblTime;
    @FXML
    private ToggleGroup toggleGroupSeeResult;
    @FXML
    private Button btnSaveTest;
    @FXML
    private Button btnPreviewTest;
    @FXML
    private Label lblQuestionCount;
    @FXML
    private TextField txtFieldQuestion;
    @FXML
    private TextField txtFieldImageUrl;
    @FXML
    private ToggleGroup toggleGroupCorrectAnswer;
    @FXML
    private Button btnNewAnswerAltenative;
    @FXML
    private Button btnNewQuestion;
    @FXML
    private VBox vBoxAnswerAltenatives;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        toggleGroupCorrectAnswer = new ToggleGroup();
        questionCount = 1;

        lblQuestionCount.setText(
                "Fråga " + questionCount);
        answerAlternativesList = new ArrayList<>();
        correctAnswerRadioButtonList = new ArrayList<>();
        qandaList = new ArrayList();
        ActionEvent event = null;

        newAnswerAlternative(event);

        sliderTimeLimit.valueProperty()
                .addListener((obs, oldval, newVal)
                        -> sliderTimeLimit.setValue(newVal.intValue()));
        lblTime.textProperty()
                .bind(sliderTimeLimit.valueProperty().asString());
    }

    @FXML
    private void saveTest(ActionEvent event) {
        //Skapar ett nytt testobjekt från användarens inmatade värden
        Test test = new Test();
        test.setName(txtFieldTestName.getText());
        test.setDescription(txtAreaTestDescription.getText());
        String substringTime = lblTime.getText().substring(0, lblTime.getText().indexOf("."));
        test.setTimeLimit(Integer.parseInt(substringTime));
        RadioButton selectedRadioButton = (RadioButton) toggleGroupSeeResult.getSelectedToggle();
        if (selectedRadioButton.getText().equals("Ja")) {
            test.setSeeResult(Short.valueOf("1"));
        } else {
            test.setSeeResult(Short.valueOf("0"));
        }

        // Sparar den sista frågan användaren skrev in    
        saveQuestionsAndAnswers();

        // Sparar allt detta till databasen
        l.saveCreatedTestToDb(test, qandaList);
        
        // Rensa alla inmatningar
        questionCount = 1;
        lblQuestionCount.setText("Fråga " + questionCount);
        txtFieldQuestion.clear();
        txtFieldImageUrl.clear();
        txtFieldTestName.clear();
        txtAreaTestDescription.clear();
        sliderTimeLimit.adjustValue(0);
        vBoxAnswerAltenatives.getChildren().clear();
        answerAlternativesList.clear();
        correctAnswerRadioButtonList.clear();
        newAnswerAlternative(event);
    }

    private void saveQuestionsAndAnswers() {
        Question question = new Question();
        question.setQuestion(txtFieldQuestion.getText());
        question.setImgUrl(txtFieldImageUrl.getText());
        qandaList.add(question);

        int correctAnswerRadioButtonIndex = 0;

        for (int i = 0; i < correctAnswerRadioButtonList.size(); i++) {
            if (correctAnswerRadioButtonList.get(i).isSelected()) {
                correctAnswerRadioButtonIndex = i;
            }
        }

        for (int j = 0; j < answerAlternativesList.size(); j++) {
            Answer answer = new Answer();
            answer.setAnswer(answerAlternativesList.get(j).getText());
            if (j == correctAnswerRadioButtonIndex) {
                answer.setIsCorrect(Short.valueOf("1"));
            } else {
                answer.setIsCorrect(Short.valueOf("0"));
            }

            qandaList.add(answer);

        }

        System.out.println("Size of qandaList: " + qandaList.size());
    }

    @FXML
    private void previewTest(ActionEvent event) {
    }

    @FXML
    private void newAnswerAlternative(ActionEvent event) {

        if (answerAlternativesList.size() < 6) {
            TextField answerAltenative = new TextField();
            answerAltenative.setPrefWidth(355);
            answerAlternativesList.add(answerAltenative);

            RadioButton correctAnswerRadioButton = new RadioButton();
            correctAnswerRadioButton.setToggleGroup(toggleGroupCorrectAnswer);
            correctAnswerRadioButtonList.add(correctAnswerRadioButton);

            int size = answerAlternativesList.size();

            HBox hbox = new HBox();
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);

            hbox.getChildren().addAll(answerAlternativesList.get(size - 1), correctAnswerRadioButtonList.get(size - 1));
            vBoxAnswerAltenatives.getChildren().add(hbox);
        } else {
            System.out.println("Max 6 svarsalternativ, annars blir GUI:t fult! :)");
        }
    }

    @FXML
    private void newQuestion(ActionEvent event) {
        saveQuestionsAndAnswers();
        questionCount++;
        lblQuestionCount.setText("Fråga " + questionCount);
        txtFieldQuestion.clear();
        txtFieldImageUrl.clear();
        vBoxAnswerAltenatives.getChildren().clear();
        answerAlternativesList.clear();
        correctAnswerRadioButtonList.clear();
        newAnswerAlternative(event);

    }

}
