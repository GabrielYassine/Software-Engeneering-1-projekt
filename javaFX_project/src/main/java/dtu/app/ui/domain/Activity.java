package dtu.app.ui.domain;

import java.util.ArrayList;
import java.util.List;

public class Activity extends AbstractActivity{
    private List<Employee> employees;
    private final Project project;
    private final DateServer dateServer = new DateServer();
    private double budgetHours;
    private double hoursSpent = 0;
    private boolean completed = false;

    public Activity(Project project, String name, Double budgetHours, int startWeek, int endWeek, List<Employee> employees, int startYear, int endYear) {
        this.project = project;
        this.name = name;
        this.budgetHours = budgetHours;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startYear = startYear;
        this.endYear = endYear;
        this.employees = new ArrayList<>(employees);
        addEmployees(employees);
        project.addActivity(this);
    }

    // Method overload because there are two different cases where the name validation is needed.

    private double parseAndValidateHours(String budgetHours) {
        try {
            double hours = Double.parseDouble(budgetHours);
            return hours;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Budget hours missing");
        }
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
        int currentWeek = dateServer.getWeek();
        int currentYear = dateServer.getYear();
        int currentMonth = dateServer.getMonth();

        if (employees == null) {
            employees = new ArrayList<>();
        }

        if (e.getActiveActivityCount(currentYear, currentMonth, currentWeek) < 20) {
            employees.add(e);
            e.addActivity(this);
        }
    }

    public void addEmployees(List<Employee> PotentialEmployees) {
        int currentWeek = dateServer.getWeek();
        int currentYear = dateServer.getYear();
        int currentMonth = dateServer.getMonth();

        if (employees == null) {
            employees = new ArrayList<>();
        }

        for (Employee e : PotentialEmployees) {
            if (e.getActiveActivityCount(currentYear, currentMonth, currentWeek) <= 20) {
                employees.add(e);
                e.addActivity(this);
            }
        }
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

    public void registerHours(String hours) {
        double hoursDouble = parseAndValidateHours(hours);
        hoursSpent += hoursDouble;
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
        if (employees == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(employees);
    }

    public int getEmployeesSize() {
        return employees.size();
    }

    public String getStatus() {
        return hoursSpent + "/" + budgetHours;
    }
    public boolean getCompletedStatus() {
        return completed;
    }

    public String toString() {
        return "Activity: " + name + " budgetHours: " + budgetHours + " startWeek: " + startWeek + " endWeek: " + endWeek + "startYear: " + startYear + "endYear: " + endYear + "hoursSpent: " + hoursSpent + "completed: " + completed + "employees: " + employees;
    }

}