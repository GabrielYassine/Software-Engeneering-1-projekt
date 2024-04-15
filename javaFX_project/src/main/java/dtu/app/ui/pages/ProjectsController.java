package dtu.app.ui.pages;

import dtu.app.ui.classes.Employee;
import dtu.app.ui.classes.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsController extends CommonElementsController {
    @FXML
    public TableView<Project> projectsTableView;
    @FXML
    private TableColumn<Project, Integer> projectCompletedColumn;
    @FXML
    private TableColumn<Project, Integer> idColumn;
    @FXML
    private TableColumn<Project, String> nameColumn;
    @FXML
    private TableColumn<Project, Integer> employeeSizeColumn;
    @FXML
    private TableColumn<Project, Integer> activitySizeColumn;
    @FXML
    public TextField activityNameField;
    @FXML
    public ListView<Employee> employeesListView;
    @FXML
    public ComboBox<Employee> projectLeaderComboBox;
    @FXML
    private ListView<Employee> selectedEmployeesListView;

    @FXML
    private void initialize() throws Exception {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeSizeColumn.setCellValueFactory(new PropertyValueFactory<>("employeesSize"));
        activitySizeColumn.setCellValueFactory(new PropertyValueFactory<>("activitiesSize"));
        projectCompletedColumn.setCellValueFactory(new PropertyValueFactory<>("activitiesCompleted"));

        projectsTableView.getItems().addAll(App.database.getProjects());
        employeesListView.getItems().addAll(App.database.getEmployees());
        projectLeaderComboBox.setItems(selectedEmployeesListView.getItems());

        setRowClickAction(projectsTableView, clickedRow -> {
            App.database.setSelectedProject(clickedRow);
            try {
                App.setRoot("projectInfo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void createProject() throws IOException {
        String projectName = activityNameField.getText();
        Employee projectLeader = projectLeaderComboBox.getSelectionModel().getSelectedItem();

        try {
            List<Employee> employees = new ArrayList<>(selectedEmployeesListView.getItems());
            Project newProject = new Project(App.database, projectName, employees, projectLeader);
            projectsTableView.getItems().add(newProject);
            resetActivityCreationFields();
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

    private void resetActivityCreationFields() {
        super.resetActivityCreationFields(activityNameField, null, null, null, employeesListView, selectedEmployeesListView, null, null, null);
    }
}