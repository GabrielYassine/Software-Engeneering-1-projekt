package dtu.app.ui.info;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {
    private final int id;
    private final String name;
    private final Employee projectLeader;
    private final List<Activity> activities;
    private final List<Employee> employees;

    public ProjectInfo(Project project) {
        this.id = project.getID();
        this.name = project.getName();
        this.projectLeader = project.getProjectLeader();
        this.activities = new ArrayList<>(project.getActivities());
        this.employees = new ArrayList<>(project.getEmployees());
    }

    public String getName() {
        return name;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public List<String> getEmployeeInitials() {
        List<String> employeeInitials = new ArrayList<>();
        for (Employee employee : employees) {
            employeeInitials.add(employee.getInitials());
        }
        return employeeInitials;
    }

    public int getID() {
        return id;
    }

    //////////////////////////// GETTERS FOR GUI ////////////////////////////

    public String getCompletionStatus() {
        int number = 0;
        for (Activity a : activities) {
            if (a.getCompletedStatus()) {
                number++;
            }
        }
        return number + "/" + activities.size();
    }

    public int getActivitiesSize() {
        return activities.size();
    }

    public int getEmployeesSize() {
        return employees.size();
    }

    public List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

}