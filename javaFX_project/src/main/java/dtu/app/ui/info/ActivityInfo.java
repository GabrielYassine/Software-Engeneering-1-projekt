package dtu.app.ui.info;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Illias Chaykh
 */
public class ActivityInfo {
    private final String name;
    private final double budgetHours;
    private final int startWeek;
    private final int endWeek;
    private final int startYear;
    private final int endYear;
    private final double hoursSpent;
    private final boolean completed;
    private final List<Employee> employees;

    public ActivityInfo(Activity activity) {
        this.name = activity.getName();
        this.budgetHours = activity.getBudgetHours();
        this.startWeek = activity.getStartWeek();
        this.endWeek = activity.getEndWeek();
        this.startYear = activity.getStartYear();
        this.endYear = activity.getEndYear();
        this.hoursSpent = activity.getHoursSpent();
        this.completed = activity.getCompletedStatus();
        this.employees = new ArrayList<>(activity.getEmployees());
    }

    public String getName() {
        return name;
    }

    public double getBudgetHours() {
        return budgetHours;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public String getCompletionStatus() {
        return completed ? "Completed" : "Not completed";
    }

    //////////////////////////// GETTERS FOR GUI ////////////////////////////

    public int getEmployeesSize() {
        return employees.size();
    }

    public String getStatus() {
        return hoursSpent + "/" + budgetHours;
    }

}