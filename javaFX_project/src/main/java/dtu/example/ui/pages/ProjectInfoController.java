package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Project;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.io.IOException;

public class ProjectInfoController {

    public Button createActivityButton;
    public TableView<Activity> activityTableView;
    public TableColumn<Activity, String> nameColumn;

    @FXML
    private TextField activityNameField;
    @FXML
    private TextField budgetHoursField;
    @FXML
    private TextField startWeekField;
    @FXML
    private TextField endWeekField;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        Project selectedProject = App.database.selectedProject;
        activityTableView.getItems().addAll(selectedProject.getActivities());

        activityTableView.setRowFactory(tv -> {
            TableRow<Activity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Activity clickedRow = row.getItem();
                    App.database.setSelectedActivity(clickedRow);
                    try {
                        App.setRoot("activityInfo");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        budgetHoursField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                budgetHoursField.setText(oldValue);
            }
        });

        startWeekField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                startWeekField.setText(oldValue);
            }
        });

        endWeekField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                endWeekField.setText(oldValue);
            }
        });
    }

    @FXML
    private void createActivity() throws IOException {
        Project selectedProject = App.database.selectedProject;
        String activityName = activityNameField.getText();
        String budgetHours = budgetHoursField.getText();
        String startWeek = startWeekField.getText();
        String endWeek = endWeekField.getText();
        try {
            Activity newActivity = new Activity(selectedProject, activityName, budgetHours, startWeek, endWeek);
            activityTableView.getItems().add(newActivity);
            resetActivityCreationFields();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void resetActivityCreationFields() {
        activityNameField.clear();
        budgetHoursField.clear();
        startWeekField.clear();
        endWeekField.clear();
    }
}