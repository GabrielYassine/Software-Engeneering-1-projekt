package dtu.app.ui.pages;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ProjectInfoController extends CommonElementsController {
    @FXML
    public Button createActivityButton;
    @FXML
    public TableView<ActivityInfo> activityTableView;
    @FXML
    public TableColumn<ActivityInfo, String> nameColumn;
    @FXML
    public TableColumn<ActivityInfo, Boolean> completedColumn;
    @FXML
    public Button viewAvailabilitySchedule;
    @FXML
    public TextField startYearField;
    @FXML
    public TextField endYearField;
    @FXML
    private TableColumn<ActivityInfo, String> statusColumn;
    @FXML
    public TableColumn<ActivityInfo, Integer> employeeSizeColumn;
    @FXML
    public Label projectNameValue;
    @FXML
    public Label projectLeaderValue;
    @FXML
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
    private ListView<EmployeeInfo> selectedEmployeesListView;
    @FXML
    private ListView<EmployeeInfo> employeesListView;
    @FXML

    private void initialize() throws Exception {
        super.setupNumericTextFieldListeners(2, startWeekField, endWeekField);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeSizeColumn.setCellValueFactory(new PropertyValueFactory<>("employeesSize"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completionStatus"));

        ProjectInfo selectedProject = App.application.getSelectedProject();

        projectNameValue.setText(selectedProject.getName());

        if (selectedProject.getProjectLeader() != null) {
            projectLeaderValue.setText(selectedProject.getProjectLeader().getInitials());
        } else {
            projectLeaderValue.setText("No leader assigned");
        }

        activityTableView.getItems().addAll(App.application.getActivitiesInProject(selectedProject));
        employeesListView.getItems().addAll(App.application.getEmployeesInProject(selectedProject));

        setRowClickAction(activityTableView, clickedRow -> {
            App.application.setActivity(clickedRow);
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
    private void createActivity() throws Exception {
        ProjectInfo selectedProject = App.application.getSelectedProject();
        String activityName = activityNameField.getText();
        String budgetHours = budgetHoursField.getText();
        String startWeek = startWeekField.getText();
        String endWeek = endWeekField.getText();
        String startYear = startYearField.getText();
        String endYear = endYearField.getText();
        try {
            App.application.createActivity(selectedProject, activityName, budgetHours, startWeek, endWeek, selectedEmployeesListView.getItems(), startYear, endYear);
            // Reset fields
            initialize();
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

    @FXML
    private void editProject() throws IOException {
        App.setRoot("editProject");
    }
}