package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private String start_Week;
    private String end_Week;
    private int budgeted_hours;
    private int time_used_on;
    private List<Integer> assignedEmployees;

    // Constructor
    public Activity(String name, String start_date, String end_date, int budgeted_hours, int time_used_on) {
        this.name = name;
        this.start_Week = start_date;
        this.end_Week = end_date;
        this.budgeted_hours = budgeted_hours;
        this.time_used_on = time_used_on;
        this.assignedEmployees = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_Week() {
        return start_Week;
    }

    public void setStart_Week(String start_Week) {
        this.start_Week = start_Week;
    }

    public String getEnd_Week() {
        return end_Week;
    }

    public void setEnd_Week(String end_Week) {
        this.end_Week = end_Week;
    }

    public int getBudgeted_hours() {
        return budgeted_hours;
    }

    public void setBudgeted_hours(int budgeted_hours) {
        this.budgeted_hours = budgeted_hours;
    }

    public int getTime_used_on() {
        return time_used_on;
    }

    public void setTime_used_on(int time_used_on) {
        this.time_used_on = time_used_on;
    }

    public List<Integer> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Integer> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    @Override
    public String toString() {
        return "Activity{" +
                ", name='" + name + '\'' +
                ", start_date='" + start_Week + '\'' +
                ", end_date='" + end_Week + '\'' +
                ", budgeted_hours=" + budgeted_hours +
                ", assignedEmployees=" + assignedEmployees +
                '}';
    }
}

