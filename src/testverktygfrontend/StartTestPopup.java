/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Alexiz
 */
public class StartTestPopup extends Application{
    Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2;
    Stage thestage, newStage;
   
    @Override
    public void start(Stage primaryStage) {
     
        thestage=primaryStage;
        //kan använda scenen i andra metoder
        //make things to put on panes
        btnscene1=new Button("Starta");   //Klicka för att komma till andra scenen 
        btnscene1.setOnAction(e-> ButtonClicked(e));
        lblscene1=new Label("Scene 1");
    
        //gör en Panes
        pane1=new FlowPane();
        pane1.setHgap(20);
        //lägger till allting till pane
        pane1.getChildren().addAll(lblscene1, btnscene1);
     
        //make 1 scenes from 1 panes
        scene1 = new Scene(pane1, 200, 100);
        //tell stage it is meannt to pop-up (Modal)
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Pop up window");
        //rest of code -
        primaryStage.setTitle("Starting a test");
        primaryStage.setScene(scene1);
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }
    
    public void ButtonClicked(ActionEvent e){
        if (e.getSource()==btnscene1)
            newStage.showAndWait();
        else
            newStage.close();
    }

}
