package dtu.app.ui.pages;

import dtu.app.ui.classes.Activity;
import dtu.app.ui.classes.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ActivityInfoController extends CommonElementsController {
    @FXML
    private Button completeAButton;
    @FXML
    private Label activityNameValue;
    @FXML
    private Label startWeekValue;
    @FXML
    private Label endWeekValue;
    @FXML
    private Label budgetHoursValue;
    @FXML
    private Button editButton;
    @FXML
    private TableView<Employee> selectedEmployeesTableView;
    @FXML
    private TableColumn<Employee, String> employeeColumn;
    @FXML
    private TextField initialsField;
    @FXML
    private TextField hoursField;
    @FXML
    private DatePicker datePicker;


    public void initialize() {
        Activity activity = App.database.selectedActivity;
        updateActivityInfo(activity);

        datePicker.addEventFilter(KeyEvent.KEY_TYPED, KeyEvent::consume);
        setupNumericTextFieldListeners(hoursField);
        setupLetterTextFieldListeners(initialsField);

        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("initials"));
        selectedEmployeesTableView.getItems().addAll(App.database.selectedActivity.getEmployees());
    }

    private void updateActivityInfo(Activity activity) {
        budgetHoursValue.setText(activity.getHoursSpent() + " / " + activity.getBudgetHours());
    }

    @FXML
    private void registerHours() {
        try {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate == null) {
                throw new Exception("Date not selected");
            }
            String initials = initialsField.getText();
            Activity activity = App.database.selectedActivity;
            Employee employee = activity.getProject().getEmployee(initials);
            Calendar calendar = getCalendarFromSelectedDate(selectedDate);
            String hours = hoursField.getText();

            registerHoursForEmployee(employee, calendar, activity, hours);
            updateActivityInfo(activity);
            resetActivityCreationFields();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Calendar getCalendarFromSelectedDate(LocalDate selectedDate) {
        Instant instant = selectedDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(instant));
        return calendar;
    }

    private void registerHoursForEmployee(Employee employee, Calendar calendar, Activity activity, String hours) throws Exception {
        employee.getActivityLog().registerHours(calendar, activity, hours);
        activity.registerHours(Integer.parseInt(hours));
    }

    private void resetActivityCreationFields() {
        super.resetActivityCreationFields(null, null, null, null, null, null, initialsField, hoursField, datePicker);
    }

    @FXML
    private void editActivity() throws IOException {
        App.setRoot("editActivity");
    }

    @FXML
    private void completeActivity() {
        Activity activity = App.database.selectedActivity;
        activity.completeActivity();
    }
}