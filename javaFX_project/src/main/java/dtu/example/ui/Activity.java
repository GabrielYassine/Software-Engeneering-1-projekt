package dtu.example.ui;

import java.util.List;

public class Activity {
    private final Project project;
    private String name;
    private int budgetHours;
    private int startWeek;
    private int endWeek;
    private List<Employee> employees;

    public Activity(Project project, String name, int budgetHours, int startWeek, int endWeek) {
        this.project = project;
        this.name = name;
        this.budgetHours = budgetHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        project.appendActivity(this);
    }
}

