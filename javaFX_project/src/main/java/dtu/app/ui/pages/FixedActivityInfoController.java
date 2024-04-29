package dtu.app.ui.pages;

import dtu.app.ui.domain.FixedActivity;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.FixedActivityInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FixedActivityInfoController extends CommonElementsController {
    @FXML
    private TableView<FixedActivityInfo> fixedActivityTableView;
    @FXML
    private TableColumn<FixedActivityInfo, String> nameColumn;
    @FXML
    private TableColumn<FixedActivityInfo, Integer> startWeekColumn;
    @FXML
    private TableColumn<FixedActivityInfo, Integer> endWeekColumn;
    @FXML
    private TableColumn<FixedActivityInfo, Integer> startYearColumn;
    @FXML
    private TableColumn<FixedActivityInfo, Integer> endYearColumn;

    @FXML
    private TextField activityNameField;
    @FXML
    private TextField startWeekField;
    @FXML
    private TextField endWeekField;
    @FXML
    private TextField startYearField;
    @FXML
    private TextField endYearField;

    @FXML
    private void initialize() throws Exception {
        super.setupNumericTextFieldListeners(2, startWeekField, endWeekField);
        super.setupNumericTextFieldListeners(4, startYearField, endYearField);
        setupLetterTextFieldListeners(activityNameField);


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startWeekColumn.setCellValueFactory(new PropertyValueFactory<>("startWeek"));
        endWeekColumn.setCellValueFactory(new PropertyValueFactory<>("endWeek"));
        startYearColumn.setCellValueFactory(new PropertyValueFactory<>("startYear"));
        endYearColumn.setCellValueFactory(new PropertyValueFactory<>("endYear"));
        fixedActivityTableView.getItems().addAll(App.application.getFixedActivitiesForEmployee(App.application.getSelectedEmployee()));
    }

    @FXML
    private void createFixedActivity() throws Exception {
        EmployeeInfo employee = App.application.getSelectedEmployee();
        String activityName = activityNameField.getText();
        String startWeek = startWeekField.getText();
        String endWeek = endWeekField.getText();
        String startYear = startYearField.getText();
        String endYear = endYearField.getText();
        App.application.createFixedActivity(employee, activityName, startWeek, endWeek, startYear, endYear);
        initialize();
    }
}