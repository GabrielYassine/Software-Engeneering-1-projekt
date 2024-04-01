package dtu.app.ui.pages;

import dtu.app.ui.classes.Employee;
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