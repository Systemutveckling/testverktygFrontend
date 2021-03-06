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
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    @FXML
    private ImageView questionImage;

    Logic logic = Logic.getInstanceOf();
    Test test = logic.getPickedTest();

    private ToggleGroup toggleGroupCorrectAnswer = new ToggleGroup();
    private List<RadioButton> correctAnswerRadioButtonList = new ArrayList();
    private List<HBox> answerAltenativesContainer = new ArrayList();
    private HBox hBoxAnswerAltenatives = new HBox();
    private RadioButton correctAnswerRadioButton;
    private List<Studentanswer> studentAnswer = new ArrayList();
    private boolean testDone = false;
    public Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(1000),
            ae -> counterLogic()));

    int questionId = 0;

    int secondsLeft = logic.getPickedTest().getTimeLimit();

    //int secondsLeft = 6;

    @FXML
    private Button quittest;
    @FXML
    private ImageView left;
    @FXML
    private ImageView right;
    @FXML
    private Label clockImg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentAnswer.clear();
        nameOnTest.setText(test.getName());

        counterLogic();
        startCounter();

        showQuestion();
        showAnswer();
        quittest.setVisible(false);

        if (questionId == 0) {
            left.setVisible(false);
        }

    }

    public void startCounter() {

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
            timeline.stop();

        } else {

            hours = secondsLeft / 3600;
            minutes = (secondsLeft % 3600) / 60;
            seconds = secondsLeft % 60;
            countDownLabel.setText(String.valueOf(hours + " h  " + minutes + " min  " + seconds + " sek "));
        }
        return secondsLeft;

    }

    @FXML
    private void showTestResult(ActionEvent event) throws IOException {
        timeline.stop();
        savePickedAnswer();
        logic.setUserStudent(logic.getUser());
        List<Studentanswer> list = timeIsUpList(studentAnswer);
        studentAnswer = list;
        testComplete();
        Stage stg = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("FXMLShowTestResult.fxml")));
        stg.setScene(sc);
        stg.show();

    }

    @FXML
    private void backward(MouseEvent event) {
        testDone = false;
        questionId--;
        if (questionId <= 0) {
            left.setVisible(false);
        } else {
            quittest.setVisible(false);
            right.setVisible(true);
        }

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
    private void forward(MouseEvent event) throws IOException {
        questionId++;

        if (questionId > 0) {
            left.setVisible(true);
        }
        System.out.println("questionListSize = " + test.getQuestionList().size());

        System.out.println("questionListSize = " + questionId);

        if (test.getQuestionList().size() == (questionId + 1)) {
            quittest.setVisible(true);
            quittest.setText("Skicka in provet");

            right.setVisible(false);
        } else {

            quittest.setVisible(false);
        }

        try {
            savePickedAnswer();
            showQuestion();
            showAnswer();

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
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
        if (test.getQuestionList().get(questionId).getImgUrl().isEmpty()) {
            questionImage.setVisible(false);
        } else {

            questionImage.setVisible(true);
            Image image = null;

            try {
                System.out.println("Är vi här?");
                System.out.println(test.getQuestionList().get(questionId).getImgUrl());
                image = new Image(test.getQuestionList().get(questionId).getImgUrl());
            } catch (Exception e) {
                System.out.println(e);

                image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBts1WDuUyrYVWju_bZU_12feDt-1DkfcjwFYKP-N4JeQ6OfB-8A");

            }

            questionImage.setImage(image);

        }
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

        int percent = (int) ((amountOfCorrects * 100.0f) / test.getQuestionList().size());
        return Integer.toString(percent);
    }

    public void testComplete() {
        
        List<UserHasTest> userTests = logic.getUserTests(logic.getUser().getId());
        for (UserHasTest uht : userTests) {
            if (uht.getTestId().getId() == test.getId()) {
                uht.setGrade(gradeCalc());
                uht.setIsDone((short) 1);
                logic.updateStudentTestStatus(uht);
                break;
            }
        }
        for (Studentanswer sa : studentAnswer) {
            logic.saveStudentAnswer(sa);
        }
        studentAnswer.clear();
    }

    public List<Studentanswer> timeIsUpList(List<Studentanswer> studentList) {
        List<Studentanswer> toReturnList = studentList;
        if (test.getQuestionList().size() != studentList.size()) {
            System.out.println("kommer in här");
            for (int i = toReturnList.size(); i < test.getQuestionList().size(); i++) {
                for (Answer a : test.getQuestionList().get(i).getAnswerList()) {
                    if (a.getIsCorrect() == 0) {
                        Studentanswer sa = new Studentanswer();
                        sa.setAnswerId(a);
                        sa.setQuestionId(test.getQuestionList().get(i));
                        sa.setUserId(logic.getUser());
                        toReturnList.add(sa);
                        break;
                    }
                }
            }
        }
        return toReturnList;
    }

}
