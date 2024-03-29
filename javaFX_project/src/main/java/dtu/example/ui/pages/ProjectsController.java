package dtu.example.ui.pages;

import dtu.example.ui.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProjectsController {
    public Button createProjectsButton;
    public ListView<Project> projectsListView;

    @FXML
    private void initialize() throws Exception {
        projectsListView.getItems().addAll(App.database.getProjects());
        projectsListView.setOnMouseClicked(this::handleMouseClick);
    }

    @FXML
    private void switchToCreateProject() throws IOException {
        App.setRoot("createProject");
    }

    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                Project selectedProject = projectsListView.getSelectionModel().getSelectedItem();
                App.database.setSelectedProject(selectedProject);
                App.setRoot("projectInfo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}