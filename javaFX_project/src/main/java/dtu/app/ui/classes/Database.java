package dtu.app.ui.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * JavaFX App
 */

public class Database {
    private final List<Employee> employeeRepository = new ArrayList<>();
    private final List<Project> projectRepository = new ArrayList<>();
    private EmailServer emailServer;
    private DateServer dateServer = new DateServer();
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

    public void setEmailServer(EmailServer emailServer) {
        this.emailServer = emailServer;
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
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

    public boolean hasEmployeeRegistered(Employee employee) throws Exception {
        if (!employeeRepository.contains(employee)) {
            throw new Exception("Employee does not exist");
        }

        Calendar date = dateServer.getDate();
        return employee.hasRegistered(date);
    }

    public void sendNotification() throws Exception {
        for (Employee employee : employeeRepository) {
            if (!hasEmployeeRegistered(employee)) {
                employee.sendEmailNotification(emailServer);
            }
        }
    }

    public void initializeTestRun() throws Exception {
        Employee employee1 = new Employee(this, "Huba");
        Employee employee2 = new Employee(this, "Abed");
        Employee employee3 = new Employee(this, "Dora");
        Employee employee4 = new Employee(this, "Jama");

        Project project1 = new Project(this, "Project 1", List.of(employee1, employee2, employee3, employee4), employee1);

        Activity activity1 = new Activity(project1, "Activity 1", "10", "1", "10", List.of(employee1, employee2));
        Activity activity2 = new Activity(project1, "Activity 2", "20", "11", "20", List.of(employee2, employee3, employee4));

        // register activities today
        Calendar date1 = Calendar.getInstance();
        employee1.getActivityLog().registerHours(date1, activity1, "5");

        // register activities tomorrow
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DATE, 1);
        employee1.getActivityLog().registerHours(date2, activity2, "5");

        Calendar date3 = Calendar.getInstance();
        date3.add(Calendar.DATE, 2);
        employee1.getActivityLog().registerHours(date3, activity2, "5");

        Calendar date4 = Calendar.getInstance();
        date4.add(Calendar.DATE, 3);
        employee1.getActivityLog().registerHours(date4, activity2, "5");

        Calendar date5 = Calendar.getInstance();
        date5.add(Calendar.DATE, 4);
        employee1.getActivityLog().registerHours(date5, activity2, "5");

        Calendar date6 = Calendar.getInstance();
        date6.add(Calendar.DATE, 5);
        employee1.getActivityLog().registerHours(date6, activity2, "5");

        Calendar date7 = Calendar.getInstance();
        date7.add(Calendar.DATE, 6);
        employee1.getActivityLog().registerHours(date7, activity2, "5");
        employee1.getActivityLog().registerHours(date7, activity1, "5");
        employee1.getActivityLog().registerHours(date7, activity1, "5");
    }
}