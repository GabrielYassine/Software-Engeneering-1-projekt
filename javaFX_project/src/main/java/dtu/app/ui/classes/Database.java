package dtu.app.ui.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */

public class Database {
    private final List<Employee> employeeRepository = new ArrayList<>();
    private final List<Project> projectRepository = new ArrayList<>();
    public Project selectedProject;
    public Activity selectedActivity;

    public void appendEmployee(Employee employee) {
        employeeRepository.add(employee);
    }

    public void appendProject(Project project) {
        projectRepository.add(project);
    }

    public void setSelectedProject(Project project) {
        this.selectedProject = project;
    }

    public void setSelectedActivity(Activity activity) {
        this.selectedActivity = activity;
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
        throw new Exception("Project with ID '" + id + "' not found");
    }

    public void initializeTestRun() {
        Employee employee1 = new Employee(this, "Huba");
        Employee employee2 = new Employee(this, "Abed");
        Employee employee3 = new Employee(this, "Dora");
        Employee employee4 = new Employee(this, "Jama");

        Project project1 = new Project(this, "Project 1", List.of(employee1, employee2, employee3, employee4), employee1);

        Activity activity1 = new Activity(project1, "Activity 1", "10", "1", "10", List.of(employee1, employee2));
        Activity activity2 = new Activity(project1, "Activity 2", "20", "11", "20", List.of(employee2, employee3, employee4));
    }

}