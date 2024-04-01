package dtu.app.ui.pages;

import dtu.app.ui.classes.Activity;
import dtu.app.ui.classes.Employee;
import dtu.app.ui.classes.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditActivityController extends CommonElementsController{

    public ListView<Employee> employeesListView;
    public ListView<Employee> selectedEmployeesListView;
    @FXML
    private TextField activityNameField;

    @FXML
    private TextField budgetHoursField;

    @FXML
    private TextField startWeekField;

    @FXML
    private TextField endWeekField;

    public void initialize() {
        super.setupNumericTextFieldListeners(budgetHoursField, startWeekField, endWeekField);

        Activity activity = App.database.selectedActivity;
        Project project = App.database.selectedProject;

        activityNameField.setText(activity.getName());
        budgetHoursField.setText(String.valueOf(activity.getBudgetHours()));
        startWeekField.setText(String.valueOf(activity.getStartWeek()));
        endWeekField.setText(String.valueOf(activity.getEndWeek()));

        List<Employee> allProjectEmployees = new ArrayList<>(project.getEmployees());
        List<Employee> assignedEmployees = activity.getEmployees();
        allProjectEmployees.removeAll(assignedEmployees);

        selectedEmployeesListView.getItems().addAll(assignedEmployees);
        employeesListView.getItems().addAll(allProjectEmployees);
    }

    @FXML
    private void saveChanges() throws IOException {
        Activity activity = App.database.selectedActivity;
        try {
            String activityName = activityNameField.getText();
            String budgetHours = budgetHoursField.getText();
            String startWeek = startWeekField.getText();
            String endWeek = endWeekField.getText();

            validateAndSaveChanges(activity, activityName, budgetHours, startWeek, endWeek);
            goBack();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void validateAndSaveChanges(Activity activity, String activityName, String budgetHours, String startWeek, String endWeek) throws Exception {
        activity.editActivity(activityName, budgetHours, startWeek, endWeek);
        activity.clearEmployees();
        for (Employee employee : selectedEmployeesListView.getItems()) {
            activity.addEmployee(employee);
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