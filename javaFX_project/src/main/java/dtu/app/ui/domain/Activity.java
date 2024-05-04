package dtu.app.ui.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity extends AbstractActivity{
    private final List<Employee> employees = new ArrayList<>();
    private final Project project;
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

    public void editActivity(String newName, double newBudgetHours, int newStartWeek, int newEndWeek, int newStartYear, int newEndYear) {
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

    public Map<Integer, List<Integer>> getWeeksInInterval() {
        Map<Integer, List<Integer>> weeks = new HashMap<>();
        for (int year = startYear; year <= endYear; year++) {

            int start = year == startYear ? startWeek : 1;
            int end = year == endYear ? endWeek : 52;

            for (int week = start; week <= end; week++) {
                weeks.computeIfAbsent(year, k -> new ArrayList<>()).add(week);
            }
        }
        return weeks;
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