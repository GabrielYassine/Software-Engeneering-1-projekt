package dtu.app.ui.pages;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import dtu.app.ui.classes.ActivityLog;
import dtu.app.ui.classes.Activity;

public class ActivityLogController {
    public TableView activityTable;
    @FXML
    private TableColumn<Activity, String> mondayColumn;
    @FXML
    private TableColumn<Activity, String> tuesdayColumn;
    @FXML
    private TableColumn<Activity, String> wednesdayColumn;
    @FXML
    private TableColumn<Activity, String> thursdayColumn;
    @FXML
    private TableColumn<Activity, String> fridayColumn;
    @FXML
    private TableColumn<Activity, String> saturdayColumn;
    @FXML
    private TableColumn<Activity, String> sundayColumn;

    public void initialize() {
        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("mondayActivities"));
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesdayActivities"));
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesdayActivities"));
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursdayActivities"));
        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("fridayActivities"));
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<>("saturdayActivities"));
        sundayColumn.setCellValueFactory(new PropertyValueFactory<>("sundayActivities"));
    }
}