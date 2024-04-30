package dtu.app.ui.domain;

import dtu.app.ui.ApplicationProjects;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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

    public boolean hasEmployeeRegistered(Employee employee) throws Exception {
        if (!employeeRepository.contains(employee)) {
            throw new Exception("Employee does not exist");
        }

        Calendar date = dateServer.getDate();
        return employee.hasRegistered(date);
    }

    // Might be wrong now, look at paramters for getActiveActivityCount
//    public void sendEmail() throws Exception {
//        for (Employee e : employeeRepository) {
//            if (!hasEmployeeRegistered(e)) {
//                e.sendEmailNotification("Work", "Register your daily work");
//            }
//            if (e.getActiveActivityCount(dateServer.getWeek(), dateServer.getYear()) > 20) {
//                e.sendEmailNotification("Work", "You're working on too many activities");
//            }
//        }
//    }

//    public void initializeTestRun() throws Exception {
//        Employee employee1 = new Employee(this, "Huba");
//        Employee employee2 = new Employee(this, "Abed");
//        Employee employee3 = new Employee(this, "Dora");
//        Employee employee4 = new Employee(this, "Jama");
//
//        Project project1 = new Project(this, "Project 1", List.of(employee1, employee2, employee3, employee4), employee1);
//
//        Activity activity1 = new Activity(project1, "Activity 1", 10.0, 1, 10, 2024, 2024);
//        Activity activity2 = new Activity(project1, "Activity 2", 20.0, 11, 20, 2024, 2024);
//
////        // email
////        employee1.sendEmailNotification("Work", "Ice cream");
////        employee1.sendEmailNotification("Work", "Ilias er dum");
////        employee2.sendEmailNotification("Work", "Hello Gabriel");
//
//        LocalDate date1 = LocalDate.now();
//        employee1.getActivityLog().registerHours(date1, activity1, 5);
//
//        LocalDate date2 = LocalDate.now().plusDays(1);
//        employee1.getActivityLog().registerHours(date2, activity2, 5);
//
//        LocalDate date3 = LocalDate.now().plusDays(2);
//        employee1.getActivityLog().registerHours(date3, activity2, 5);
//
//        LocalDate date4 = LocalDate.now().plusDays(3);
//        employee1.getActivityLog().registerHours(date4, activity2, 5);
//
//        LocalDate date5 = LocalDate.now().plusDays(4);
//        employee1.getActivityLog().registerHours(date5, activity2, 5);
//
//        LocalDate date6 = LocalDate.now().plusDays(5);
//        employee1.getActivityLog().registerHours(date6, activity2, 5);
//
//        LocalDate date7 = LocalDate.now().plusDays(6);
//        employee1.getActivityLog().registerHours(date7, activity2, 5);
//        employee1.getActivityLog().registerHours(date7, activity1, 5);
//        employee1.getActivityLog().registerHours(date7, activity1, 5);
//    }

}