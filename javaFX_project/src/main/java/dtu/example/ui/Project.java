package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private List<Activity> activities;
    private Employee projectLeader;

    // Constructor
    public Project(int id, String name) {
        this.id = id;
        this.name = name;
        this.activities = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", activities=" + activities +
                ", projectLeader=" + (projectLeader != null ? projectLeader.getName() : "None") +
                '}';
    }
}