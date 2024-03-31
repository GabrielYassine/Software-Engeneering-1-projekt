package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditActivityController extends CommonElementsController{

    @FXML
    private TextField activityNameField;

    @FXML
    private TextField budgetHoursField;

    @FXML
    private TextField startWeekField;

    @FXML
    private TextField endWeekField;

    public void initialize() {
        super.setupNumericTextFieldListeners(budgetHoursField, startWeekField, endWeekField);
        Activity activity = App.database.selectedActivity;
        activityNameField.setText(activity.getName());
        budgetHoursField.setText(String.valueOf(activity.getBudgetHours()));
        startWeekField.setText(String.valueOf(activity.getStartWeek()));
        endWeekField.setText(String.valueOf(activity.getEndWeek()));
    }

    @FXML
    private void saveChanges() throws IOException {
        Activity activity = App.database.selectedActivity;
        try {
            activity.editActivity(activityNameField.getText(), budgetHoursField.getText(), startWeekField.getText(), endWeekField.getText());
            new CommonElementsController().goBack();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void cancelEdit() throws IOException {
        new CommonElementsController().goBack();
    }

}