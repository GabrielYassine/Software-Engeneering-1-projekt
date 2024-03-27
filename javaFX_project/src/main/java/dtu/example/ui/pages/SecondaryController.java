package dtu.example.ui.pages;

import java.io.IOException;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToProjects() throws IOException {
        App.setRoot("Projects");
    }
    @FXML
    private void switchToEmployees() throws IOException {
        App.setRoot("Employees");
    }
}