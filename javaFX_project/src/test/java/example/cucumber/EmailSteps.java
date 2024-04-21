package example.cucumber;

import dtu.app.ui.classes.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailSteps {
    private final Database database;
    private final ErrorMessageHolder errorMessageHolder;
    private final DateServer dateServer;
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
    public void theEmployeeHasRegisteredHisDailyWorkForTheCurrentDay() throws Exception {
        Project exampleProject = exampleProject();

        employee.getActivityLog().registerHours(dateServer.getDate(), new Activity(exampleProject, "Activity", "10", "1", "2", List.of(employee)), "15");
        assertTrue(database.hasEmployeeRegistered(employee));
    }


    @Given("the employee is working on {int} activities in a week")
    public void theEmployeeIsWorkingOnActivitiesInAWeek(Integer int1) {
        exampleActivities(int1);
        assertThat(employee.getActiveActivityCount(dateServer.getWeek()), is(equalTo(int1)));
    }

    public Project exampleProject() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        return new Project(database, "ExampleProject", employees, employee);
    }

    public void exampleActivities(int n) {
        Project exampleProject = exampleProject();
        String startWeek = String.valueOf(dateServer.getWeek());
        String endWeek = String.valueOf(dateServer.getWeek() + 4);

        for (int i = 0; i < n; i++) {
            new Activity(exampleProject, "Activity" + i, "5", startWeek, endWeek, List.of(employee));
        }
    }


    @Then("the email with subject {string} and text {string} is in the employees inbox")
    public void theEmailWithSubjectAndTextIsInTheEmployeesInbox(String subject, String text) {
        email = new Email(subject,text);
        assertThat(email.getSubject(),is(equalTo(subject)));
        assertThat(email.getText(),is(equalTo(text)));
        assertTrue(employee.getInboxStream().anyMatch(e -> e.getText().equals(email.getText()) && e.getSubject().equals(email.getSubject())));
    }

    @When("the system sends a reminder email")
    public void theSystemSendsAReminderEmail() {
        try {
            database.sendEmail();
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }
}
