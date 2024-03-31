package dtu.example.ui;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private List<Employee> employees;
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

    private void validateName(String newName, Project project) {
        List<Activity> activities = project.getActivities();
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("Activity name cannot be empty");
        }
        for (Activity a : activities) {
            if (a.getName().equals(newName)) {
                throw new IllegalArgumentException("Activity with this name already exists in the project");
            }
        }
    }

    private void validateName(String newName, Project project, Activity activityBeingEdited) {
        List<Activity> activities = project.getActivities();
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("Activity name cannot be empty");
        }
        for (Activity a : activities) {
            if (a != activityBeingEdited && a.getName().equals(newName)) {
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
            throw new IllegalArgumentException("Budget hours cannot be empty");
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

    public void editActivity(String newName, String newBudgetHours, String newStartWeek, String newEndWeek) {
        validateName(newName, project, this);
        this.name = newName;
        this.budgetHours = parseAndValidateHours(newBudgetHours);
        this.startWeek = parseAndValidateWeek(newStartWeek, "Start week value error");
        this.endWeek = parseAndValidateWeek(newEndWeek, "End week value error");
    }

    public String toString() {
        return "Activity: " + name;
    }

    public List<Employee> getEmployees() {
        if (employees == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(employees);
    }

    public void addEmployee(Employee e) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(e);
    }

    public void clearEmployees() {
        employees = new ArrayList<>();
    }

    public void updateEmployees() {
        if (employees == null) {
            return;
        }
        List<Employee> projectEmployees = project.getEmployees();
        employees.removeIf(e -> !projectEmployees.contains(e));
    }
}