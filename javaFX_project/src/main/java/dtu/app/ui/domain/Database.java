package dtu.app.ui.domain;

import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * JavaFX App
 */

public class Database {
    private final List<Employee> employeeRepository = new ArrayList<>();
    private final List<Project> projectRepository = new ArrayList<>();
    private final DateServer dateServer = new DateServer();
    public ProjectInfo selectedProject = null;
    public ActivityInfo selectedActivity = null;

    public List<String> selectedEmployeeLog = new ArrayList<>();

    public EmployeeInfo selectedEmployee = null;
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

    public Project getProject(int id) throws Exception {
        for (Project project : projectRepository) {
            if (project.getID() == id) {
                return project;
            }
        }
        return null;
    }

}