package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Project> projects;
    private List<Employee> employees;

    // Constructor
    public Company() {
        this.projects = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    // Methods
    public void addProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }


    // Add any additional methods as needed

    @Override
    public String toString() {
        return "Company{" +
                "projects=" + projects +
                ", employees=" + employees +
                '}';
    }
}

