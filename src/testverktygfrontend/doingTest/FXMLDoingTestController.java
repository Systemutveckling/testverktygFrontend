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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    Test test = Logic.getInstanceOf().getPickedTest();
        
        private ToggleGroup toggleGroupCorrectAnswer = new ToggleGroup();
        private List<RadioButton> correctAnswerRadioButtonList = new ArrayList();
        private List<HBox> answerAltenativesContainer = new ArrayList();
        private HBox hBoxAnswerAltenatives = new HBox();
        private RadioButton correctAnswerRadioButton;
        private List<Studentanswer> studentAnswer = new ArrayList();
        int questionId = 0;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameOnTest.setText(test.getName());
        showQuestion();
        showAnswer();
        
    }    

    @FXML
    private void backward(MouseEvent event) {
        questionId--;
        
        if(questionId >= 0 && questionId < test.getQuestionList().size()){
        
        showQuestion();
        showAnswer();
        
        } 
        
    }
    
    @FXML
    private void forward(MouseEvent event) {
        questionId++;
        if(questionId < test.getQuestionList().size() && questionId >= 0){
        savePickedAnswer();
        showQuestion();
        showAnswer();
        }
        
         
        
    }
    
    public void savePickedAnswer(){
            
        int answerId = 0;
            for(int i = 0; i < correctAnswerRadioButtonList.size();i++){
                       if(correctAnswerRadioButtonList.get(i).isSelected()){
                           answerId = i + 1 ;
                          } 
            }
                System.out.println("Answer id = "+ answerId );
                System.out.println("Question id = "+ questionId );
            
            
    }
    
    public void showQuestion(){
        label.setText(test.getQuestionList().get(questionId).getQuestion());
        questionsLeft.setText(String.valueOf(questionId + 1) + "/" + test.getQuestionList().size());
        
    }
    
    public void showAnswer(){
        
        correctAnswerRadioButtonList.clear();
        vbox.getChildren().clear();
        List<Answer> answer = test.getQuestionList().get(questionId).getAnswerList();
        
        
        for(int i = 0; i < answer.size();i++){
             correctAnswerRadioButton = new RadioButton();
            
            correctAnswerRadioButton.setToggleGroup(toggleGroupCorrectAnswer);
            
            correctAnswerRadioButtonList.add(correctAnswerRadioButton);
            
            
            int size = correctAnswerRadioButtonList.size();
            
            correctAnswerRadioButton.setText(answer.get(i).getAnswer());
            vbox.setSpacing(20);
            vbox.setAlignment(Pos.CENTER_LEFT);
            
            
            vbox.getChildren().add(correctAnswerRadioButtonList.get(size - 1));
        }
        
    }
}
