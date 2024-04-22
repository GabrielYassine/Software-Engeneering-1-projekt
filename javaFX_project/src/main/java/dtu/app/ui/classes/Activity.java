package dtu.app.ui.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import dtu.app.ui.classes.Project;

public class Activity {
    private List<Employee> employees;
    private final Project project;
    private DateServer dateServer = new DateServer();
    private String name;
    private double budgetHours;
    private int startWeek;
    private int endWeek;
    private double hoursSpent = 0;

    private boolean completed = false;

    public Activity(Project project, String name, String budgetHours, String startWeek, String endWeek, List<Employee> employees) {
        validateProject(project);
        this.project = project;
        validateName(name, project);
        this.name = name;

        this.budgetHours = parseAndValidateHours(budgetHours);
        this.startWeek = parseAndValidateWeek(startWeek, "Start week value error");
        this.endWeek = parseAndValidateWeek(endWeek, "End week value error");
        this.employees = new ArrayList<>(employees);
        this.completed = false;
        addEmployees(employees);
        project.addActivity(this);
    }

    private void validateProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null");
        }
    }

    private void validateName(String name, Project project) {
        List<Activity> activities = project.getActivities();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name missing");
        }
        for (Activity a : activities) {
            if (a.getName().equals(name)) {
                throw new IllegalArgumentException("Activity with this name already exists in the project");
            }
        }
    }

    // Method overload because there are two different cases where the name validation is needed.
    private void validateName(String name, Project project, Activity activity) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name missing");
        }
        for (Activity a : project.getActivities()) {
            if (a.getName().equals(name) && a != activity) {
                throw new IllegalArgumentException("Name already exists");
            }
        }
    }

    private double parseAndValidateHours(String budgetHours) {
        try {
            double hours = Double.parseDouble(budgetHours);
            return hours;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Budget hours missing");
        }
    }

    private int parseAndValidateWeek(String week, String errorMessage) {
        try {
            int weekNumber = Integer.parseInt(week);
            if (weekNumber == 0 || weekNumber > 52) {
                throw new IllegalArgumentException(errorMessage);
            }
            return weekNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void editActivity(String newName, String newBudgetHours, String newStartWeek, String newEndWeek) {
        validateName(newName, project, this);
        this.name = newName;
        this.budgetHours = parseAndValidateHours(newBudgetHours);
        this.startWeek = parseAndValidateWeek(newStartWeek, "Start week value error");
        this.endWeek = parseAndValidateWeek(newEndWeek, "End week value error");
    }

    public void addEmployee(Employee e) {
        int currentWeek = dateServer.getWeek();

        if (employees == null) {
            employees = new ArrayList<>();
        }

        if (e.getActiveActivityCount(currentWeek) <= 20) {
            employees.add(e);
            e.addActivity(this);
        }
    }

    public void addEmployees(List<Employee> PotentialEmployees) {
        int currentWeek = dateServer.getWeek();

        if (employees == null) {
            employees = new ArrayList<>();
        }

        for (Employee e : PotentialEmployees) {
            if (e.getActiveActivityCount(currentWeek) <= 20) {
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

    public void registerHours(double hours) {
        hoursSpent += hours;
    }

    public void completeActivity() {
        completed = !completed;
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
        double halfBudgetHours = budgetHours / 2.0;
        if (hoursSpent < halfBudgetHours) {
            return "Good";
        } else if (hoursSpent <= budgetHours) {
            return "Nearing budget";
        } else {
            return "Over budget";
        }
    }
    public boolean getCompletedStatus() {
        return completed;
    }

    public String toString() {
        return "Activity: " + name;
    }

}