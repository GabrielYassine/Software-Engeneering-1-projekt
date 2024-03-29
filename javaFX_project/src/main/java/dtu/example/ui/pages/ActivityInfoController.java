package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ActivityInfoController {
    private Activity activity;
    @FXML
    private Label activityNameLabel;

    @FXML
    private Label budgetHoursLabel;

    @FXML
    private Label startWeekLabel;

    @FXML
    private Label endWeekLabel;

    @FXML
    private void initialize() {
        updateActivityInfo();
    }

    private void updateActivityInfo() {
        activity = App.database.selectedActivity;
        if (activity != null) {
            activityNameLabel.setText("name: " + activity.getName());
            budgetHoursLabel.setText("BudgetHours: " + String.valueOf(activity.getBudgetHours()));
            startWeekLabel.setText("StartWeek: " + String.valueOf(activity.getStartWeek()));
            endWeekLabel.setText("EndWeek: " + String.valueOf(activity.getEndWeek()));
        }
    }
}