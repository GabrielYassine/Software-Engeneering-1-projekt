package dtu.app.ui.pages;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.info.ActivityLogInfo;
import dtu.app.ui.info.EmployeeInfo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeLogController extends CommonElementsController {
    @FXML
    public ListView<String> mondayListView;
    @FXML
    public ListView<String> tuesdayListView;
    @FXML
    public ListView<String> wednesdayListView;
    @FXML
    public ListView<String> thursdayListView;
    @FXML
    public ListView<String> fridayListView;
    @FXML
    public ListView<String> saturdayListView;
    @FXML
    public ListView<String> sundayListView;
    public TextField yearField;
    public TextField weekField;
    public Label yearNumber;
    public Label weekNumber;
    public Label initialsValue;
    @FXML
    public ComboBox<EmployeeInfo> EmployeesComboBox;
    public Label mondayDateValue;
    public Label tuesdayDateValue;
    public Label wednesdayDateValue;
    public Label thursdayDateValue;
    public Label fridayDateValue;
    public Label saturdayDateValue;
    public Label sundayDateValue;

    public void initialize() {
        setupNumericTextFieldListeners(4, yearField);
        setupNumericTextFieldListeners(2, weekField);

        EmployeesComboBox.setItems(FXCollections.observableArrayList(App.application.getEmployeesInApp()));

        yearNumber.setText("No year chosen");
        weekNumber.setText("No week chosen");
        initialsValue.setText("No employee chosen");

        setupListViewClickHandler(mondayListView, mondayDateValue);
        setupListViewClickHandler(tuesdayListView, tuesdayDateValue);
        setupListViewClickHandler(wednesdayListView, wednesdayDateValue);
        setupListViewClickHandler(thursdayListView, thursdayDateValue);
        setupListViewClickHandler(fridayListView, fridayDateValue);
        setupListViewClickHandler(saturdayListView, saturdayDateValue);
        setupListViewClickHandler(sundayListView, sundayDateValue);
    }

    private void setupListViewClickHandler(ListView<String> listView, Label dateLabel) {
        listView.setOnMouseClicked(event -> {
            if (!listView.getSelectionModel().isEmpty() && event.getClickCount() == 2) {
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                EmployeeInfo employeeInfo = EmployeesComboBox.getSelectionModel().getSelectedItem();

                List<String> items = new ArrayList<>();

                items.add((selectedItem.split("\n")[0])); // ProjectId
                items.add((selectedItem.split("\n")[1])); // ActivityName
                items.add((dateLabel.getText())); // Date
                items.add(employeeInfo.getInitials()); // Initials

                App.application.setSelectedEmployeeLog(items);
                try {
                    // You can now use the date variable as needed
                    App.setRoot("employeeLogInfo");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void selectWeek(ActionEvent actionEvent) throws Exception {
        EmployeeInfo employeeInfo = EmployeesComboBox.getSelectionModel().getSelectedItem();
        String year = yearField.getText();
        String week = weekField.getText();

        initialsValue.setText(employeeInfo.getInitials());
        yearNumber.setText(year);
        weekNumber.setText(week);

        ActivityLogInfo employeeLog = App.application.getEmployeeWeekLog(employeeInfo, year, week);
        refreshPage();
        setupLists(employeeInfo, employeeLog, year, week);
    }

    public void setupLists(EmployeeInfo e, ActivityLogInfo a, String year, String week) {
        addActivitiesToView(mondayListView, App.application.getEmployeeDayLog(a, "Monday"));
        addActivitiesToView(tuesdayListView, App.application.getEmployeeDayLog(a, "Tuesday"));
        addActivitiesToView(wednesdayListView, App.application.getEmployeeDayLog(a, "Wednesday"));
        addActivitiesToView(thursdayListView, App.application.getEmployeeDayLog(a, "Thursday"));
        addActivitiesToView(fridayListView, App.application.getEmployeeDayLog(a, "Friday"));
        addActivitiesToView(saturdayListView, App.application.getEmployeeDayLog(a, "Saturday"));
        addActivitiesToView(sundayListView, App.application.getEmployeeDayLog(a, "Sunday"));

        mondayDateValue.setText(App.application.getWeekDates(year, week).get(0));
        tuesdayDateValue.setText(App.application.getWeekDates(year, week).get(1));
        wednesdayDateValue.setText(App.application.getWeekDates(year, week).get(2));
        thursdayDateValue.setText(App.application.getWeekDates(year, week).get(3));
        fridayDateValue.setText(App.application.getWeekDates(year, week).get(4));
        saturdayDateValue.setText(App.application.getWeekDates(year, week).get(5));
        sundayDateValue.setText(App.application.getWeekDates(year, week).get(6));
    }

    private void addActivitiesToView(ListView<String> listView, Map<Activity, Double> activities) {
        for (Map.Entry<Activity, Double> entry : activities.entrySet()) {
            int projectID = entry.getKey().getProject().getID();
            listView.getItems().add(projectID + "\n" + entry.getKey().getName() + "\n" + entry.getValue() + " hours");
        }
    }

    public void SwitchToFixedActvities(ActionEvent actionEvent) {
        try {
            App.application.setEmployee(EmployeesComboBox.getSelectionModel().getSelectedItem());
            App.setRoot("fixedActivities");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void refreshPage() throws Exception {
        TextField[] textFields = new TextField[]{yearField, weekField};
        DatePicker[] datePickers = new DatePicker[]{};
        ListView<?>[] listViews = new ListView<?>[]{mondayListView, tuesdayListView, wednesdayListView, thursdayListView, fridayListView, saturdayListView, sundayListView};
        TableView<?>[] tableViews = new TableView<?>[]{};
        clearFields(textFields, datePickers, listViews, tableViews);
        initialize();
    }
}