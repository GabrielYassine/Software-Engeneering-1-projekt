package dtu.example.ui.pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ProjectsController {
    public Button createProjectsButton;

    @FXML
    private void switchToCreateProject() throws IOException {
        App.setRoot("createProject");
    }
}