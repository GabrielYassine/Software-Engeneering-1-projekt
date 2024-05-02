package dtu.app.ui.domain;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * JavaFX App
 */

public class Database {
    private final List<Employee> employeeRepository = new ArrayList<>();
    private final List<Project> projectRepository = new ArrayList<>();
    private final ProjectApp projectApp;
    private ProjectInfo selectedProject = null;
    private ActivityInfo selectedActivity = null;
    private List<String> selectedEmployeeLog = new ArrayList<>();
    private EmployeeInfo selectedEmployee = null;

    public Database(ProjectApp projectApp) {
        this.projectApp = projectApp;
    }

    public void appendEmployee(Employee employee) {
        employeeRepository.add(employee);
    }

    public void appendProject(Project project) {
        projectRepository.add(project);
    }

    public void setSelectedProject(ProjectInfo project) {
        this.selectedProject = project;
    }

    public ProjectInfo getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedActivity(ActivityInfo activity) {
        this.selectedActivity = activity;
    }

    public ActivityInfo getSelectedActivity() {
        return selectedActivity;
    }

    public void setSelectedEmployeeLog(List<String> logDetails) {
        this.selectedEmployeeLog = logDetails;
    }

    public List<String> getSelectedEmployeeLog() {
        return selectedEmployeeLog;
    }

    public void setSelectedEmployee(EmployeeInfo employee) {
        this.selectedEmployee = employee;
    }

    public EmployeeInfo getSelectedEmployee() {
        return selectedEmployee;
    }


    public List<Project> getProjects() {
        return projectRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository;
    }

    public Employee getEmployee(String initials) throws Exception {
        for (Employee employee : employeeRepository) {
            if (employee.getInitials().equals(initials)) {
                return employee;
            }
        }
        return null;
    }

    public Project getProject(int id) {
        Project p = null;
        for (Project project : projectRepository) {
            if (project.getID() == id) {
                p = project;
            }
        }
        return p;
    }

    public void initializeTestData() throws Exception {
        Employee employee1 = projectApp.createEmployee("Huba");
        Employee employee2 = projectApp.createEmployee("Abed");
        Employee employee3 = projectApp.createEmployee("Nico");

        EmployeeInfo employeeInfo1 = new EmployeeInfo(employee1);
        EmployeeInfo employeeInfo2 = new EmployeeInfo(employee2);
        EmployeeInfo employeeInfo3 = new EmployeeInfo(employee3);

        Project project1 = projectApp.createProject("NyProject", List.of(employeeInfo1, employeeInfo2, employeeInfo3), employeeInfo1);
        ProjectInfo projectInfo1 = new ProjectInfo(project1);

        Activity activity1 = projectApp.createActivity(projectInfo1, "NyActivity", "10", "5","8", List.of(employeeInfo1), "2024", "2024");
    }
}