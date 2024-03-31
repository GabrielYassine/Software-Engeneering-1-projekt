package dtu.example.ui.pages;

import dtu.example.ui.Employee;
import dtu.example.ui.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditProjectController extends CommonElementsController{

    public ListView<Employee> employeesListView;
    public ListView<Employee> selectedEmployeesListView;
    public ComboBox<String> projectLeaderComboBox;
    @FXML
    private TextField projectNameField;

    @FXML
    private TextField projectLeaderField;

    public void initialize() {
        Project project = App.database.selectedProject;
        projectNameField.setText(project.getName());

        List<Employee> allEmployees = new ArrayList<>(App.database.getEmployees());
        List<Employee> assignedEmployees = project.getEmployees();
        allEmployees.removeAll(assignedEmployees);

        for (Employee employee : allEmployees) {
            projectLeaderComboBox.getItems().add(employee.getInitials());
        }

        if (project.getProjectLeader() != null) {
            projectLeaderComboBox.setValue(project.getProjectLeader().getInitials());
        }

        selectedEmployeesListView.getItems().addAll(assignedEmployees);
        employeesListView.getItems().addAll(allEmployees);
    }

    @FXML
    private void saveChanges() throws IOException {
        Project project = App.database.selectedProject;
        String newProjectName = projectNameField.getText();
        String projectLeaderInitials = projectLeaderComboBox.getValue();
        Employee newProjectLeader = null;

        for (Employee employee : App.database.getEmployees()) {
            if (employee.getInitials().equals(projectLeaderInitials)) {
                newProjectLeader = employee;
                break;
            }
        }

        project.clearEmployees();
        for (Employee employee : selectedEmployeesListView.getItems()) {
            project.addEmployee(employee);
        }

        project.editProject(newProjectName, newProjectLeader);

        new CommonElementsController().goBack();
    }

    @FXML
    private void cancelEdit() throws IOException {
        new CommonElementsController().goBack();
    }

    public void addEmployee(ActionEvent actionEvent) {
        super.addEmployee(employeesListView, selectedEmployeesListView);
    }

    public void removeEmployee(ActionEvent actionEvent) {
        super.removeEmployee(employeesListView, selectedEmployeesListView);
    }
}