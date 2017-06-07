/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.doingTest;

import com.logic.Logic;
import com.model.Answer;
import com.model.Question;
import com.model.Studentanswer;
import com.model.Test;
import com.model.UserHasTest;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Andreas Vettefors (contact@vettefors.se)
 */
public class FXMLDoingTestController implements Initializable {

    @FXML
    private Label nameOnTest;
    @FXML
    private Label label;
    @FXML
    private Label questionsLeft;
    @FXML
    private Label arrowLeft;
    @FXML
    private Label arrowRight;
    @FXML
    private Label countDownLabel;
    @FXML
    private VBox vbox;
    Logic logic = Logic.getInstanceOf();
    Test test = logic.getPickedTest();

    private ToggleGroup toggleGroupCorrectAnswer = new ToggleGroup();
    private List<RadioButton> correctAnswerRadioButtonList = new ArrayList();
    private List<HBox> answerAltenativesContainer = new ArrayList();
    private HBox hBoxAnswerAltenatives = new HBox();
    private RadioButton correctAnswerRadioButton;
    private List<Studentanswer> studentAnswer = new ArrayList();
    private boolean testDone = false;

    int questionId = 0;

    int secondsLeft = test.getTimeLimit();
    @FXML
    private Button quittest;
    @FXML
    private ImageView left;
    @FXML
    private ImageView right;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameOnTest.setText(test.getName());

        startCounter();
        counterLogic();
        
        showQuestion();
        showAnswer();
        quittest.setVisible(false);
        
        
    }

    public void startCounter() {

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> counterLogic()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public int counterLogic() {

        secondsLeft--;

        int hours;
        int minutes;
        int seconds;

        if (secondsLeft <= 0) {
            countDownLabel.setText("Tyvärr tiden är slut");
            quittest.setVisible(true);
            quittest.setText("Avsluta test");
            left.setVisible(false);
            right.setVisible(false);
            for(Studentanswer sa : studentAnswer){
                
            }
            quittest.setOnAction((event)->{
            
            });

        } else {

            hours = secondsLeft / 3600;
            minutes = (secondsLeft % 3600) / 60;
            seconds = secondsLeft % 60;
            countDownLabel.setText(String.valueOf("Hours = " + hours + " Minutes = " + minutes + " Seconds = " + seconds));
        }
        return secondsLeft;

    }

    @FXML
    private void backward(MouseEvent event) {
        questionId--;

        if (questionId >= 0 && questionId < test.getQuestionList().size()) {

            showQuestion();
            showAnswer();

        }
        if (studentAnswer.size() > 0) {
            for (RadioButton rb : correctAnswerRadioButtonList) {
                for (Studentanswer sa : studentAnswer) {
                    if (rb.getText().equals(sa.getAnswerId().getAnswer())) {
                        rb.setSelected(true);
                    }
                }
            }
            studentAnswer.remove((studentAnswer.size() - 1));
        }

    }

    @FXML
    private void forward(MouseEvent event) {
        questionId++;
        if (testDone) {

        } else {
            savePickedAnswer();
            try {
                showQuestion();
                showAnswer();
            } catch (Exception e) {
                List<UserHasTest> userTests = logic.getUserTests(logic.getUser().getId());
                for (UserHasTest uht : userTests) {
                    if (uht.getTestId().getId() == test.getId()) {
                        uht.setGrade(gradeCalc());
                        uht.setIsDone((short) 1);
                        logic.updateStudentTestStatus(uht);
                    }
                }
                System.out.println("!!");
                System.out.println("VISA TESTRESULTAT");
                System.out.println("!!");
                for (Studentanswer sa : studentAnswer) {
                    //logic.saveStudentAnswer(sa);
                }
            }

        }

    }

    public void savePickedAnswer() {
        Studentanswer sa = new Studentanswer();
        int answerId = 0;
        for (int i = 0; i < correctAnswerRadioButtonList.size(); i++) {
            if (correctAnswerRadioButtonList.get(i).isSelected()) {
                String string = correctAnswerRadioButtonList.get(i).getStyleClass().get(1);
                String stringSplit[] = string.split("radio-button");

                String split = stringSplit[0];

                answerId = Integer.parseInt(split);
            }
        }
        for (Question q : test.getQuestionList()) {
            for (Answer a : q.getAnswerList()) {
                if (a.getId() == answerId) {
                    sa.setAnswerId(a);
                    sa.setQuestionId(q);
                    sa.setUserId(logic.getUser());
                    studentAnswer.add(sa);
                }
            }
        }

        if (questionId == test.getQuestionList().size()) {

            testDone = true;
        }

    }

    public void showQuestion() {

        label.setText(test.getQuestionList().get(questionId).getQuestion());
        questionsLeft.setText(String.valueOf(questionId + 1) + "/" + test.getQuestionList().size());

    }

    public void showAnswer() {

        correctAnswerRadioButtonList.clear();
        vbox.getChildren().clear();
        List<Answer> answer = test.getQuestionList().get(questionId).getAnswerList();

        for (int i = 0; i < answer.size(); i++) {
            correctAnswerRadioButton = new RadioButton();

            correctAnswerRadioButton.setToggleGroup(toggleGroupCorrectAnswer);

            //int size = correctAnswerRadioButtonList.size();
            String id = String.valueOf(answer.get(i).getId());
            correctAnswerRadioButton.getStyleClass().add(id);
            correctAnswerRadioButton.setText(answer.get(i).getAnswer());
            vbox.setSpacing(20);
            vbox.setAlignment(Pos.CENTER_LEFT);
            correctAnswerRadioButtonList.add(correctAnswerRadioButton);

            //vbox.getChildren().add(correctAnswerRadioButtonList.get(size - 1));
            vbox.getChildren().add(correctAnswerRadioButton);
        }

    }

    public String gradeCalc() {
        int amountOfCorrects = 0;
        for (Studentanswer sa : studentAnswer) {
            if (sa.getAnswerId().getIsCorrect() == 1) {
                amountOfCorrects++;
            }
        }

        int percent = (int) ((amountOfCorrects * 100.0f) / studentAnswer.size());

        return Integer.toString(percent);
    }
}
