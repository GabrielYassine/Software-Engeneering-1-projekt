package dtu.app.ui.domain;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private final Database database;
    private final int ID;
    private String name;
    private final List<Activity> activities;
    private final List<Employee> employees;
    private Employee projectLeader;

    public Project(Database database, String name, List<Employee> employees, Employee projectLeader) {
        this.database = database;
        this.ID = generateID();
        this.name = name;
        this.activities = new ArrayList<>();
        this.employees = new ArrayList<>(employees);
        this.projectLeader = projectLeader;
        database.appendProject(this);
    }
    public int generateID() {
        int currentYear = Year.now().getValue() % 100;
        int serialNumber = 1;

        for (Project p : database.getProjects()) {
            if (p.getID() / 1000 == currentYear) {
                serialNumber = Math.max(serialNumber, p.getID() % 1000 + 1);
            }
        }
        return currentYear * 1000 + serialNumber;
    }

    public void addActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("No activity given");
        }
        activities.add(activity);
    }

    public void editProject(String newName, String projectLeaderInitials, List<Employee> newEmployees) throws Exception {
        this.name = newName;
        if (projectLeaderInitials != null) {
            if (projectLeaderInitials.equals("None")) {
                projectLeader = null;
            } else {
                this.projectLeader = database.getEmployee(projectLeaderInitials);
            }
        }
        employees.clear();
        employees.addAll(newEmployees);
        updateData();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    private void updateData() {
        for (Activity a : activities) {
            a.updateEmployees();
        }
        if (projectLeader != null) {
            boolean found = false;
            for (Employee e : employees) {
                if (e.getInitials().equals(projectLeader.getInitials())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                projectLeader = null;
            }
        }
    }

    public void switchActivityCompletionStatus(String activityName) {
        getActivity(activityName).switchCompletionStatus();
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

    public String getCompletionStatus() {
        int number = 0;
        for (Activity a : activities) {
            if (a.getCompletedStatus()) {
                number++;
            }
        }
        return number + "/" + activities.size();
    }

    @Override
    public String toString() {
        return "Project ID: " + ID + "\n" +
                "Project Name: " + name + "\n" +
                "Project Leader: " + projectLeader + "\n" +
                "Employees: " + employees + "\n" +
                "Activities: " + activities;
    }
}