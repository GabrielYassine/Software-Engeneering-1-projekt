package example.cucumber;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.errorMessageHolders.ErrorMessageHolder;
import dtu.app.ui.info.EmployeeInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailSteps {
    private final ProjectApp projectApp;
    private final ErrorMessageHolder errorMessage;

    public EmailSteps(ProjectApp projectApp, ErrorMessageHolder errorMessage) {
        this.projectApp = projectApp;
        this.errorMessage = errorMessage;
    }

    @Given("the employee {string} has not registered his daily work for the current day")
    public void theEmployeeHasNotRegisteredHisDailyWorkForTheCurrentDay(String initials) {
        try {
            EmployeeInfo employee = projectApp.getEmployee(initials);
            assertFalse(projectApp.hasEmployeeRegisteredDailyWork(employee));
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee {string} is not doing a fixed activity")
    public void theEmployeeIsNotDoingAFixedActivity(String initials) {
        try {
            EmployeeInfo employee = projectApp.getEmployee(initials);
            assertFalse(projectApp.isEmployeeDoingFixedActivity(employee));
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system sends a reminder email to employee {string}")
    public void theSystemSendsAReminderEmailToEmployee(String initials) {
        try {
            EmployeeInfo employee = projectApp.getEmployee(initials);
            projectApp.sendEmailToEmployee(employee);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }


    @And("the email with subject {string} and text {string} is in the employees {string} inbox")
    public void theEmailWithSubjectAndTextIsInTheEmployeesInbox(String subject, String text, String initials) {
        try {
            EmployeeInfo employee = projectApp.getEmployee(initials);
            LocalDate date = LocalDate.now().plusDays(1);
            assertTrue(projectApp.doesEmailExist(employee, subject, text, date));
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }


    @Then("the system does not send a reminder email to {string}")
    public void theSystemDoesNotSendAReminderEmailTo(String initials) {
        theSystemSendsAReminderEmailToEmployee(initials);
    }

    @Then("the email with subject {string} and text {string} is not in the employees {string} inbox")
    public void theEmailWithSubjectAndTextIsNotInTheEmployeesInbox(String subject, String text, String initials) {
        try {
            EmployeeInfo employee = projectApp.getEmployee(initials);
            LocalDate date = LocalDate.now().plusDays(1);
            assertFalse(projectApp.doesEmailExist(employee, subject, text, date));
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
}
