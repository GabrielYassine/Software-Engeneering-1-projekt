package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Project;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateActivityController {

    public Project selectedProject;

    @FXML
    private TextField activityNameField;
    @FXML
    private TextField budgetHoursField;
    @FXML
    private TextField startWeekField;
    @FXML
    private TextField endWeekField;


    @FXML
    private void createActivity() throws IOException {
        selectedProject = App.database.selectedProject;
        String activityName = activityNameField.getText();
        int budgetHours = Integer.parseInt(budgetHoursField.getText());
        int startWeek = Integer.parseInt(startWeekField.getText());
        int endWeek = Integer.parseInt(endWeekField.getText());
        try {
            Activity newActivity = new Activity(selectedProject, activityName, budgetHours, startWeek, endWeek);
            App.setRoot("projectInfo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}