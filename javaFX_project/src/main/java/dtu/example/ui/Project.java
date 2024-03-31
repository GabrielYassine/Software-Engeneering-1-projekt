package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final Database app;
    private final int ID;
    private String name;
    private final List<Activity> activities;
    private final List<Employee> employees;
    private Employee projectLeader;

    public Project(Database app, String name, List<Employee> employees) {
        this.app = app;
        this.ID = generateID();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("No project name given");
        }
        this.name = name;
        this.activities = new ArrayList<>();
        this.employees = new ArrayList<>(employees);
        this.projectLeader = null;
        app.appendProject(this);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }

    public void assignToProject(Employee employee) throws Exception {
        if (employee == null) {
            throw new IllegalArgumentException("No employee given");
        }
        for (Employee e : employees) {
            if (e.getInitials().equals(employee.getInitials())) {
                throw new Exception("Employee already assigned to project");
            }
        }
        employees.add(employee);
    }

    public int generateID() {
        int defaultID = 24001;
        if (app.getProjects().isEmpty()) {
            return defaultID;
        } else {
            int maxID = 0;
            for (Project p : app.getProjects()) {
                if (p.getID() > maxID) {
                    maxID = p.getID();
                }
            }
            return maxID + 1;
        }
    }

    public void assignProjectLeader(Employee employee) throws Exception {
        if (employee == null) {
            throw new IllegalArgumentException("No employee given");
        }
        for (Employee e : employees) {
            if (e.getInitials().equals(employee.getInitials())) {
                this.projectLeader = employee;
                return;
            }
        }
        throw new Exception("Employee not assigned to project");
    }

    public void appendActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("No activity given");
        }
        activities.add(activity);
    }

    public Activity findActivityByName(String name) {
        for (Activity a : activities) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public void clearEmployees() {
        employees.clear();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void editProject(String newName, Employee newProjectLeader) {
        this.name = newName;
        this.projectLeader = newProjectLeader;
        updateActivityEmployees();
    }

    private void updateActivityEmployees() {
        for (Activity a : activities) {
            a.updateEmployees();
        }
    }

    @Override
    public String toString() {
        return "Project ID: " + ID + "\n" +
                "Project Name: " + name + "\n";
    }

}