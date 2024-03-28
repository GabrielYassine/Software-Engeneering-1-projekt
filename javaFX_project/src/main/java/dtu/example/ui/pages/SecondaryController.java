package dtu.example.ui.pages;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    public Button viewProjectsButton;
    public Button viewEmployeesButton;

    @FXML
    private void switchToProjects() throws IOException {
        App.setRoot("Projects");
    }
    @FXML
    private void switchToEmployees() throws IOException {
        App.setRoot("Employees");
    }
}