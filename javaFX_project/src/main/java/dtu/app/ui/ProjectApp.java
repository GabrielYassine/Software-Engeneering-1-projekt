package dtu.app.ui;

import dtu.app.ui.database.Database;
import dtu.app.ui.domain.*;
import dtu.app.ui.info.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProjectApp {

    private final Database database;
    private DateServer dateServer = new DateServer();

    public ProjectApp() {
        this.database = new Database(this);
    }

    /**
     * This method initializes the test data
     * @Author: Gabriel Ali Yassine
     */

    public void initializeTestData() throws Exception {
        database.initializeTestData();
        sendEmailToEmployee(getEmployee("Huba"));
    }

    //////////////////////////// CREATE METHODS ////////////////////////////

    /**
     * This method creates an Employee object
     * @Author: Taemur Baig
     */

    public Employee createEmployee(String initials) {
        return new Employee(database, initials);
    }


    /**
     * This method creates a Project object
     * @Author: Mathias Bødkerholm Dalsgaard
     */

    public Project createProject(String projectName, List<EmployeeInfo> employeeInfoList, EmployeeInfo projectLeaderInfo) throws Exception {
        // preconditions
        assert employeeInfoList != null;

        if (projectName == null || projectName.isEmpty()) {
            throw new IllegalArgumentException("Name missing");
        }
        List<Employee> employees = new ArrayList<>();
        for (EmployeeInfo e : employeeInfoList) {
            Employee employee = findEmployee(e);
            employees.add(employee);
        }
        Employee projectLeader = null;
        if (projectLeaderInfo != null) {
            projectLeader = findEmployee(projectLeaderInfo);
        }

        Project result = new Project(database, projectName, employees, projectLeader);

        // postconditions
        assert result != null;
        return result;
    }

    /**
     * This method creates an Activity object
     * @Author: Ilias Chaykh
     */

    public Activity createActivity(ProjectInfo projectInfo, String activityName, String budgetHours, String startWeek, String endWeek, List<EmployeeInfo> employeeInfoList, String startYear, String endYear) throws Exception {
        Project project = findProject(projectInfo);
        validateName(activityName, project);

        double budgetHoursDouble = parseAndValidateHours(budgetHours);

        int startWeekInt = parseAndValidateWeek(startWeek);
        int endWeekInt = parseAndValidateWeek(endWeek);
        int startYearInt = parseAndValidateYear(startYear);
        int endYearInt = parseAndValidateYear(endYear);

        validateInterval(startWeekInt, endWeekInt, startYearInt, endYearInt);
        Activity a = new Activity(project, activityName, budgetHoursDouble, startWeekInt, endWeekInt, startYearInt, endYearInt);
        addEmployeesToActivity(a, employeeInfoList);
        return a;
    }

    /**
     * This method creates a FixedActivity object
     * @Author: Aland Ali Ahmad
     */

    public void createFixedActivity(EmployeeInfo employeeInfo, String activityName, String startWeek, String endWeek, String startYear, String endYear) throws Exception {
        if (employeeInfo == null) {
            throw new Exception("Employee not selected");
        }
        Employee employee = findEmployee(employeeInfo);
        validateName(activityName, null);
        int startWeekInt = parseAndValidateWeek(startWeek);
        int endWeekInt = parseAndValidateWeek(endWeek);
        int startYearInt = parseAndValidateYear(startYear);
        int endYearInt = parseAndValidateYear(endYear);

        validateInterval(startWeekInt, endWeekInt, startYearInt, endYearInt);
        new FixedActivity(employee, activityName, startWeekInt, endWeekInt, startYearInt, endYearInt);
    }

    //////////////////////////// EDIT METHODS ////////////////////////////

    /**
     * This method edits a projects details
     * @Author: Mathias Bødkerholm Dalsgaard
     */

    public void editProject(ProjectInfo projectInfo, String projectName, String projectLeaderInitials, List<EmployeeInfo> employeeInfoList) throws Exception {
        if (projectName == null || projectName.isEmpty()) {
            throw new IllegalArgumentException("Name missing");
        }
        List<Employee> employees = new ArrayList<>();
        for (EmployeeInfo e : employeeInfoList) {
            Employee employee = findEmployee(e);
            employees.add(employee);
        }
        Project project = findProject(projectInfo);
        project.editProject(projectName, projectLeaderInitials, employees);
        setProject(new ProjectInfo(project));
    }

    /**
     * This method edits an Activity object
     * @Author: Ilias Chaykh
     */

    public void editActivity(ActivityInfo activityInfo, String activityName, String budgetHours, String startWeek, String endWeek, List<EmployeeInfo> employeeInfoList, String startYear, String endYear) throws Exception {
        Activity activity = findActivity(getSelectedProject(), activityInfo);
        validateName(activityName, activity.getProject());

        double budgetHoursDouble = parseAndValidateHours(budgetHours);
        int startWeekInt = parseAndValidateWeek(startWeek);
        int endWeekInt = parseAndValidateWeek(endWeek);
        int startYearInt = parseAndValidateYear(startYear);
        int endYearInt = parseAndValidateYear(endYear);

        List<Employee> employees = new ArrayList<>();
        for (EmployeeInfo e : employeeInfoList) {
            Employee employee = findEmployee(e);
            employees.add(employee);
        }

        validateInterval(startWeekInt, endWeekInt, startYearInt, endYearInt);
        activity.editActivity(activityName, budgetHoursDouble, startWeekInt, endWeekInt, startYearInt, endYearInt);
        addEmployeesToActivity(activity, employeeInfoList);
        setActivity(new ActivityInfo(activity));
    }

    /**
     * This method edits the hours spent on a specific activity on a specific date
     * @Author: Gabriel Ali Yassine
     */

    public void editEmployeeLogHours(String projectID, String activityName, String date, String employeeInitials, String oldHours, String newHours) throws Exception {
        ProjectInfo projectInfo = getProject(projectID);
        ActivityInfo activityInfo = getActivity(projectInfo, activityName);
        EmployeeInfo employeeInfo = getEmployee(employeeInitials);

        double oldHoursDouble = parseAndValidateHours(oldHours);
        double newHoursDouble = parseAndValidateHours(newHours);
        String hoursDifference = String.valueOf(newHoursDouble - oldHoursDouble);
        registerHours(employeeInfo, date, activityInfo, hoursDifference, projectInfo);
    }

    /**
     * This method completes / uncompletes an activity
     * @Author: Illias Chaykh
     */

    public void switchActivityCompletion(ProjectInfo projectInfo, ActivityInfo activityInfo) throws Exception {
        Project project = findProject(projectInfo);
        project.switchActivityCompletionStatus(activityInfo.getName());
        Activity activity = findActivity(projectInfo, activityInfo);
        setActivity(new ActivityInfo(activity));
    }

    //////////////////////////// ADDER METHODS /////////////////////////////

    /**
     * This method adds an Employee object to a Project object
     * @Author: Mathias Bødkerholm Dalsgaard
     */

    public void addEmployeeToProject(ProjectInfo projectInfo, Employee employee) throws Exception {
        Project project = findProject(projectInfo);
        project.addEmployee(employee);
    }

    /**
     * This method adds Employee objects to an Activity object
     * @Author: Aland Ali Ahmad
     */

    private void addEmployeesToActivity(Activity activity, List<EmployeeInfo> employeeInfoList) throws Exception {
        Map<Integer, List<Integer>> weeks = activity.getWeeksInInterval();

        for (EmployeeInfo e : employeeInfoList) {
            Employee employee = findEmployee(e);
            for (Map.Entry<Integer, List<Integer>> entry : weeks.entrySet()) {
                int year = entry.getKey();
                for (int week : entry.getValue()) {
                    int month = LocalDate.ofYearDay(year, week * 7).getMonthValue();
                    int weekOfMonth = (LocalDate.ofYearDay(year, week * 7).getDayOfMonth() + 6) / 7;
                    if (employee.getActiveActivityCount(year, month, weekOfMonth) >= 20) {
                        throw new Exception("Employee is already working on 20 activities this week");
                    }
                }
            }
            activity.addEmployee(employee);
            employee.addActivity(activity);
        }
    }

    /**
     * This method registers hours on an Activity object
     * @Author: Gabriel Ali Yassine
     */

    public void registerHours(EmployeeInfo employeeInfo, String date, ActivityInfo activityInfo, String hours, ProjectInfo projectInfo) throws Exception {
        if (employeeInfo == null) {
            throw new Exception("Employee missing");
        }
        double hoursDouble = parseAndValidateHours(hours);
        if (date == null || date.isEmpty()) {
            throw new Exception("Date missing");
        }
        LocalDate localDate = dateServer.parseDate(date);
        Employee employee = findEmployee(employeeInfo);
        ProjectInfo projectToUse = projectInfo != null ? projectInfo : getSelectedProject();
        Activity activity = findActivity(projectToUse, activityInfo);
        employee.getActivityLog().registerHours(localDate, activity, hoursDouble);
        activity.registerHours(hoursDouble);
        setActivity(new ActivityInfo(activity));
    }


    /**
     * This method sends an email to an employee
     * @Author: Taemur Baig
     */
    public void sendEmailToEmployee(EmployeeInfo employeeInfo) throws Exception {
        DateServer dateServer1 = new DateServer();
        String currentDate = dateServer1.dateToString(dateServer.getDate());
        LocalDate beforeDate = dateServer1.parseDate(currentDate).minusDays(1);

        Employee e = findEmployee(employeeInfo);
        if (!isEmployeeDoingFixedActivity(employeeInfo) && !e.hasRegisteredDailyWork(beforeDate)) {
            e.addEmail(new Email("Work", "Register your daily work", currentDate));
        }
    }

    //////////////////////////// CHECKER METHODS ////////////////////////////

    /**
     * This method checks if an employee is doing a fixed activity
     * @Author: Taemur Baig
     */

    public boolean isEmployeeDoingFixedActivity(EmployeeInfo employeeInfo) throws Exception {
        Employee e = findEmployee(employeeInfo);
        DateServer dateServer1 = new DateServer();
        int currentWeek = dateServer1.getWeek();
        int currentYear = dateServer1.getYear();

        return e.isDoingFixedActivity(currentWeek, currentYear);
    }

    /**
     * This method checks if an employee has registered daily work
     * @Author: Taemur Baig
     */

    public boolean hasEmployeeRegisteredDailyWork(EmployeeInfo employeeInfo) throws Exception {
        LocalDate currentDate = dateServer.getDate();
        return findEmployee(employeeInfo).hasRegisteredDailyWork(currentDate);
    }

    /**
     * This method checks if an email with specific details exists
     * @Author: Taemur Baig
     */
    public boolean doesEmailExist(EmployeeInfo employeeInfo, String subject, String text, LocalDate date) throws Exception {
        DateServer dateServer1 = new DateServer();
        String dateStr = dateServer1.dateToString(date);

        return (getEmployeeInbox(employeeInfo).stream()
                .anyMatch(email -> email.getSubject().equals(subject) && email.getText().equals(text) && email.getDate().equals(dateStr)));
    }

    //////////////////////////// SETTER METHODS ////////////////////////////

    /**
     * This method sets the selected project
     */

    public void setProject(ProjectInfo project) {
        database.setSelectedProject(project);
    }

    /**
     * This method returns the selected activity
     */

    public ActivityInfo getSelectedActivity() {
        return database.getSelectedActivity();
    }

    /**
     * This method sets the selected activity
     */

    public void setActivity(ActivityInfo activity) {
        database.setSelectedActivity(activity);
    }

    /**
     * This method sets the selected employee
     */

    public void setEmployee(EmployeeInfo employee) throws Exception {
        if (employee == null) {
            throw new Exception("Employee missing");
        }
        database.setSelectedEmployee(employee);
    }

    /**
     * this method sets a specific log in the employees schedule as the selected log
     */

    public void setSelectedEmployeeLog(List<String> logDetails) {
        database.setSelectedEmployeeLog(logDetails);
    }

    public void setDateServer(DateServer dateServer) {
        this.dateServer = dateServer;
    }

    //////////////////////////// GETTER METHODS ////////////////////////////

    /**
     * This method returns an EmployeeInfo object
     */

    public EmployeeInfo getEmployee(String initials) throws Exception {
        if (initials == null || initials.isEmpty()) {
            return null;
        }
        return new EmployeeInfo(database.getEmployee(initials));
    }

    /**
     * This method returns a ProjectInfo object
     */

    public ProjectInfo getProject(String ID) {
        return new ProjectInfo(database.getProject(Integer.parseInt(ID)));
    }

    /**
     * This method returns the selected project
     */

    public ProjectInfo getSelectedProject() throws Exception {
        return database.getSelectedProject();
    }

    /**
     * This method returns the selected project leader
     */

    public ActivityInfo getActivity(ProjectInfo projectInfo, String activityName) throws Exception {
        Project project = findProject(projectInfo);
        Activity activity = project.getActivity(activityName);
        return new ActivityInfo(activity);
    }

    /**
     * This method returns the selected employee
     */

    public EmployeeInfo getSelectedEmployee() {
        return database.getSelectedEmployee();
    }

    public List<Email> getEmployeeInbox(EmployeeInfo employee) {
        return employee.getInbox();
    }

    /**
     * This method returns the registered hours for an employee on a specific day
     */

    public Map<Activity, Double> getEmployeesRegisteredHoursForADay(EmployeeInfo employeeInfo, String date) throws Exception {

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Employee employee = findEmployee(employeeInfo);
        ActivityLogInfo activityLogInfo = new ActivityLogInfo(employee.getActivityLog());
        return activityLogInfo.getDateActivities(localDate);
    }

    /**
     * This method returns the registered hours for an employee on a specific activity on a specific day
     */

    public double getEmployeeRegisteredHoursOnActivityForDay(Map<Activity, Double> hoursLog, ActivityInfo activityInfo) throws Exception {
        Activity activity = findActivity(getSelectedProject(), activityInfo);
        return hoursLog.get(activity);
    }

    /**
     * This method returns the total hours spent on an activity
     */

    public double getActivityTotalHours(ActivityInfo activityInfo) {
        return activityInfo.getHoursSpent();
    }

    /**
     * This method returns the completion status of a project
     */

    public String getProjectCompletionStatus(ProjectInfo projectInfo) throws Exception {
        Project project = findProject(projectInfo);
        return project.getCompletionStatus();
    }

    /**
     * This method gets the registered hours for an employee for a specific week
     */

    public ActivityLogInfo getEmployeeWeekLog(EmployeeInfo employeeInfo, String year, String week) throws Exception {
        if (employeeInfo == null) {
            throw new Exception("Employee missing");
        }
        int yearInt = parseAndValidateYear(year);
        int weekInt = parseAndValidateWeek(week);
        Employee employee = findEmployee(employeeInfo);
        ActivityLogInfo activityLogInfo = new ActivityLogInfo(employee.getActivityLog());
        ActivityLog weekLog = activityLogInfo.getWeekActivities(yearInt, weekInt);
        return new ActivityLogInfo(weekLog);
    }


    /**
     * This method returns the selected log in the employees schedule
     */

    public List<String> getSelectedEmployeeLog() {
        return database.getSelectedEmployeeLog();
    }

    /**
     * This method returns the hours spent on a specific activity on a specific date
     */

    public String getSelectedEmployeeLogHours(String projectID, String activityName, String date, String employeeInitials) throws Exception {
        double hours = 0;
        ProjectInfo projectInfo = getProject(projectID);
        ActivityInfo activityInfo = getActivity(projectInfo, activityName);
        Activity activity = findActivity(projectInfo, activityInfo);
        EmployeeInfo employeeInfo = getEmployee(employeeInitials);
        Map<Activity, Double> hoursLog = getEmployeesRegisteredHoursForADay(employeeInfo, date);
        if (hoursLog.containsKey(activity)) {
            hours = hoursLog.get(activity);
        }
        return String.valueOf(hours);
    }

    /**
     * This method returns a list of the active activities for an employee in a specific year and month
     */

    public Map<EmployeeInfo, List<Integer>> getAvailability(String year, String month) throws Exception {
        int yearInt = parseAndValidateYear(year);
        int monthInt = parseAndValidateMonth(month);

        Map<EmployeeInfo, List<Integer>> availability = new HashMap<>();
        List<EmployeeInfo> employees = getEmployeesInApp();

        for (EmployeeInfo employee : employees) {
            Employee realEmployee = findEmployee(employee);
            List<Integer> monthHours = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                monthHours.add(realEmployee.getActiveActivityCount(yearInt, monthInt, i));
            }
            availability.put(employee, monthHours);
        }
        return availability;
    }

    /**
     * This method returns a list of all fixed activities for an employee
     */

    public List<FixedActivityInfo> getFixedActivitiesForEmployee(EmployeeInfo employee) throws Exception {
        Employee e = findEmployee(employee);
        List<FixedActivityInfo> fixedActivityInfoList = new ArrayList<>();
        for (FixedActivity fa : e.getFixedActivities()) {
            fixedActivityInfoList.add(new FixedActivityInfo(fa));
        }
        return fixedActivityInfoList;
    }

    //////////////////////////// VALIDATION METHODS ////////////////////////////


    /**
     * This method parses and validates the hours worked or budget hours
     * @Author Aland Ali Ahmad
     */

    private double parseAndValidateHours(String registeredHours) throws Exception {
        try {
            double hours = Double.parseDouble(registeredHours);
            hours = Math.round(hours * 2) / 2.0;
            return hours;
        } catch (Exception e) {
            throw new Exception("Hours missing");
        }
    }

    /**
     * This method parses and validates the year
     * @Author: Aland Ali Ahmad
     */

    private int parseAndValidateYear(String year) throws Exception {
        int yearInt;
        try {
            yearInt = Integer.parseInt(year);
        } catch (Exception e) {
            throw new Exception("No year given");
        }
        if (yearInt < 1) {
            throw new Exception("Year value out of bounds");
        }
        return yearInt;
    }

    /**
     * This method parses and validates the week
     * @Author: Aland Ali Ahmad
     */

    public int parseAndValidateWeek(String week) throws Exception {
        assert true;

        int weekInt;
        try {
            weekInt = Integer.parseInt(week);
        } catch (Exception e) {
            throw new Exception("No week given");
        }

        if (weekInt == 0 || weekInt > 52) {
            throw new Exception("Week value out of bounds");
        }

        // Postconditions
        assert weekInt > 0 && weekInt <= 52;

        return weekInt;
    }

    /**
     * This method parses and validates the month
     * @Author: Aland Ali Ahmad
     */

    private int parseAndValidateMonth(String month) {
        try {
            int monthInt = Integer.parseInt(month);
            if (monthInt == 0 || monthInt > 12) {
                throw new IllegalArgumentException("Month value out of bounds");
            }
            return monthInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("No month given");
        }
    }

    /**
     * This method validates the name of an activity or employee or project
     * @Author: Mathias Bødkerholm Dalsgaard
     */

    public void validateName(String name, Project project) {
        //preconditions
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name missing");
        }
        if (project == null) {
            return;
        }

        ActivityInfo oldActivity = database.getSelectedActivity();
        if (oldActivity != null && oldActivity.getName().equals(name)) {
            return;
        }
        for (Activity a : project.getActivities()) {
            if (a.getName().equals(name)) {
                throw new IllegalArgumentException("Activity with this name already exists in the project");
            }
        }
        // postConditions
        assert name != null;
        assert name.isEmpty() == false;
        assert project != null;
        assert oldActivity == null || !oldActivity.getName().equals(name);
    }

    /**
     * This method validates the interval of the start and end week
     * @Author: Gabriel Ali Yassine
     */

    private void validateInterval(int startWeekInt, int endWeekInt, int startYearInt, int endYearInt) {
        if (startYearInt > endYearInt) {
            throw new IllegalArgumentException("Start year is after end year");
        }
        if (startYearInt == endYearInt && startWeekInt > endWeekInt) {
            throw new IllegalArgumentException("Start week is after end week");
        }
    }

    //////////////////////////// FIND METHODS ////////////////////////////


    /**
     * This method return the Employee object from an EmployeeInfo object
     * @Author: Gabriel Ali Yassine
     */

    private Employee findEmployee(EmployeeInfo employee) throws Exception {
        String initials = employee.getInitials();
        return database.getEmployee(initials);
    }

    /**
     * This method returns the Project object from a ProjectInfo object
     * @Author: Mathias Bødkerholm Dalsgaard
     */

    public Project findProject(ProjectInfo project) {
        int id = project.getID();
        return database.getProject(id);
    }

    /**
     * This method returns the Activity object from a ProjectInfo object and an ActivityInfo object
     * @Author: Ilias Chaykh
     */

    private Activity findActivity(ProjectInfo projectInfo, ActivityInfo activity) {
        Project project = findProject(projectInfo);
        String name = activity.getName();
        return project.getActivity(name);
    }

    //////////////////////////// GETTERS FOR GUI ////////////////////////////

    /**
     * This method returns a list of all employees in the application
     */


    public List<EmployeeInfo> getEmployeesInApp() {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        for (Employee employee : database.getEmployees()) {
            employeeInfoList.add(new EmployeeInfo(employee));
        }
        return employeeInfoList;
    }

    /**
     * This method returns a list of all projects in the application
     */

    public List<ProjectInfo> getProjectsInApp() {
        List<ProjectInfo> projectInfoList = new ArrayList<>();
        for (Project project : database.getProjects()) {
            projectInfoList.add(new ProjectInfo(project));
        }
        return projectInfoList;
    }

    /**
     * This method returns a list of all activities in a project
     */

    public List<ActivityInfo> getActivitiesInProject(ProjectInfo project) {
        Project p = findProject(project);
        List<ActivityInfo> activityInfoList = new ArrayList<>();
        for (Activity a : p.getActivities()) {
            activityInfoList.add(new ActivityInfo(a));
        }
        return activityInfoList;
    }

    /**
     * This method returns a list of all employees in a project
     */

    public List<EmployeeInfo> getEmployeesInProject(ProjectInfo selectedProject) {
        Project p = findProject(selectedProject);
        List<Employee> employees = p.getEmployees();
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        for (Employee e : employees) {
            employeeInfoList.add(new EmployeeInfo(e));
        }
        return employeeInfoList;
    }

    /**
     * This method returns a list of all employees in an activity
     */

    public List<EmployeeInfo> getEmployeesInActivity(ProjectInfo project, ActivityInfo activity) {
        Activity a = findActivity(project, activity);
        List<Employee> employees = a.getEmployees();
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        for (Employee e : employees) {
            employeeInfoList.add(new EmployeeInfo(e));
        }
        return employeeInfoList;
    }

    /**
     * This method returns a list of dates for a specific year and week
     */

    public List<String> getWeekDates(String year, String week) throws Exception {
        int weekInt = parseAndValidateWeek(week);
        int yearInt = parseAndValidateYear(year);
        List<String> weekDates = new ArrayList<>();
        LocalDate date = LocalDate.ofYearDay(yearInt, (weekInt * 7) - 6);
        for (int i = 0; i < 7; i++) {
            weekDates.add(date.toString());
            date = date.plusDays(1);
        }
        return weekDates;
    }

    /**
     * This method returns a Map of all activities and their hours of an ActivityLog for a specific day
     */

    public Map<Activity, Double> getEmployeeDayLog(ActivityLogInfo a, String day) {
        Map<Activity, Double> dayLog = new HashMap<>();
        DayOfWeek specifiedDay = DayOfWeek.valueOf(day.toUpperCase());

        for (Map.Entry<LocalDate, Map<Activity, Double>> entry : a.getDateLog().entrySet()) {
            if (entry.getKey().getDayOfWeek() == specifiedDay) {
                dayLog.putAll(entry.getValue());
            }
        }
        return dayLog;
    }

    /**
     * This method returns the completion status of an activity
     */

    public String getActivityCompletionStatus(ActivityInfo activityInfo) {
        return activityInfo.getCompletionStatus();
    }

}