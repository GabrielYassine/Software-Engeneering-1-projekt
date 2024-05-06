package dtu.app.ui.domain;

import dtu.app.ui.database.Database;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mathias Bødkerholm Dalsgaard
 */
public class Project {
    private final Database database;
    private final int ID;
    private String name;
    private List<Activity> activities;
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

    private int generateID() {
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
        activities.add(activity);
    }

    public void editProject(String newName, String projectLeaderInitials, List<Employee> newEmployees) throws Exception {
        this.name = newName;
        this.projectLeader = (projectLeaderInitials == null || projectLeaderInitials.equals("None")) ? null : database.getEmployee(projectLeaderInitials);
        employees.clear();
        employees.addAll(newEmployees);
        updateEmployees();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void updateEmployees() {
        // Preconditions
        assert employees != null;

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
        // Postconditions
        assert projectLeader == null || employees.contains(projectLeader);
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
        return activities.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
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
}