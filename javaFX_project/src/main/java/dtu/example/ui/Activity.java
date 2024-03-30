package dtu.example.ui;

import java.util.List;

public class Activity {
    private final Project project;
    private String name;
    private int budgetHours;
    private int startWeek;
    private int endWeek;

    public int hoursSpent = 0;

    public Activity(Project project, String name, String budgetHours, String startWeek, String endWeek) {
        validateProject(project);
        validateName(name, project);
        this.budgetHours = parseAndValidateHours(budgetHours);
        this.startWeek = parseAndValidateWeek(startWeek, "Start week value error");
        this.endWeek = parseAndValidateWeek(endWeek, "End week value error");
        this.project = project;
        this.name = name;
        project.appendActivity(this);
    }

    private void validateProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
    }

    private void validateName(String name, Project project) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Activity name cannot be empty");
        }
        for (Activity a : project.getActivities()) {
            if (a.getName().equals(name)) {
                throw new IllegalArgumentException("Activity with this name already exists in the project");
            }
        }
    }

    private int parseAndValidateHours(String budgetHours) {
        try {
            int hours = Integer.parseInt(budgetHours);
            if (hours < 0) {
                throw new IllegalArgumentException("Budget hours cannot be negative");
            }
            return hours;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Budget hours value error");
        }
    }

    private int parseAndValidateWeek(String week, String errorMessage) {
        try {
            int weekNumber = Integer.parseInt(week);
            if (weekNumber < 0 || weekNumber > 52) {
                throw new IllegalArgumentException(errorMessage);
            }
            return weekNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
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

    public String toString() {
        return "Activity: " + name;
    }
}