package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class ProjectLeader {
    private int id;
    private String name;
    private List<Project> managedProjects;

    // Constructor
    public ProjectLeader(int id, String name) {
        this.id = id;
        this.name = name;
        this.managedProjects = new ArrayList<>();
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

    // Methods
    public void addManagedProject(Project project) {
        managedProjects.add(project);
    }

    public void removeManagedProject(Project project) {
        managedProjects.remove(project);
    }

    public List<Project> getManagedProjects() {
        return managedProjects;
    }

    @Override
    public String toString() {
        return "ProjectLeader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managedProjects=" + managedProjects +
                '}';
    }
}

