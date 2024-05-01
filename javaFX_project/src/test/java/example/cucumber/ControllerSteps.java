package example.cucumber;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.errorMessageHolders.ErrorMessageHolder;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.ActivityLogInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControllerSteps {

    private final ProjectApp application;
    private final ErrorMessageHolder errorMessage;

    public ControllerSteps(ProjectApp application) {
        this.application = application;
        this.errorMessage = application.getErrorMessage();
    }


    @Then("the employee should see the following employees in the app")
    public void theEmployeeShouldSeeTheFollowingEmployees(List<String> initials) throws Exception {
        List<EmployeeInfo> e1 = new ArrayList<>();
        for (String initial : initials) {
            EmployeeInfo e = application.getEmployee(initial);
            e1.add(e);
        }
        List<EmployeeInfo> e2 = application.getEmployeesInApp();
        assertEquals(e1, e2);
    }

    @Then("the size of the projects should be {int}")
    public void theSizeOfTheProjectsShouldBe(int projectsSize) {
        List<ProjectInfo> projects = application.getProjectsInApp();
        assertEquals(projectsSize, projects.size());
    }

    @Then("the employee should see the following activities in the selected project")
    public void theEmployeeShouldSeeTheFollowingActivitiesInTheSelectedProject(List<String> activityNames) throws Exception {
        ProjectInfo p1 = application.getSelectedProject();
        List<ActivityInfo> a1 = application.getActivitiesInProject(p1);

        List<String> actualActivityNames = new ArrayList<>();
        for (ActivityInfo activity : a1) {
            actualActivityNames.add(activity.getName());
        }

        assertEquals(activityNames, actualActivityNames);
    }

    @Then("the employee should see the following employees in the project")
    public void theEmployeeShouldSeeTheFollowingEmployeesInTheProject(List<String> initials) throws Exception {
        List<EmployeeInfo> e1 = new ArrayList<>();

        ProjectInfo p1 = application.getSelectedProject();
        List<EmployeeInfo> e2 = application.getEmployeesInProject(p1);
        for (String initial : initials) {
            EmployeeInfo e = application.getEmployee(initial);
            e1.add(e);
        }
        assertEquals(e1, e2);
    }

    @Then("the employee should see the following employees in the activity")
    public void theEmployeeShouldSeeTheFollowingEmployeesInTheActivity(List<String> initials) throws Exception {
        List<EmployeeInfo> e1 = new ArrayList<>();

        ActivityInfo a1 = application.getSelectedActivity();
        ProjectInfo p1 = application.getSelectedProject();
        List<EmployeeInfo> e2 = application.getEmployeesInActivity(p1, a1);
        for (String initial : initials) {
            EmployeeInfo e = application.getEmployee(initial);
            e1.add(e);
        }
        assertEquals(e1, e2);
    }


    @When("the user searches for the year {string} and week {string} they should see the following dates")
    public void theUserSearchesForTheYearAndWeekTheyShouldSeeTheFollowingDates(String year, String week, List<String> dates1) {
        List<String> dates2 = application.getWeekDates(year, week);
        assertEquals(dates1, dates2);
    }


    @Then("the employee should see the details for the weekday {string} in week {string} of year {string} for the employee {string}")
    public void theEmployeeShouldSeeTheDetailsForTheWeekdayInWeekOfYearForTheEmployee(String weekDay, String week, String year, String initials, DataTable table) throws Exception {
        EmployeeInfo e = application.getEmployee(initials);
        ActivityLogInfo a = application.getEmployeeWeekLog(e, year, week);
        Map<Activity, Double> actualDayLog = application.getEmployeeDayLog(e, a, weekDay);

        // Convert DataTable to Map
        Map<String, Double> dataTableMap = table.asMaps(String.class, String.class).stream()
                .collect(Collectors.toMap(row -> row.get("ActivityName"), row -> Double.parseDouble(row.get("Hours"))));

        // Compare actualDayLog with dataTableMap
        boolean mapsAreEqual = actualDayLog.entrySet().stream()
                .allMatch(entry -> dataTableMap.containsKey(entry.getKey().getName()) && dataTableMap.get(entry.getKey().getName()).equals(entry.getValue()));

        assertTrue(mapsAreEqual);
    }

    @Then("the activity completion status should be {string}")
    public void theActivityCompletionStatusShouldBe(String completionStatus) {
        ActivityInfo activity = application.getSelectedActivity();
        String actualCompletionStatus = application.getActivityCompletionStatus(activity);
        assertEquals(completionStatus, actualCompletionStatus);
    }

    @Then("the size of the employees list in the activity should be {int}")
    public void theSizeOfTheEmployeesListInTheActivityShouldBe(int employeesSize) {
        ActivityInfo activity = application.getSelectedActivity();
        int actualEmployeesSize = activity.getEmployeesSize();
        assertEquals(employeesSize, actualEmployeesSize);
    }

    @Then("the status of the activity should be {string}")
    public void theStatusOfTheActivityShouldBe(String status) {
        ActivityInfo activity = application.getSelectedActivity();
        String actualStatus = activity.getStatus();
        assertEquals(status, actualStatus);
    }

    @Then("the projectInfo completion status should be {string}")
    public void theProjectCompletionStatusShouldBe(String projectCompletion) throws Exception {
        ProjectInfo project = application.getSelectedProject();
        Project p = application.findProject(project);
        ProjectInfo project2 = new ProjectInfo(p);
        String actualProjectCompletion = project2.getCompletionStatus();
        assertEquals(projectCompletion, actualProjectCompletion);
    }

    @Then("the size of the activity list in the project should be {int}")
    public void theSizeOfTheActivityListInTheProjectShouldBe(int activitiesSize) throws Exception {
        ProjectInfo project = application.getSelectedProject();
        Project actualProject = application.findProject(project);
        ProjectInfo project2 = new ProjectInfo(actualProject);
        int actualActivitiesSize = project2.getActivitiesSize();
        assertEquals(activitiesSize, actualActivitiesSize);
    }

    @Then("the size of the employees list in the project should be {int}")
    public void theSizeOfTheEmployeesListInTheProjectShouldBe(int employeesSize) throws Exception {
        ProjectInfo project = application.getSelectedProject();
        Project actualProject = application.findProject(project);
        ProjectInfo project2 = new ProjectInfo(actualProject);
        int actualEmployeesSize = project2.getEmployeesSize();
        assertEquals(employeesSize, actualEmployeesSize);
    }

    @Then("the employee should see the following activities in the project")
    public void theEmployeeShouldSeeTheFollowingActivitiesInTheProject(List<String> activityNames) throws Exception {
        ProjectInfo p1 = application.getSelectedProject();
        Project p = application.findProject(p1);
        ProjectInfo p2 = new ProjectInfo(p);

        List<Activity> a1 = p2.getActivities();

        List<String> actualActivityNames = new ArrayList<>();
        for (Activity activity : a1) {
            actualActivityNames.add(activity.getName());
        }

        assertEquals(activityNames, actualActivityNames);
    }

    @Then("the employee should see this employee\\(s) in the project")
    public void theEmployeeShouldSeeThisTheseEmployeesInTheProject(List<String> initials) throws Exception {
        List<EmployeeInfo> e1 = new ArrayList<>();

        ProjectInfo p1 = application.getSelectedProject();
        Project p = application.findProject(p1);
        ProjectInfo p2 = new ProjectInfo(p);

        List<Employee> e2 = p2.getEmployees();
        for (String initial : initials) {
            EmployeeInfo e = application.getEmployee(initial);
            e1.add(e);
        }
        List<EmployeeInfo> e3 = new ArrayList<>();
        for (Employee e : e2) {
            e3.add(new EmployeeInfo(e));
        }
        assertEquals(e1, e3);
    }

    @Then("the employee should see the following initials")
    public void theEmployeeShouldSeeTheFollowingInitials(List<String> initials) throws Exception {
        for (String initial : initials) {
            EmployeeInfo e = application.getEmployee(initial);
            assertEquals(initial, e.toString());
        }
    }
}
