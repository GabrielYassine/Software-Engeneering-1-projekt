package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ActivityInfoController {
    @FXML
    private TextField initialsField;
    @FXML
    private TextField hoursField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label activityNameLabel;
    @FXML
    private Label startWeekLabel;
    @FXML
    private Label endWeekLabel;
    @FXML
    private Label budgetHoursLabel;


    public void initialize() {
        Activity activity = App.database.selectedActivity;
        activityNameLabel.setText(activity.getName());
        startWeekLabel.setText("Start week: " + activity.getStartWeek());
        endWeekLabel.setText("End week: " + activity.getEndWeek());
        budgetHoursLabel.setText("Budget hours: " + activity.getBudgetHours());
    }

    @FXML
    private void registerHours() throws Exception {
        String initials = initialsField.getText();
        String hours = hoursField.getText();
        LocalDate selectedDate = datePicker.getValue();

        Instant instant = selectedDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(instant));

        Activity activity = App.database.selectedActivity;
        Employee employee = App.database.findEmployeeByInitials(initials);
        employee.getActivityLog().registerHours(calendar, activity, hours);
        activity.hoursSpent += Integer.parseInt(hours);
        System.out.println(activity.hoursSpent);
    }
}