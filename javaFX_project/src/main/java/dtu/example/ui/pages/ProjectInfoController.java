package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Employee;
import dtu.example.ui.Project;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.io.IOException;

public class ProjectInfoController extends CommonElementsController {
    @FXML
    public Button createActivityButton;
    public TableView<Activity> activityTableView;
    public TableColumn<Activity, String> nameColumn;

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

        Project selectedProject = App.database.selectedProject;
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
    private void createActivity() throws IOException {
        Project selectedProject = App.database.selectedProject;
        String activityName = activityNameField.getText();
        String budgetHours = budgetHoursField.getText();
        String startWeek = startWeekField.getText();
        String endWeek = endWeekField.getText();
        try {
            Activity newActivity = new Activity(selectedProject, activityName, budgetHours, startWeek, endWeek);
            for (Employee e : selectedEmployeesListView.getItems()) {
                newActivity.addEmployee(e);
            }
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
        super.resetActivityCreationFields(activityNameField, budgetHoursField, startWeekField, endWeekField, employeesListView, selectedEmployeesListView);
    }
}