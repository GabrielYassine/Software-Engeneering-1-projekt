package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ActivityInfoController extends CommonElementsController {


    public Label activityNameValue;

    public Label startWeekValue;

    public Label endWeekValue;

    public Label budgetHoursValue;
    public Button editButton;
    public TableView<Employee> selectedEmployeesTableView;
    public TableColumn<Employee, String> employeeColumn;

    @FXML
    private TextField initialsField;

    @FXML
    private TextField hoursField;

    @FXML
    private DatePicker datePicker;


    public void initialize() {
        super.setupNumericTextFieldListeners(hoursField);
        super.setupLetterTextFieldListeners(initialsField);
        Activity activity = App.database.selectedActivity;
        activityNameValue.setText(activity.getName());
        startWeekValue.setText(String.valueOf(activity.getStartWeek()));
        endWeekValue.setText(String.valueOf(activity.getEndWeek()));
        budgetHoursValue.setText(activity.hoursSpent + " / " + activity.getBudgetHours());
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("initials"));
        selectedEmployeesTableView.getItems().addAll(App.database.selectedActivity.getEmployees());
    }

    @FXML
    private void registerHours() throws Exception {
        String initials = initialsField.getText();
        Activity activity = App.database.selectedActivity;
        Employee employee = App.database.findEmployeeByInitials(initials);

        LocalDate selectedDate = datePicker.getValue();
        Instant instant = selectedDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(instant));

        String hours = hoursField.getText();

        employee.getActivityLog().registerHours(calendar, activity, hours);
        activity.hoursSpent += Integer.parseInt(hours);
    }

    @FXML
    private void editActivity() throws IOException {
        App.setRoot("editActivity");
    }

}