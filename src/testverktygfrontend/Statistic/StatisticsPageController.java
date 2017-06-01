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
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class StatisticsPageController implements Initializable {

    @FXML
    private Label provLabel;
    @FXML
    private Label courseLabel;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    private int courseId = 1;
    private int testId = 1;
    private String courseName;
    private String testName;
    private int avarageGrade;
    private final Glow glow = new Glow(.8);
    private List<XYChart.Series> bars = new ArrayList<>();
    Logic logic = Logic.getInstanceOf();
    Server server = new Server();
    List<User> users = server.getUsers();
    List<User> usersInClass = new ArrayList<User>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (User u : users) {
            for (Course c : u.getCourseList()) {
                if (c.getId() == courseId) {
                    usersInClass.add(u);
                    courseName = c.getName();
                }
            }
        }

        for (int i = 0; i < usersInClass.size(); i++) {
            List<UserHasTest> tests = logic.getUserTests(usersInClass.get(i).getId());
            for (UserHasTest ut : tests) {
                if (ut.getCourseId().getId() == courseId && ut.getTestId().getId() == testId) {
                    bars.add(new XYChart.Series<>());
                    String email = usersInClass.get(i).getEMail();
                    String[] parts = email.split("@");
                    String name = parts[0];
                    int grade = Integer.parseInt(ut.getGrade());
                    avarageGrade += grade;
                    bars.get(i).getData().add(new XYChart.Data(name, grade));
                    testName = ut.getTestId().getName();
                }
            }
        }
        for (XYChart.Series s : bars) {
            barChart.getData().add(s);
            setupHover(s);

        }
        yAxis.setTickLabelFormatter(
                new NumberAxis.DefaultFormatter(yAxis, null, " %")
        );
        avarageGrade /= bars.size();

        barChart.setTitle("Medelvärde: " + avarageGrade + "%");
        provLabel.setText(testName);
        courseLabel.setText(courseName);

    }

    //Metoden kopierad från https://stackoverflow.com/questions/14119475/javafx-2-2-adding-a-mouse-event-to-a-bar-in-a-barchart-or-stockedbarchart
    //Modifierad av Conrad
    private void setupHover(XYChart.Series<String, Number> series) {
        for (final XYChart.Data<String, Number> dt : series.getData()) {
            final Node n = dt.getNode();

            n.setEffect(null);
            n.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    n.setEffect(glow);
                    Tooltip t = new Tooltip(dt.getXValue() + ": " + String.valueOf(dt.getYValue() + "%"));
                    hackTooltipStartTiming(t);
                    t.install(n, t);
                }
            });
            n.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    n.setEffect(null);
                }
            });
            n.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    //BYTA SCEN TILL TESTRESULT
                    System.out.println(dt.getXValue() + " : " + dt.getYValue());
                }
            });
        }

    }

    //Metoden kopierad från https://stackoverflow.com/questions/26854301/control-javafx-tooltip-delay
    public static void hackTooltipStartTiming(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
