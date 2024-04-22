package dtu.app.ui.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AvailabilityScheduleController extends CommonElementsController{
    @FXML
    public ListView<String> Week1;
    @FXML
    public ListView<String> Week2;
    @FXML
    public ListView<String> Week3;
    @FXML
    public ListView<String> Week4;
    @FXML
    public ListView<String> Week5;

    public TextField yearField;
    public TextField monthField;
    public Label yearNumber;
    public Label monthNumber;

    public void initialize() {
        setupNumericTextFieldListeners(yearField);
        setupNumericTextFieldListeners(monthField);

        yearNumber.setText("No year chosen");
        monthNumber.setText("No month chosen");
    }

    public void selectMonth(ActionEvent actionEvent) throws Exception {
        String year = yearField.getText();
        String month = monthField.getText();
        displayMonth(year, month);
        yearNumber.setText(year);
        monthNumber.setText(month);
    }

    public void displayMonth(String year, String month) throws Exception {
        // Implement the logic to display the month availability here
    }
}