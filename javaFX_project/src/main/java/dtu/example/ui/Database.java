package dtu.example.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */

public class Database {
    private List<Employee> employeeRepository = new ArrayList<>();
    private List<Project> projectRepository = new ArrayList<>();
    public Project selectedProject;
    public Activity selectedActivity;

    public void appendEmployee(Employee employee) {
        employeeRepository.add(employee);
    }

    public void appendProject(Project project) {
        projectRepository.add(project);
    }

    public Employee findEmployeeByInitials(String initials) throws Exception {
        for (Employee employee : employeeRepository) {
            if (employee.getInitials().equals(initials)) {
                return employee;
            }
        }
        throw new Exception("Employee with initials '" + initials + "' not found");
    }

    public Project getProjectWithID(int id) throws Exception {
        for (Project project : projectRepository) {
            if (project.getID() == id) {
                return project;
            }
        }
        throw new Exception("Project with ID '" + id + "' not found");
    }
    public List<Project> getProjects() {
        return projectRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository;
    }
    

    public void setSelectedProject(Project project) {
        this.selectedProject = project;
    }

    public void setSelectedActivity(Activity activity) {
        this.selectedActivity = activity;
    }

    public void initializeTestRun() {
        Employee employee1 = new Employee(this, "Huba");
        Employee employee2 = new Employee(this, "Abcd");
        Employee employee3 = new Employee(this, "Efgh");
        Employee employee4 = new Employee(this, "Hijk");
        Employee employee5 = new Employee(this, "Lmno");
        Employee employee6 = new Employee(this, "Pqrs");
        Employee employee7 = new Employee(this, "Tuvw");
        Employee employee8 = new Employee(this, "Xyzz");
        Project project1 = new Project(this, "Project 1", List.of(employee1, employee2, employee3));
        Project project2 = new Project(this, "Project 2", List.of(employee4, employee5, employee6));
        Project project3 = new Project(this, "Project 3", List.of(employee7, employee8));
        Activity activity1 = new Activity(project1, "Activity 1", "10", "1", "10");
        Activity activity2 = new Activity(project1, "Activity 2", "20", "1", "10");
        Activity activity3 = new Activity(project2, "Activity 3", "30", "1", "10");
        Activity activity4 = new Activity(project2, "Activity 4", "40", "1", "10");
        Activity activity5 = new Activity(project3, "Activity 5", "50", "1", "10");
        Activity activity6 = new Activity(project3, "Activity 6", "60", "1", "10");
        activity1.addEmployee(employee1);
        activity1.addEmployee(employee2);
    }

}