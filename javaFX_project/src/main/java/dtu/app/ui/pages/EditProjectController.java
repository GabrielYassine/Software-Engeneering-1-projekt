package dtu.app.ui.pages;

import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditProjectController extends CommonElementsController{

    @FXML
    public ListView<EmployeeInfo> selectedEmployeesListView;
    @FXML
    public ComboBox<String> projectLeaderComboBox;
    @FXML
    public ListView<EmployeeInfo> employeesListView;
    @FXML
    private TextField projectNameField;

    @FXML
    public void initialize() throws Exception {
        ProjectInfo project = App.application.getSelectedProject();
        String projectName = project.getName();
        projectNameField.setText(projectName);
        super.setupLetterTextFieldListeners(projectNameField);

        List<EmployeeInfo> allEmployees = App.application.getEmployeesInApp();
        List<EmployeeInfo> assignedEmployees = App.application.getEmployeesInProject(project);
        allEmployees.removeAll(assignedEmployees);
        selectedEmployeesListView.getItems().addAll(assignedEmployees);
        employeesListView.getItems().addAll(allEmployees);
        projectLeaderComboBox.getItems().add("None");

        for (EmployeeInfo employee : assignedEmployees) {
            projectLeaderComboBox.getItems().add(employee.getInitials());
        }

        if (project.getProjectLeader() != null) {
            projectLeaderComboBox.setValue(project.getProjectLeader().getInitials());
        }
    }

    @FXML
    private void saveChanges() throws Exception {
        ProjectInfo project = App.application.getSelectedProject();
        String newProjectName = projectNameField.getText();
        String projectLeaderInitials = projectLeaderComboBox.getValue();
        List<EmployeeInfo> newEmployees = new ArrayList<>(selectedEmployeesListView.getItems());
        App.application.editProject(project, newProjectName, projectLeaderInitials, newEmployees);
        goBack();
    }

    @FXML
    private void cancelEdit() throws Exception {
        goBack();
    }

    @FXML
    public void addEmployee(ActionEvent actionEvent) {
        super.addEmployee(employeesListView, selectedEmployeesListView);
    }
    @FXML
    public void removeEmployee(ActionEvent actionEvent) {
        super.removeEmployee(employeesListView, selectedEmployeesListView);
    }
}