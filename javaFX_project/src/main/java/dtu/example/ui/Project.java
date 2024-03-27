package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final App app;
    private int ID;
    private String name;
    private List<Activity> activities;
    private List<Employee> employees;
    private Employee projectLeader;

    public Project(App app, String name, List<Employee> employees) {
        this.app = app;
        this.ID = generateID();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("No project name given");
        }
        this.name = name;
        this.activities = new ArrayList<>();
        this.employees = employees;
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
        return activities;
    }
    public void assignToProject(Employee employee) throws Exception{
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
        for (Employee e : employees) {
            if (e.getInitials().equals(employee.getInitials())) {
                this.projectLeader = employee;
                return;
            }
        }
        throw new Exception("Employee not assigned to project");
    }
    public void appendActivity(Activity activity) {activities.add(activity);
    }
}