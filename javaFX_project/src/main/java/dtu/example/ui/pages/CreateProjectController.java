package dtu.example.ui.pages;

import dtu.example.ui.Employee;
import dtu.example.ui.Project;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateProjectController {

    public TextField employeesField;
    @FXML
    private TextField projectNameField;

    @FXML
    private void createProject() throws IOException {
        String projectName = projectNameField.getText();
        String employeeInitials = employeesField.getText();
        try {
            Employee employee = App.database.findEmployeeByInitials(employeeInitials);
            List<Employee> employees = new ArrayList<>();
            employees.add(employee);
            Project newProject = new Project(App.database, projectName, employees);
            App.setRoot("projects");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}