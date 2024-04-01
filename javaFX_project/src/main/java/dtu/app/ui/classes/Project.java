package dtu.app.ui.classes;

import java.time.Year;
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

    public int generateID() {
        int currentYear = Year.now().getValue() % 100;
        int serialNumber = 1;

        for (Project p : app.getProjects()) {
            if (p.getID() / 1000 == currentYear) {
                serialNumber = Math.max(serialNumber, p.getID() % 1000 + 1);
            }
        }
        return currentYear * 1000 + serialNumber;
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

    public void assignProjectLeader(Employee employee) throws Exception {
        if (employee != null) {
            for (Employee e : employees) {
                if (e.getInitials().equals(employee.getInitials())) {
                    this.projectLeader = employee;
                    return;
                }
            }
        }
    }

    public void addActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("No activity given");
        }
        activities.add(activity);
    }

    public void editProject(String newName, Employee newProjectLeader) {
        this.name = newName;
        this.projectLeader = newProjectLeader;
        updateActivityEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void clearEmployees() {
        employees.clear();
    }

    private void updateActivityEmployees() {
        for (Activity a : activities) {
            a.updateEmployees();
        }
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

    public Activity getActivity(String name) {
        for (Activity a : activities) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public int getActivitiesSize() {
        return activities.size();
    }

    public int getEmployeesSize() {
        return employees.size();
    }

    @Override
    public String toString() {
        return "Project ID: " + ID + "\n" +
                "Project Name: " + name + "\n";
    }

}