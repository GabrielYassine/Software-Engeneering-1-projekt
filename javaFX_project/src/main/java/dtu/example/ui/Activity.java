package dtu.example.ui;

import java.util.List;

public class Activity {
    private final Project project;
    private String name;
    private int budgetHours;
    private int startWeek;
    private int endWeek;

    public Activity(Project project, String name, int budgetHours, int startWeek, int endWeek) {
        if (project == null || name == null || name.isEmpty() || budgetHours < 0 || startWeek < 0 || endWeek < 0) {
            throw new IllegalArgumentException("Can't create activity: Insufficient or incorrect information given");
        }
        for (Activity a : project.getActivities()) {
            if (a.getName().equals(name)) {
                throw new IllegalArgumentException("Activity with this name already exists in the project");
            }
        }
        this.project = project;
        this.name = name;
        this.budgetHours = budgetHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        project.appendActivity(this);
    }

    public String getName() {
        return name;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getBudgetHours() {
        return budgetHours;
    }

    public void editActivity(String newName, int newBudgetHours, int newStartWeek, int newEndWeek) {
        if (newName == null || newName.isEmpty() || newBudgetHours < 0 || newStartWeek < 0 || newEndWeek < 0) {
            throw new IllegalArgumentException("Can't edit activity: Insufficient or incorrect information given");
        }
        for (Activity a : project.getActivities()) {
            if (a.getName().equals(newName) && !this.name.equals(newName)) {
                throw new IllegalArgumentException("Activity with this name already exists in the project");
            }
        }
        this.name = newName;
        this.budgetHours = newBudgetHours;
        this.startWeek = newStartWeek;
        this.endWeek = newEndWeek;
    }
}