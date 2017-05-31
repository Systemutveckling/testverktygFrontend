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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button btnNewQuestion;
    @FXML
    private VBox vBoxAnswerAltenatives;

    @FXML
    private Button btnSaveQuestion;
    @FXML
    private Button btnRemoveAnswerAltenative;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

        // Sparar den sista frågan användaren skrev in

        // Sparar allt detta till databasen
        l.saveCreatedTestToDb(l.getTest());

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
        answerAltenativesContainer.clear();
        newAnswerAlternative(event);
    }

    private void saveTest() {
        
        l.setTest(new Test());
        //Skapar ett nytt testobjekt från användarens inmatade värden
        l.getTest().setName(txtFieldTestName.getText());
        l.getTest().setDescription(txtAreaTestDescription.getText());
        String substringTime = lblTime.getText().substring(0, lblTime.getText().indexOf("."));
        l.getTest().setTimeLimit(Integer.parseInt(substringTime));
        RadioButton selectedRadioButton = (RadioButton) toggleGroupSeeResult.getSelectedToggle();
        if (selectedRadioButton.getText().equals("Ja")) {
            l.getTest().setSeeResult(Short.valueOf("1"));
        } else {
            l.getTest().setSeeResult(Short.valueOf("0"));
        }
        
        List<Question> tempQuestionList = new ArrayList<>();
        l.getTest().setQuestionList(tempQuestionList);
    }

    private void saveQuestionsAndAnswers() {

        Question question = new Question();
        question.setQuestion(txtFieldQuestion.getText());
        question.setImgUrl(txtFieldImageUrl.getText());
        l.getTest().getQuestionList().add(question);
        
        List<Answer> tempListAnswer = new ArrayList<>();
        l.getTest().getQuestionList().get(questionCount-1).setAnswerList(tempListAnswer);

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

            l.getTest().getQuestionList().get(questionCount-1).getAnswerList().add(answer);

        }

    }

    @FXML
    private void previewTest(ActionEvent event) throws IOException {
        
        Stage s = new Stage();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("previewtest/FXMLPreviewTest.fxml")));
        s.setScene(sc);
        s.show();
        
        System.out.println("Test" + l.getTest().getName());
        
        for(Question q:l.getTest().getQuestionList()){
            System.out.println("Q: " + q.getQuestion());
            for(Answer a: q.getAnswerList()){
                System.out.println("A: " + a.getAnswer());
            }
        }
/*
        String contentText = "";
        String seeResult;
        if(test.getSeeResult()== 1){
            seeResult = "Ja";
        }
        else{
            seeResult = "Nej";
        }
        
        contentText += "Namn: " + test.getName()
                + "\nBeskrivning: " + test.getDescription()
                + "\nTidsgräns: " + test.getTimeLimit()
                + "\nSe resultatet direkt: " + seeResult +  "\n";
        int questionIndex = 1;
        int answerIndex = 1;

        for (Object obj : qandaList) {
            if (obj instanceof Question) {
                Question q = (Question) obj;
                contentText += "\n" + "Fråga " + questionIndex + "\n" + q.getQuestion() + "\n";
                questionIndex++;
                answerIndex = 1;
            } else {
                Answer a = (Answer) obj;
                contentText += answerIndex + ". " + a.getAnswer() + "\n";
                answerIndex++;
            }
        }
*/
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

            hbox.getChildren().addAll(correctAnswerRadioButtonList.get(size - 1),answerAlternativesList.get(size - 1));
            
            answerAltenativesContainer.add(hbox);
            vBoxAnswerAltenatives.getChildren().add(hbox);
        } else {
            System.out.println("Max 6 svarsalternativ, annars blir GUI:t fult! :)");
        }
    }
    
    @FXML
    private void removeAnswerAlternative(ActionEvent event) {
        // Tar bort senaste svarsalternativet både från GUI:t och från listorna
        int sizeContainer = answerAltenativesContainer.size();
        vBoxAnswerAltenatives.getChildren().remove(answerAltenativesContainer.get(sizeContainer - 1));
        answerAltenativesContainer.remove(sizeContainer-1);
        
        // Tar bort textfältet och radioknappen från sina listorna så att man ska kunna lägga till igen
        int sizeAnswerTextField = answerAlternativesList.size();
        answerAlternativesList.remove(sizeAnswerTextField-1);
        
        int sizeIsCorrect = correctAnswerRadioButtonList.size();
        correctAnswerRadioButtonList.remove(sizeIsCorrect-1);
    }

    @FXML
    private void newQuestion(ActionEvent event) {
       
        questionCount++;
        lblQuestionCount.setText("Fråga " + questionCount);
        txtFieldQuestion.clear();
        txtFieldImageUrl.clear();
        vBoxAnswerAltenatives.getChildren().clear();
        answerAlternativesList.clear();
        correctAnswerRadioButtonList.clear();
        newAnswerAlternative(event);
        //previewTest(event);

    }

    @FXML
    private void saveQuestion(ActionEvent event) {
         if (l.getTest() == null) {
            saveTest();
        }
        saveQuestionsAndAnswers();
    }
   

    

}
