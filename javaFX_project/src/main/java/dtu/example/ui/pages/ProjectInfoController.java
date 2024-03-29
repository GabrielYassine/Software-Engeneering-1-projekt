package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ProjectInfoController {

    public Button createActivityButton;
    @FXML
    private ListView<Activity> activityListView;

    @FXML
    private void initialize() {
        Project selectedProject = App.database.selectedProject;
        activityListView.getItems().addAll(selectedProject.getActivities());
        activityListView.setOnMouseClicked(this::handleMouseClick);
    }

    @FXML
    private void switchToCreateActivity() throws IOException {
        App.setRoot("createActivity");
    }

    private void handleMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                Activity selectedActivity = activityListView.getSelectionModel().getSelectedItem();
                App.database.setSelectedActivity(selectedActivity);
                App.setRoot("activityInfo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}