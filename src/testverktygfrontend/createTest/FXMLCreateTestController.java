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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLCreateTestController implements Initializable {

    private Logic l = Logic.getInstanceOf();

    private List<TextField> answerAlternativesList;
    private List<RadioButton> correctAnswerRadioButtonList;
    private List<HBox> answerAltenativesContainer;

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
    private Label lblQuestionCount;
    @FXML
    private TextField txtFieldQuestion;
    @FXML
    private TextField txtFieldImageUrl;

    private ToggleGroup toggleGroupCorrectAnswer;
    @FXML
    private Button btnNewAnswerAlternative;
    @FXML
    private VBox vBoxAnswerAltenatives;

    @FXML
    private Button btnSaveQuestion;
    @FXML
    private Button btnRemoveAnswerAltenative;
    @FXML
    private Label lblLeftWarning;
    @FXML
    private Button btnPreviewTest;
    @FXML
    private Label lblRightWarning;
    @FXML
    private Label lblCreateTestCourseName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        l.setCreatedTempTest(new Test());
        List<Question> tempQuestionList = new ArrayList<>();
        l.getCreatedTempTest().setQuestionList(tempQuestionList);

        lblCreateTestCourseName.setText("Skapa ett test för kursen " + l.getChoosenCourseToCreateTestTo().getName());
        
        toggleGroupCorrectAnswer = new ToggleGroup();
        questionCount = 1;

        lblQuestionCount.setText(
                "Fråga " + questionCount);
        answerAltenativesContainer = new ArrayList<>();
        answerAlternativesList = new ArrayList<>();
        correctAnswerRadioButtonList = new ArrayList<>();

        ActionEvent event = null;

        newAnswerAlternative(event);

        sliderTimeLimit.valueProperty()
                .addListener((obs, oldval, newVal)
                        -> sliderTimeLimit.setValue(newVal.intValue()));
        lblTime.textProperty()
                .bind(sliderTimeLimit.valueProperty().asString());
    }

    @FXML
    private void saveTestToDb(ActionEvent event) {
        
        if (l.getCreatedTempTest().getQuestionList().isEmpty()) {
            lblLeftWarning.setText("Du måste spara minst en fråga innan du kan spara testet");
        } else if (txtFieldTestName.getText().isEmpty()) {
             lblLeftWarning.setText("Du måste ange namn på testet för att få spara");
        } else if (sliderTimeLimit.getValue() == 0) {
             lblLeftWarning.setText("Ska studenterna verkligen få så lite tid på sig att svara på testet");
        } else {
            Alert alert = new Alert(null);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Är du säker på att du vill spara testet?");

            ButtonType btnYes = new ButtonType("Ja");
            ButtonType btnNo = new ButtonType("Nej");

            alert.getButtonTypes().setAll(btnYes, btnNo);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                  getClass().getResource("createtest.css").toExternalForm());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == btnYes) {

                // Spara testet en sista gång om man skulle ändrat något
                saveTest();

                // Sparar allt till databasen
                int testId = l.saveCreatedTestToDb(l.getCreatedTempTest());

                // Sparar även testet så man vet vilken kurs och vilken student det tillhör
                l.addCreatedTestToCourseAndUser(l.getChoosenCourseToCreateTestTo().getId(), testId);

                // Stänger sidan när man skapat ett test
                Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stg.close();
                // Rensa alla inmatningar
                /*questionCount = 1;
                lblQuestionCount.setText("Fråga " + questionCount);
                txtFieldQuestion.clear();
                txtFieldImageUrl.clear();
                txtFieldTestName.clear();
                txtAreaTestDescription.clear();
                sliderTimeLimit.adjustValue(0);
                vBoxAnswerAltenatives.getChildren().clear();
                answerAlternativesList.clear();
                correctAnswerRadioButtonList.clear();
                answerAltenativesContainer.clear();
                newAnswerAlternative(event);*/
            }
        }
    }

    private void saveTest() {

        //Skapar ett nytt testobjekt från användarens inmatade värden
        l.getCreatedTempTest().setName(txtFieldTestName.getText());
        l.getCreatedTempTest().setDescription(txtAreaTestDescription.getText());
        String substringTime = lblTime.getText().substring(0, lblTime.getText().indexOf("."));
        l.getCreatedTempTest().setTimeLimit(Integer.parseInt(substringTime));
        RadioButton selectedRadioButton = (RadioButton) toggleGroupSeeResult.getSelectedToggle();
        if (selectedRadioButton.getText().equals("Ja")) {
            l.getCreatedTempTest().setSeeResult(Short.valueOf("1"));
        } else {
            l.getCreatedTempTest().setSeeResult(Short.valueOf("0"));
        }
    }

    private void saveQuestionsAndAnswers() {

        Question question = new Question();
        question.setQuestion(txtFieldQuestion.getText());
        question.setImgUrl(txtFieldImageUrl.getText());
        l.getCreatedTempTest().getQuestionList().add(question);

        List<Answer> tempListAnswer = new ArrayList<>();
        l.getCreatedTempTest().getQuestionList().get(questionCount - 1).setAnswerList(tempListAnswer);

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

            l.getCreatedTempTest().getQuestionList().get(questionCount - 1).getAnswerList().add(answer);

        }

    }

    @FXML
    private void previewTest(ActionEvent event) throws IOException {

        if (l.getCreatedTempTest().getQuestionList().isEmpty()) {
            lblLeftWarning.setText("Du måste spara minst en fråga innan du kan granska testet");
        } else {
            lblLeftWarning.setText("");
            Stage s = new Stage();
            Scene sc = new Scene(FXMLLoader.load(getClass().getResource("previewtest/FXMLPreviewTest.fxml")));
            s.setScene(sc);
            s.show();
        }

    }

    @FXML
    private void newAnswerAlternative(ActionEvent event) {

        if (answerAlternativesList.size() < 6) {
            lblRightWarning.setText("");
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

            hbox.getChildren().addAll(correctAnswerRadioButtonList.get(size - 1), answerAlternativesList.get(size - 1));

            answerAltenativesContainer.add(hbox);
            vBoxAnswerAltenatives.getChildren().add(hbox);
        } else {
            lblRightWarning.setText("Max 6 svarsalternativ");
        }
    }

    @FXML
    private void removeAnswerAlternative(ActionEvent event) {
        int sizeContainer = answerAltenativesContainer.size();

        if (sizeContainer > 1) {
            lblRightWarning.setText("");
            // Tar bort senaste svarsalternativet både från GUI:t och från listorna
            vBoxAnswerAltenatives.getChildren().remove(answerAltenativesContainer.get(sizeContainer - 1));
            answerAltenativesContainer.remove(sizeContainer - 1);

            // Tar bort textfältet och radioknappen från sina listorna så att man ska kunna lägga till igen
            int sizeAnswerTextField = answerAlternativesList.size();
            answerAlternativesList.remove(sizeAnswerTextField - 1);

            int sizeIsCorrect = correctAnswerRadioButtonList.size();
            correctAnswerRadioButtonList.remove(sizeIsCorrect - 1);
        } else {
            lblRightWarning.setText("Måste finnas minst ett svarsalternativ");
        }
    }

    private void newQuestion(ActionEvent event) {

        questionCount++;
        lblQuestionCount.setText("Fråga " + questionCount);
        txtFieldQuestion.clear();
        txtFieldImageUrl.clear();
        vBoxAnswerAltenatives.getChildren().clear();
        answerAlternativesList.clear();
        correctAnswerRadioButtonList.clear();
        newAnswerAlternative(event);
        lblRightWarning.setText("");
        //previewTest(event);

    }

    @FXML
    private void saveQuestion(ActionEvent event) {
        boolean someAnswerAltenativeIsEmpty = false;
        for (TextField t : answerAlternativesList) {
            if (t.getText().isEmpty()) {
                someAnswerAltenativeIsEmpty = true;
                break;
            }
        }

        if (txtFieldQuestion.getText().isEmpty()
                || someAnswerAltenativeIsEmpty) {
            lblRightWarning.setText("Frågan och svarsalternativen får inte vara tomma");
        } else {

            saveQuestionsAndAnswers();
            newQuestion(event);
        }
    }

}
