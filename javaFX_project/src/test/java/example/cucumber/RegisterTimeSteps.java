package example.cucumber;

import dtu.app.ui.ApplicationProjects;
import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.errorMessageHolders.ErrorMessageHolder;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RegisterTimeSteps {

    private final ApplicationProjects application;
    private final ErrorMessageHolder errorMessage;

    public RegisterTimeSteps(ApplicationProjects application) {
        this.application = application;
        this.errorMessage = application.getErrorMessage();
    }

	@When("the employee with initials {string} registers {string} hours on the activity {string} on the date {string}")
	public void theEmployeeWithInitialsRegistersHoursOnTheActivityOnTheDate(String initials, String hours, String activityName, String date) throws Exception {
		try {
			EmployeeInfo employee = application.getEmployee(initials);
			ActivityInfo activity = application.getSelectedActivity();
			application.registerHours(employee, date, activity, hours, null);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity {string} should have {string} hours registered by {string} on the date {string}")
	public void theActivityShouldHaveHoursRegisteredByOnTheDate(String activityName, String expectedHours, String initials, String date) throws Exception {
		try {
			ActivityInfo activityInfo = application.getActivity(application.getSelectedProject(), activityName);
			EmployeeInfo employee = application.getEmployee(initials);
			Map<Activity, Double> hoursLog = application.getEmployeesRegisteredHoursForADay(employee, date);
			double registeredHours = application.getEmployeeRegisteredHoursOnActivityForDay(hoursLog, activityInfo);
			assertEquals(Double.parseDouble(expectedHours), registeredHours, 0);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity {string} should have {string} hours registered in total")
	public void theActivityShouldHaveHoursRegisteredInTotal(String activityName, String totalHours) throws Exception {
		ActivityInfo activity = application.getActivity(application.getSelectedProject(), activityName);
		double registeredHours = application.getActivityTotalHours(activity);
		assertEquals(Double.parseDouble(totalHours), registeredHours, 0);
	}

}
