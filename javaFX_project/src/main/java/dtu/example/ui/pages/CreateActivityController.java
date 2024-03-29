package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Project;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public void initialize() {
        budgetHoursField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    budgetHoursField.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private void createActivity() throws IOException {
        selectedProject = App.database.selectedProject;
        String activityName = activityNameField.getText();
        String budgetHours = budgetHoursField.getText();
        String startWeek = startWeekField.getText();
        String endWeek = endWeekField.getText();
        try {
            Activity newActivity = new Activity(selectedProject, activityName, budgetHours, startWeek, endWeek);
            App.setRoot("projectInfo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}