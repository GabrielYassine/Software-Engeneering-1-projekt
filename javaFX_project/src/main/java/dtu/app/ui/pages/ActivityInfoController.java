package dtu.app.ui.pages;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import javafx.collections.FXCollections;
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
    public Label completionStatus;
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
    private TableView<EmployeeInfo> selectedEmployeesTableView;
    @FXML
    private TableColumn<EmployeeInfo, String> employeeColumn;
    @FXML
    private TextField initialsField;
    @FXML
    private TextField hoursField;
    @FXML
    private DatePicker datePicker;
    @FXML
    public ComboBox<EmployeeInfo> initialsComboBox;


    public void initialize() throws Exception {
        ProjectInfo project = App.application.getSelectedProject();
        ActivityInfo activity = App.application.getSelectedActivity();

        String startWeek = String.valueOf(activity.getStartWeek());
        String endWeek = String.valueOf(activity.getEndWeek());
        startWeekValue.setText(startWeek);
        endWeekValue.setText(endWeek);
        initialsComboBox.setItems(FXCollections.observableArrayList(App.application.getEmployeesInProject(project)));
        updateCompletionStatus(activity);

        datePicker.addEventFilter(KeyEvent.KEY_TYPED, KeyEvent::consume);

        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("initials"));
        selectedEmployeesTableView.getItems().addAll(App.application.getEmployeesInActivity(project, activity));
    }

    private void updateCompletionStatus(ActivityInfo activity) throws Exception {
        completionStatus.setText(App.application.getActivityCompletionStatus(activity));
    }

    @FXML
    private void completeActivity() throws Exception {
        ActivityInfo activity = App.application.getSelectedActivity();
        App.application.switchActivityCompletion(App.application.getSelectedProject(), activity);
        updateCompletionStatus(activity);
    }

    @FXML
    private void registerHours() {
        try {
            EmployeeInfo employeeInfo = initialsComboBox.getSelectionModel().getSelectedItem();
            LocalDate selectedDate = datePicker.getValue();
            String date = selectedDate.toString();
            ActivityInfo activity = App.application.getSelectedActivity();
            String hours = hoursField.getText();
            App.application.registerHours(employeeInfo, date, activity, hours, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void editActivity() throws IOException {
        App.setRoot("editActivity");
    }


}