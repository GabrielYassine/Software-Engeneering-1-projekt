package dtu.app.ui.pages;

import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsController extends CommonElementsController {
    @FXML
    public TableView<ProjectInfo> projectsTableView;
    @FXML
    private TableColumn<ProjectInfo, Integer> projectCompletedColumn;
    @FXML
    private TableColumn<ProjectInfo, Integer> idColumn;
    @FXML
    private TableColumn<ProjectInfo, String> nameColumn;
    @FXML
    private TableColumn<ProjectInfo, Integer> employeeSizeColumn;
    @FXML
    private TableColumn<ProjectInfo, Integer> activitySizeColumn;
    @FXML
    public TextField activityNameField;
    @FXML
    public ListView<EmployeeInfo> employeesListView;
    @FXML
    public ComboBox<EmployeeInfo> projectLeaderComboBox;
    @FXML
    private ListView<EmployeeInfo> selectedEmployeesListView;

    @FXML
    private void initialize() throws Exception {
        super.setupLetterTextFieldListeners(activityNameField);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        employeeSizeColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeesSize"));
        activitySizeColumn.setCellValueFactory(new PropertyValueFactory<>("ActivitiesSize"));
        projectCompletedColumn.setCellValueFactory(new PropertyValueFactory<>("ActivitiesCompleted"));

        projectsTableView.getItems().addAll(App.application.getProjectsInApp());
        employeesListView.getItems().addAll(App.application.getEmployeesInApp());
        projectLeaderComboBox.setItems(selectedEmployeesListView.getItems());

        setRowClickAction(projectsTableView, clickedRow -> {
            App.application.setProject(clickedRow);
            try {
                App.setRoot("projectInfo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void createProject() throws IOException {;
        try {
            String projectName = activityNameField.getText();
            EmployeeInfo projectLeader = projectLeaderComboBox.getSelectionModel().getSelectedItem();
            List<EmployeeInfo> employees = new ArrayList<>(selectedEmployeesListView.getItems());
            App.application.createProject(projectName, employees, projectLeader);

            refreshPage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void addEmployee(ActionEvent actionEvent) {
        super.addEmployee(employeesListView, selectedEmployeesListView);
    }

    @FXML
    public void removeEmployee(ActionEvent actionEvent) {
        super.removeEmployee(employeesListView, selectedEmployeesListView);
    }

    public void refreshPage() throws Exception {
        TextField[] textFields = new TextField[]{activityNameField};
        DatePicker[] datePickers = new DatePicker[]{};
        ListView<?>[] listViews = new ListView<?>[]{employeesListView, selectedEmployeesListView};
        TableView<?>[] tableViews = new TableView<?>[]{projectsTableView};
        clearFields(textFields,datePickers, listViews, tableViews);
        initialize();
    }
}