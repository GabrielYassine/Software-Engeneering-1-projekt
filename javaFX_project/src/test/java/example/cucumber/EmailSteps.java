package example.cucumber;

import dtu.app.ui.classes.*;
import dtu.app.ui.pages.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

public class EmailSteps {
    private Database database;
    private App app;
    private ErrorMessageHolder errorMessageHolder;
    private MockEmailServer emailServer;
    private Employee employee;

    public EmailSteps(App app, Database database, ErrorMessageHolder errorMessageHolder, MockEmailServer emailServer) {
        this.app = app;
        this.database = database;
        this.errorMessageHolder = errorMessageHolder;
        this.emailServer = emailServer;
    }

    @Given("that there is an employee {string}")
    public void thatThereIsAnEmployee(String initials) {
        employee = new Employee(database, initials);
        assertThat(employee.getInitials(), is(equalTo(initials)));
    }

    @When("the employee has not registered his daily work for the current day")
    public void theEmployeeHasNotRegisteredHisDailyWorkForTheCurrentDay() throws Exception {
        assertFalse(database.hasEmployeeRegistered(employee));
    }

    @Then("the employee should receive the notification {string}")
    public void theEmployeeShouldReceiveTheNotification(String notification) {
        try {
            database.sendNotification();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
        verify(emailServer.getMockEmailServer()).sendEmail(employee.getInitials(), "Work", notification);
    }

    @Given("the employee is working on {int} activities in a week")
    public void theEmployeeIsWorkingOnActivitiesInAWeek(Integer activityCount) {
        exampleActivities(activityCount);
        assertThat(employee.getActivityCount(), is(equalTo(activityCount)));
    }

//    @When("the employee tries to start a new activity")
//    public void theEmployeeTriesToStartANewActivity() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

//    @Then("the employee will get rejected")
//    public void theEmployeeWillGetRejected() {
//       // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

    @Then("the employee should receive a notification {string}")
    public void theEmployeeShouldReceiveANotification(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    public Project exampleProject() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        return new Project(database, "ExampleProject", employees, employee);
    }

    public void exampleActivities(int n) {
        Project exampleProject = exampleProject();

        for (int i = 0; i < n; i++) {
            new Activity(exampleProject, "Activity" + i, "5", "1", "10", List.of(employee));
        }
    }
}
