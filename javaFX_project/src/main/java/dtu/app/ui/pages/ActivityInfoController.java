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
    public Label activityNameValue;
    public Label startYearValue;
    public Label endYearValue;
    @FXML
    private Button completeAButton;
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
    private TextField hoursField;
    @FXML
    private DatePicker datePicker;
    @FXML
    public ComboBox<EmployeeInfo> initialsComboBox;


    public void initialize() throws Exception {
        super.setupDoubleTextFieldListeners(hoursField);

        ProjectInfo project = App.application.getSelectedProject();
        ActivityInfo activity = App.application.getSelectedActivity();

        String startWeek = String.valueOf(activity.getStartWeek());
        String endWeek = String.valueOf(activity.getEndWeek());

        activityNameValue.setText(activity.getName());
        startWeekValue.setText(startWeek);
        endWeekValue.setText(endWeek);
        startYearValue.setText(String.valueOf(activity.getStartYear()));
        endYearValue.setText(String.valueOf(activity.getEndYear()));
        budgetHoursValue.setText(String.valueOf(activity.getStatus()));
        initialsComboBox.setItems(FXCollections.observableArrayList(App.application.getEmployeesInProject(project)));
        updateCompletionStatus();

        datePicker.addEventFilter(KeyEvent.KEY_TYPED, KeyEvent::consume);

        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("initials"));
        selectedEmployeesTableView.getItems().addAll(App.application.getEmployeesInActivity(project, activity));
    }

    private void updateCompletionStatus() throws Exception {
        ActivityInfo activity = App.application.getSelectedActivity();
        String completed = App.application.getActivityCompletionStatus(activity);
        completionStatus.setText(completed);
    }

    @FXML
    private void completeActivity() throws Exception {
        ActivityInfo activity = App.application.getSelectedActivity();
        App.application.switchActivityCompletion(App.application.getSelectedProject(), activity);
        updateCompletionStatus();
    }

    @FXML
    private void registerHours() {
        try {
            EmployeeInfo employeeInfo = initialsComboBox.getSelectionModel().getSelectedItem();
            LocalDate selectedDate = datePicker.getValue();
            String date = selectedDate == null ? "" : selectedDate.toString();
            ActivityInfo activity = App.application.getSelectedActivity();
            String hours = hoursField.getText();
            App.application.registerHours(employeeInfo, date, activity, hours, null);
            refreshPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void editActivity() throws IOException {
        App.setRoot("editActivity");
    }

    public void refreshPage() throws Exception {
        TextField[] textFields = new TextField[]{hoursField};
        DatePicker[] datePickers = new DatePicker[]{datePicker};
        ListView<?>[] listViews = new ListView<?>[]{};
        TableView<?>[] tableViews = new TableView<?>[]{selectedEmployeesTableView};
        clearFields(textFields,datePickers, listViews, tableViews);
        initialize();
    }


}