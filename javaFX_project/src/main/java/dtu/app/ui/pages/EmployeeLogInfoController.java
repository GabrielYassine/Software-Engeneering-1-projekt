package dtu.app.ui.pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class EmployeeLogInfoController extends CommonElementsController{

    public String projectID;
    public String activityName;
    public String date;
    public String employeeInitials;
    public String oldHours;
    public Label projectIDValue;
    public Label activityNameValue;
    public Label activityHoursValue;
    @FXML
    private Label projectIDLabel;
    @FXML
    private Label activityNameLabel;
    @FXML
    private TextField hoursTextField;

    public void initialize() throws Exception {
        projectID = App.application.getSelectedEmployeeLog().get(0);
        activityName = App.application.getSelectedEmployeeLog().get(1);
        date = App.application.getSelectedEmployeeLog().get(2);
        employeeInitials = App.application.getSelectedEmployeeLog().get(3);
        oldHours = App.application.getSelectedEmployeeLogHours(projectID, activityName, date, employeeInitials);

        projectIDValue.setText(projectID);
        activityNameValue.setText(activityName);
        hoursTextField.setText(oldHours);
    }

    public void saveChanges(ActionEvent actionEvent) throws Exception {
        String newHours = hoursTextField.getText();
        App.application.editEmployeeLogHours(projectID, activityName, date, employeeInitials, oldHours, newHours);
        goBack();
    }

    public void cancelEdit(ActionEvent actionEvent) throws IOException {
        goBack();
    }

    public void refreshPage() throws Exception {
        TextField[] textFields = new TextField[]{hoursTextField};
        DatePicker[] datePickers = new DatePicker[]{};
        ListView<?>[] listViews = new ListView<?>[]{};
        TableView<?>[] tableViews = new TableView<?>[]{};
        clearFields(textFields,datePickers, listViews, tableViews);
        initialize();
    }
}