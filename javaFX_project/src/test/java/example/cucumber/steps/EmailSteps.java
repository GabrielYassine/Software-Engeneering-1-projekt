package example.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmailSteps {
    @Given("that there is an employee {string}")
    public void thatThereIsAnEmployee(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the employee has not registered his daily work for the current day")
    public void theEmployeeHasNotRegisteredHisDailyWorkForTheCurrentDay() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the system checks for unregistered daily work")
    public void theSystemChecksForUnregisteredDailyWork() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the employee should receive the notification {string}")
    public void theEmployeeShouldReceiveTheNotification(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
