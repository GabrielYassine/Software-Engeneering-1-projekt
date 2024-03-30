package dtu.example.ui.pages;

import dtu.example.ui.Employee;
import dtu.example.ui.Project;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsController {

    @FXML
    public TableView<Project> projectsTableView;
    @FXML
    public TableColumn<Project, Integer> idColumn;
    @FXML
    public TableColumn<Project, String> nameColumn;
    @FXML
    private TextField projectNameField;
    @FXML
    private ComboBox<Employee> employeesComboBox;
    @FXML
    private ListView<Employee> selectedEmployeesListView;
    @FXML
    private Button addEmployeeButton;
    @FXML
    public Button removeEmployeeButton;

    @FXML
    private void initialize() throws Exception {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        projectsTableView.getItems().addAll(App.database.getProjects());

        projectsTableView.setRowFactory(tv -> {
            TableRow<Project> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Project clickedRow = row.getItem();
                    App.database.setSelectedProject(clickedRow);
                    try {
                        App.setRoot("projectInfo");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });

        List<Employee> employeesCopy = new ArrayList<>(App.database.getEmployees());
        employeesComboBox.getItems().addAll(employeesCopy);

        addEmployeeButton.setOnAction(event -> {
            Employee selectedEmployee = employeesComboBox.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                selectedEmployeesListView.getItems().add(selectedEmployee);
                employeesComboBox.getItems().remove(selectedEmployee);
            }
        });

        removeEmployeeButton.setOnAction(event -> {
            Employee selectedEmployee = selectedEmployeesListView.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                selectedEmployeesListView.getItems().remove(selectedEmployee);
                employeesComboBox.getItems().add(selectedEmployee);
            }
        });
    }

    @FXML
    private void createProject() throws IOException {
        String projectName = projectNameField.getText();
        try {
            List<Employee> employees = new ArrayList<>(selectedEmployeesListView.getItems());
            Project newProject = new Project(App.database, projectName, employees);
            System.out.println("Created project: " + newProject.getName() + " with employees: " + newProject.getEmployees());
            projectsTableView.getItems().add(newProject);
            resetEmployeeSelection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void resetEmployeeSelection() {
        projectNameField.clear();
        selectedEmployeesListView.getItems().clear();
        employeesComboBox.getItems().clear();
        List<Employee> allEmployees = new ArrayList<>(App.database.getEmployees());
        employeesComboBox.getItems().addAll(allEmployees);
    }


}