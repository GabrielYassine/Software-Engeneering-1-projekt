package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private List<Activity> activities;
    private List<Employee> employees;
    private Employee projectLeader;

    public Project(int projectID, String name, Employee employee) {
        this.id = projectID;
        this.name = name;
        this.activities = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.projectLeader = null;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

}