package dtu.app.ui.pages;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    public Button viewProjectsButton;
    public Button viewEmployeesButton;

    @FXML
    private void switchToProjects() throws IOException {
        App.setRoot("projects");
    }
    @FXML
    private void switchToEmployees() throws IOException {
        App.setRoot("employees");
    }
}