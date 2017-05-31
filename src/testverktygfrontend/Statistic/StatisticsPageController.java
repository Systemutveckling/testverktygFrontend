/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testverktygfrontend.Statistic;

import com.logic.Logic;
import com.model.Course;
import com.model.User;
import com.model.UserHasTest;
import com.serverconnection.Server;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class StatisticsPageController implements Initializable {

    @FXML
    private Label provLabel;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private int courseId=1;
    private int testId=1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series set1 = new XYChart.Series<>();
        Server server = new Server();
        List<User> users = server.getUsers();
        List<User> usersInClass = new ArrayList<User>();
        for(User u : users){
            for(Course c : u.getCourseList()){
                if(c.getId()==courseId){
                    usersInClass.add(u);
                }
            }
        }
        Logic logic = Logic.getInstanceOf();

        for(int i = 0; i < usersInClass.size(); i++){
            List<UserHasTest> tests = logic.getUserTests(usersInClass.get(i).getId());
            for(UserHasTest ut : tests){
                if (ut.getCourseId().getId()==courseId && ut.getTestId().getId() == testId){
                    int grade = Integer.parseInt(ut.getGrade());
                    set1.getData().add(new XYChart.Data(usersInClass.get(i).getEMail(), grade));
                }
            }
        }
       // set1.getData().add(new XYChart.Data("ss", 1212));
        barChart.getData().addAll(set1);
    }

}
