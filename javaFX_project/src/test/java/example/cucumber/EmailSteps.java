package example.cucumber;

import dtu.app.ui.classes.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailSteps {
    private Database database;
    private ErrorMessageHolder errorMessageHolder;
    private DateServer dateServer;
    private Employee employee;
    private Email email;

    public EmailSteps(Database database, ErrorMessageHolder errorMessageHolder, DateServer dateServer) {
        this.database = database;
        this.errorMessageHolder = errorMessageHolder;
        this.dateServer = dateServer;
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

    @Given("the employee has registered his daily work for the current day")
    public void theEmployeeHasRegisteredHisDailyWorkForTheCurrentDay() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Given("the employee is working on {int} activities in a week")
    public void theEmployeeIsWorkingOnActivitiesInAWeek(Integer activityCount) {
        exampleActivities(activityCount);
        assertThat(employee.getActiveActivityCount(dateServer.getWeek()), is(equalTo(activityCount)));
    }

    public Project exampleProject() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        return new Project(database, "ExampleProject", employees, employee);
    }

    public void exampleActivities(int n) {
        Project exampleProject = exampleProject();
        int startWeek = dateServer.getWeek();
        int endWeek = startWeek + 4;

        for (int i = 0; i < n; i++) {
            new Activity(exampleProject, "Activity" + i, "5", startWeek + "", endWeek + "", List.of(employee));
        }
    }


    @Then("the email with subject {string} and text {string} is in the employees inbox")
    public void theEmailWithSubjectAndTextIsInTheEmployeesInbox(String subject, String text) {
        assertThat(email.getSubject(),is(equalTo(subject)));
        assertThat(email.getText(),is(equalTo(text)));
        assertTrue(employee.getInboxStream().anyMatch(e -> e.getText().equals(email.getText()) && e.getSubject().equals(email.getSubject())));
    }

    @When("the system sends a reminder email with subject {string} and text {string}")
    public void theSystemSendsAReminderEmailWithSubjectAndText(String subject, String text) {
        email = new Email(subject,text);

        try {
            database.sendEmail(subject,text);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
