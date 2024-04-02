package dtu.app.ui.pages;

import dtu.app.ui.classes.Employee;
import dtu.app.ui.classes.Project;
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
    public ListView<Employee> employeesListView;
    @FXML
    public ListView<Employee> selectedEmployeesListView;
    @FXML
    public ComboBox<String> projectLeaderComboBox;
    @FXML
    private TextField projectNameField;

    @FXML
    public void initialize() {
        Project project = App.database.selectedProject;
        projectNameField.setText(project.getName());

        List<Employee> allEmployees = new ArrayList<>(App.database.getEmployees());
        List<Employee> assignedEmployees = project.getEmployees();
        allEmployees.removeAll(assignedEmployees);
        projectLeaderComboBox.getItems().add("None");

        for (Employee employee : assignedEmployees) {
            projectLeaderComboBox.getItems().add(employee.getInitials());
        }

        if (project.getProjectLeader() != null) {
            projectLeaderComboBox.setValue(project.getProjectLeader().getInitials());
        }

        selectedEmployeesListView.getItems().addAll(assignedEmployees);
        employeesListView.getItems().addAll(allEmployees);
    }

    @FXML
    private void saveChanges() throws Exception {
        Project project = App.database.selectedProject;
        String newProjectName = projectNameField.getText();
        String projectLeaderInitials = projectLeaderComboBox.getValue();
        List<Employee> newEmployees = new ArrayList<>(selectedEmployeesListView.getItems());

        project.editProject(newProjectName, projectLeaderInitials, newEmployees);

        new CommonElementsController().goBack();
    }

    @FXML
    private void cancelEdit() throws IOException {
        new CommonElementsController().goBack();
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