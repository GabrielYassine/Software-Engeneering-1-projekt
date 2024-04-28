package dtu.app.ui.pages;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditActivityController extends CommonElementsController{

    public ListView<EmployeeInfo> employeesListView;
    public ListView<EmployeeInfo> selectedEmployeesListView;
    @FXML
    private TextField activityNameField;

    @FXML
    private TextField budgetHoursField;

    @FXML
    private TextField startWeekField;

    @FXML
    private TextField endWeekField;

    public void initialize() throws Exception {
        super.setupNumericTextFieldListeners(2, startWeekField, endWeekField);

        ActivityInfo activity = App.application.getSelectedActivity();
        ProjectInfo project = App.application.getSelectedProject();

        activityNameField.setText(activity.getName());
        budgetHoursField.setText(String.valueOf(activity.getBudgetHours()));
        startWeekField.setText(String.valueOf(activity.getStartWeek()));
        endWeekField.setText(String.valueOf(activity.getEndWeek()));

        List<EmployeeInfo> allProjectEmployees = App.application.getEmployeesInProject(project);
        List<EmployeeInfo> assignedEmployees = App.application.getEmployeesInActivity(project, activity);
        allProjectEmployees.removeAll(assignedEmployees);

        selectedEmployeesListView.getItems().addAll(assignedEmployees);
        employeesListView.getItems().addAll(allProjectEmployees);
    }

    @FXML
    private void saveChanges() throws Exception {
        ActivityInfo activity = App.application.getSelectedActivity();
        try {
            String activityName = activityNameField.getText();
            String budgetHours = budgetHoursField.getText();
            String startWeek = startWeekField.getText();
            String endWeek = endWeekField.getText();
            goBack();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void cancelEdit() throws IOException {
        goBack();
    }

    public void addEmployee(ActionEvent actionEvent) {
        super.addEmployee(employeesListView, selectedEmployeesListView);
    }

    public void removeEmployee(ActionEvent actionEvent) {
        super.removeEmployee(employeesListView, selectedEmployeesListView);
    }
}