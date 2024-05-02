package dtu.app.ui.pages;

import dtu.app.ui.info.EmployeeInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Map;

public class AvailabilityScheduleController extends CommonElementsController{
    public TextField yearField;
    public TextField monthField;
    public Label yearNumber;
    public Label monthNumber;
    @FXML
    public ListView<String> AvailabilitySchedule;

    public void initialize() {
        super.setupNumericTextFieldListeners(4, yearField);
        super.setupNumericTextFieldListeners(2, monthField);
        yearNumber.setText("No year chosen");
        monthNumber.setText("No month chosen");
    }

    public void selectMonth(ActionEvent actionEvent) throws Exception {
        String year = yearField.getText();
        String month = monthField.getText();
        yearNumber.setText(year);
        monthNumber.setText(month);
        displayMonth(year, month);
    }

    public void displayMonth(String year, String month) throws Exception {
        refreshPage();
        Map<EmployeeInfo, List<Integer>> availability1 = App.application.getAvailability(year, month);
        for (Map.Entry<EmployeeInfo, List<Integer>> entry : availability1.entrySet()) {
            EmployeeInfo employee = entry.getKey();
            List<Integer> availability = entry.getValue();
            StringBuilder availabilityString = new StringBuilder(employee.getInitials() + " - ");
            for (Integer integer : availability) {
                availabilityString.append(integer).append(" ");
            }
            AvailabilitySchedule.getItems().add(availabilityString.toString());
        }
    }

    public void refreshPage() throws Exception {
        TextField[] textFields = new TextField[]{yearField, monthField};
        DatePicker[] datePickers = new DatePicker[]{};
        ListView<?>[] listViews = new ListView<?>[]{AvailabilitySchedule};
        TableView<?>[] tableViews = new TableView<?>[]{};
        clearFields(textFields,datePickers, listViews, tableViews);
        initialize();
    }
}