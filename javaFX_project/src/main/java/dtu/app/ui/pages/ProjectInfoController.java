package dtu.app.ui.pages;

import dtu.app.ui.classes.Activity;
import dtu.app.ui.classes.Employee;
import dtu.app.ui.classes.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ProjectInfoController extends CommonElementsController {
    @FXML
    public Button createActivityButton;
    public TableView<Activity> activityTableView;
    public TableColumn<Activity, String> nameColumn;
    public TableColumn<Activity, Boolean> completedColumn;
    public Button viewAvailabilitySchedule;
    public TextField startYearField;
    public TextField endYearField;
    @FXML
    private TableColumn<Activity, String> statusColumn;

    public TableColumn<Activity, Integer> employeeSizeColumn;

    public Label projectNameValue;
    public Label projectLeaderValue;
    public Button editButton;
    @FXML
    private TextField activityNameField;
    @FXML
    private TextField budgetHoursField;
    @FXML
    private TextField startWeekField;
    @FXML
    private TextField endWeekField;

    @FXML
    private ListView<Employee> selectedEmployeesListView;
    @FXML
    private ListView<Employee> employeesListView;
    @FXML
    private void initialize() {
        super.setupNumericTextFieldListeners(budgetHoursField, startWeekField, endWeekField);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeSizeColumn.setCellValueFactory(new PropertyValueFactory<>("employeesSize"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completedStatus"));

        Project selectedProject = App.database.selectedProject;
        projectNameValue.setText(selectedProject.getName());

        if (selectedProject.getProjectLeader() != null) {
            projectLeaderValue.setText(selectedProject.getProjectLeader().getInitials());
        } else {
            projectLeaderValue.setText("No leader assigned");
        }

        activityTableView.getItems().addAll(selectedProject.getActivities());
        employeesListView.getItems().addAll(selectedProject.getEmployees());

        setRowClickAction(activityTableView, clickedRow -> {
            App.database.setSelectedActivity(clickedRow);
            try {
                App.setRoot("activityInfo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    private void switchToAvailabilitySchedule() throws IOException {
        App.setRoot("availabilitySchedule");
    }
    @FXML
    private void createActivity() throws IOException {
        Project selectedProject = App.database.selectedProject;
        String activityName = activityNameField.getText();
        String budgetHours = budgetHoursField.getText();
        String startWeek = startWeekField.getText();
        String endWeek = endWeekField.getText();
        String startYear = startYearField.getText();
        String endYear = endYearField.getText();
        try {
            Activity newActivity = new Activity(selectedProject, activityName, budgetHours, startWeek, endWeek, selectedEmployeesListView.getItems(), startYear, endYear);
            activityTableView.getItems().add(newActivity);
            resetActivityCreationFields();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEmployee(ActionEvent actionEvent) {
        super.addEmployee(employeesListView, selectedEmployeesListView);
    }

    public void removeEmployee(ActionEvent actionEvent) {
        super.removeEmployee(employeesListView, selectedEmployeesListView);
    }

    private void resetActivityCreationFields() {
        super.resetActivityCreationFields(activityNameField, budgetHoursField, startWeekField, endWeekField, employeesListView, selectedEmployeesListView, null, null, null);
    }

    @FXML
    private void editProject() throws IOException {
        App.setRoot("editProject");
    }
}