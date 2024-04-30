package dtu.app.ui.domain;

import java.util.ArrayList;
import java.util.List;

public class Activity extends AbstractActivity{
    private final List<Employee> employees = new ArrayList<>();
    private final Project project;
    private final DateServer dateServer = new DateServer();
    private double budgetHours;
    private double hoursSpent = 0;
    private boolean completed = false;

    public Activity(Project project, String name, Double budgetHours, int startWeek, int endWeek, int startYear, int endYear) {
        this.project = project;
        this.name = name;
        this.budgetHours = budgetHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startYear = startYear;
        this.endYear = endYear;
        project.addActivity(this);
    }

    public void editActivity(Activity activity, String newName, double newBudgetHours, int newStartWeek, int newEndWeek, List<Employee> newEmployees, int newStartYear, int newEndYear) {
        this.name = newName;
        this.budgetHours = newBudgetHours;
        this.startWeek = newStartWeek;
        this.endWeek = newEndWeek;
        this.startYear = newStartYear;
        this.endYear = newEndYear;
        updateEmployees();
    }

    public void addEmployee(Employee e) {
        employees.add(e);
        e.addActivity(this);
    }

    public void updateEmployees() {
        List<Employee> projectEmployees = project.getEmployees();
        employees.removeIf(e -> !projectEmployees.contains(e));
    }

    public void registerHours(double hours) {
        hoursSpent += hours;
    }

    public void switchCompletionStatus() {
        completed = !completed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStartWeek() {
        return startWeek;
    }

    @Override
    public int getEndWeek() {
        return endWeek;
    }

    @Override
    public int getStartYear() {
        return startYear;
    }
    @Override
    public int getEndYear() {
        return endYear;
    }

    public double getBudgetHours() {
        return budgetHours;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    public Project getProject() {
        return project;
    }


    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
    public boolean getCompletedStatus() {
        return completed;
    }

}