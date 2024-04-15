package dtu.app.ui.pages;

import dtu.app.ui.classes.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import dtu.app.ui.classes.ActivityLog;

public class EmployeeLogController extends CommonElementsController{
    @FXML
    public ListView<String> mondayListView;
    @FXML
    public ListView<String> tuesdayListView;
    @FXML
    public ListView<String> wednesdayListView;
    @FXML
    public ListView<String> thursdayListView;
    @FXML
    public ListView<String> fridayListView;
    @FXML
    public ListView<String> saturdayListView;
    @FXML
    public ListView<String> sundayListView;

    public TextField initialsField;
    public TextField yearField;
    public TextField weekField;
    public Label yearNumber;
    public Label weekNumber;
    public Label initialsValue;

    public void initialize() {
        setupLetterTextFieldListeners(initialsField);
        setupNumericTextFieldListeners(yearField);
        setupNumericTextFieldListeners(weekField);

        yearNumber.setText("No year chosen");
        weekNumber.setText("No week chosen");
        initialsValue.setText("No employee chosen");
    }

    public void selectWeek(ActionEvent actionEvent) throws Exception {
        String initials = initialsField.getText();
        String year = yearField.getText();
        String week = weekField.getText();
        displayWeek(initials, year, week);
        initialsValue.setText(initials);
        yearNumber.setText(year);
        weekNumber.setText(week);
    }

    public void displayWeek(String initials, String year, String week) throws Exception {
        Employee employee = App.database.getEmployee(initials);
        ActivityLog weekActivities = employee.getActivityLog().getWeekActivities(year, week);

        mondayListView.getItems().addAll(weekActivities.getMondayActivities());
        tuesdayListView.getItems().addAll(weekActivities.getTuesdayActivities());
        wednesdayListView.getItems().addAll(weekActivities.getWednesdayActivities());
        thursdayListView.getItems().addAll(weekActivities.getThursdayActivities());
        fridayListView.getItems().addAll(weekActivities.getFridayActivities());
        saturdayListView.getItems().addAll(weekActivities.getSaturdayActivities());
        sundayListView.getItems().addAll(weekActivities.getSundayActivities());
    }
}