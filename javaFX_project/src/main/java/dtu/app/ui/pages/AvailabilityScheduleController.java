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
        refreshPage();
    }

    public void displayMonth(String year, String month) throws Exception {
        List<EmployeeInfo> employees = App.application.getEmployeesInApp();
        for (EmployeeInfo employee : employees) {
            List<Integer> availability = App.application.getAvailability(employee, year, month);
            String availabilityString = employee.getInitials() + "          First Week: " + availability.get(0) + "          Second Week: " + availability.get(1) + "          Third Week: " + availability.get(2) + "          Fourth Week: " + availability.get(3) + "          Fifth Week: " + availability.get(4);
            AvailabilitySchedule.getItems().add(availabilityString);
        }
    }

    public void refreshPage() throws Exception {
        TextField[] textFields = new TextField[]{yearField, monthField};
        DatePicker[] datePickers = new DatePicker[]{};
        ListView<?>[] listViews = new ListView<?>[]{};
        TableView<?>[] tableViews = new TableView<?>[]{};
        clearFields(textFields,datePickers, listViews, tableViews);
        initialize();
    }
}