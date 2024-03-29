package dtu.example.ui.pages;

import dtu.example.ui.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class EmployeesController {
    @FXML
    public ListView<Employee> employeesListView;

    @FXML
    public void initialize() {
        employeesListView.getItems().addAll(App.database.getEmployees());
    }
}