package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private final App app;
    private int id;
    private String name;
    private List<Activity> activities;
    private List<Employee> employees;
    private Employee projectLeader;

    public Project(App app, String name, List<Employee> employees) {
        this.app = app;
        this.generateID();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("No project name given");
        }
        this.activities = new ArrayList<>();
        this.employees = employees;
        this.projectLeader = null;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
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



}