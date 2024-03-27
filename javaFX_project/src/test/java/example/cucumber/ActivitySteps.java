package example.cucumber;

import dtu.example.ui.Activity;
import dtu.example.ui.App;
import dtu.example.ui.Employee;
import dtu.example.ui.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ActivitySteps {

	private final App app;
	private ErrorMessageHolder errorMessage;
	private Employee employee;
	private Activity activity;

	private Project project;

	public ActivitySteps(App app) {
		this.app = app;
		this.errorMessage = new ErrorMessageHolder();
	}

	@When("the company creates an activity with name {string}, and expected hours {int}, scheduled from week {int} to week {int} with ID {int}")
	public void theCompanyCreatesAnActivityWithNameAndExpectedHoursScheduledFromWeekToWeek(String name, int budgetHours, int weekStart, int weekEnd, int ID) throws Exception {
		project = app.getProjectWithID(ID);
		activity = new Activity(project, name, budgetHours, weekStart, weekEnd);
	}

	@Then("the activity should be created successfully")
	public void theActivityShouldBeCreatedSuccessfully() {
		assertThat(project.getActivities(), hasItem(activity));
	}
}
