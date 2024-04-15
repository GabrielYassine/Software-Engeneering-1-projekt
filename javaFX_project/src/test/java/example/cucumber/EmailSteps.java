package example.cucumber;

import dtu.app.ui.classes.Database;
import dtu.app.ui.classes.Employee;
import dtu.app.ui.classes.ErrorMessageHolder;
import dtu.app.ui.pages.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
}
