package dtu.example.ui.pages;

import dtu.example.ui.Activity;
import dtu.example.ui.Employee;
import dtu.example.ui.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsController extends CommonElementsController {
    @FXML
    public TableView<Project> projectsTableView;
    @FXML
    public TableColumn<Project, Integer> idColumn;
    @FXML
    public TableColumn<Project, String> nameColumn;
    public TextField activityNameField;
    public ListView<Employee> employeesListView;
    public ComboBox<Employee> projectLeaderComboBox;
    @FXML
    private ListView<Employee> selectedEmployeesListView;
    @FXML
    public Button removeEmployeeButton;

    @FXML
    private void initialize() throws Exception {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

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
            Project newProject = new Project(App.database, projectName, employees);
            newProject.assignProjectLeader(projectLeader);

            System.out.println("Created project: " + newProject.getName() + " with employees: " + newProject.getEmployees());
            projectsTableView.getItems().add(newProject);
            resetActivityCreationFields();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEmployee(ActionEvent actionEvent) {
        super.addEmployee(employeesListView, selectedEmployeesListView);
    }

    public void removeEmployee(ActionEvent actionEvent) {
        super.removeEmployee(employeesListView, selectedEmployeesListView);
    }

    private void resetActivityCreationFields() {
        super.resetActivityCreationFields(activityNameField, null, null, null, employeesListView, selectedEmployeesListView, null, null, null);
    }
}