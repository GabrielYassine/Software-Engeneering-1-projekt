package example.cucumber;

import dtu.example.ui.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProjectSteps {

	private final Database app;
	private final ErrorMessageHolder errorMessage;
	private Employee employee;
	private Project project;
	private Activity activity;

	DateServer dateServer;

	public ProjectSteps(Database app) {
		this.app = app;
		this.errorMessage = new ErrorMessageHolder();
		this.dateServer = new DateServer();
	}

	@Given("there is an employee with initials {string}")
	public void thereIsAnEmployeeWithInitials(String initials) throws Exception {
		employee = new Employee(app, initials);
		assertThat(employee.getInitials(),is(equalTo(initials)));
	}

	@When("the company creates a project with the employee {string} and the project name {string}")
	public void theCompanyCreatesAProjectWithTheEmployeeAndTheProjectName(String employeeInitials, String projectName) throws Exception {
		try {
			List<Employee> employees = new ArrayList<>();
			project = new Project(app, projectName, employees);
			Employee employee = app.findEmployeeByInitials(employeeInitials);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project should be created successfully")
	public void theProjectShouldBeCreatedSuccessfully() {
		assertThat(app.getProjects(), hasItem(project));
	}

	@Then("an error message {string} should be given")
	public void anErrorMessageShouldBeGiven(String expectedErrorMessage) {
		assertEquals(expectedErrorMessage, errorMessage.getErrorMessage());
	}

	@When("the company creates a project with the employee {string} and no name")
	public void theCompanyCreatesAProjectWithTheEmployeeAndNoName(String employeeInitials) throws Exception {
		try {
			List<Employee> employees = new ArrayList<>();
			Employee employee = app.findEmployeeByInitials(employeeInitials);
			project = new Project(app, "", employees);
		} catch (IllegalArgumentException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Given("there exists a project with name {string}")
	public void thereExistsAProjectWithID(String projectName) {
		List<Employee> employees = new ArrayList<>();
		project = new Project(app, projectName, employees);
		assertThat(project.getName(),is(equalTo(projectName)));
	}

	@When("the company assigns the employee {string} to the project with ID {int}")
	public void theCompanyAssignsTheEmployeeToTheProjectWithID(String initial, Integer ID) throws Exception {
		try {
			Employee employee = app.findEmployeeByInitials(initial);
			Project project = app.getProjectWithID(ID);
			project.assignToProject(employee);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee {string} should be successfully assigned to the project")
	public void theEmployeeShouldBeSuccessfullyAssignedToTheProject(String initial) {
		boolean employeeFound = false;
		for (Employee employee : project.getEmployees()) {
			if (employee.getInitials().equals(initial)) {
				employeeFound = true;
				break;
			}
		}
		assertThat(employeeFound, is(true));
	}

	@Given("there is an employee with initials {string} assigned to the project with ID {int}")
	public void thereIsAnEmployeeWithInitialsAssignedToTheProjectWithID(String initial, Integer ID) throws Exception {
		employee = new Employee(app, initial);
		assertThat(employee.getInitials(),is(equalTo(initial)));
		Employee employee = app.findEmployeeByInitials(initial);
		Project project = app.getProjectWithID(ID);
		project.assignToProject(employee);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Given("these employees are assigned to a project with ID {int}")
	public void theseEmployeesAreAssignedToAProjectWithID(int ID, List<String> initials) throws Exception {
		for (String initial : initials) {
			employee = new Employee(app, initial);
			Project project = app.getProjectWithID(ID);
			project.assignToProject(employee);
		}
	}

	@When("an employee assigns the employee {string} to be project leader of the project with ID {int}")
	public void anEmployeeAssignsTheEmployeeToBeProjectLeaderOfTheProjectWithID(String initial, Integer ID) throws Exception {
		try {
			Project project = app.getProjectWithID(ID);
			Employee employee = app.findEmployeeByInitials(initial);
			project.assignProjectLeader(employee);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee {string} should be successfully appointed as the project leader for the project with ID {int}")
	public void theEmployeeShouldBeSuccesfullyAppointedAsTheProjectLeaderForTheProjectWithID(String initial, int ID) throws Exception {
		Project project = app.getProjectWithID(ID);
		assertThat(project.getProjectLeader().getInitials(), is(equalTo(initial)));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@When("the company creates an activity with name {string}, and expected hours {string}, scheduled from week {string} to week {string} with ID {int}")
	public void theCompanyCreatesAnActivityWithNameAndExpectedHoursScheduledFromWeekToWeek(String name, String budgetHours, String weekStart, String weekEnd, int ID) throws Exception {
		activity = new Activity(project, name, budgetHours, weekStart, weekEnd);
	}

	@Then("the activity should be created successfully")
	public void theActivityShouldBeCreatedSuccessfully() {
		assertThat(project.getActivities(), hasItem(activity));
	}

	@When("the company tries to create an activity with name {string}, expected hours {string}, scheduled from week {string} to week {string} with ID {int}")
	public void theCompanyTriesToCreateAnActivityWithNameExpectedHoursScheduledFromWeekToWeekWithID(String name, String budgetHours, String startWeek, String endWeek, Integer ID) {
		try {
			Project project = app.getProjectWithID(ID);
			Activity activity = new Activity(project, name, budgetHours, startWeek, endWeek);
			project.appendActivity(activity);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Given("there exists an activity with name {string}, expected hours {string}, scheduled from week {string} to week " + "{string} in the project with ID {int}")
	public void thereExistsAnActivityWithNameExpectedHoursScheduledFromWeekToWeekInTheProject(String name, String budgetHours, String startWeek, String endWeek, int ID) throws Exception{
		project = app.getProjectWithID(ID);
		activity = new Activity(project, name, budgetHours, startWeek, endWeek);
		project.appendActivity(activity);
	}

	@When("the employee edits the activity with name {string}, changing its name to {string}, expected hours to {int}, and rescheduling from week {int} to week {int}")
	public void theEmployeeEditsTheActivityWithNameChangingItsNameToExpectedHoursToAndReschedulingFromWeekToWeek(String oldName, String newName, Integer newBudgetHours, Integer newStartWeek, Integer newEndWeek) {
		activity = project.findActivityByName(oldName);
		activity.editActivity(newName, newBudgetHours, newStartWeek, newEndWeek);
	}

	@Then("the activity {string} should have expected hours as {int} and scheduled from week {int} to week {int}")
	public void theActivityShouldHaveExpectedHoursAsAndScheduledFromWeekToWeek(String name, Integer expectedHours, Integer startWeek, Integer endWeek) {
		activity = project.findActivityByName(name);
		assertThat(activity.getBudgetHours(), is(expectedHours));
		assertThat(activity.getStartWeek(), is(startWeek));
		assertThat(activity.getEndWeek(), is(endWeek));
	}

	@When("the employee tries to edit the activity with name {string}, providing no name, expected hours as {int}, and scheduling from week {int} to week {int}")
	public void theEmployeeTriesToEditTheActivityWithNameProvidingNoNameExpectedHoursAsAndSchedulingFromWeekToWeek(String oldName, Integer newBudgetHours, Integer newStartWeek, Integer newEndWeek) {
		try {
			activity = project.findActivityByName(oldName);
			activity.editActivity("", newBudgetHours, newStartWeek, newEndWeek);
		} catch (IllegalArgumentException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@When("the employee tries to edit the activity with name {string}, changing its name to {string}")
	public void theEmployeeTriesToEditTheActivityWithNameChangingItsNameTo(String oldName, String newName) {
		try {
			activity = project.findActivityByName(oldName);
			activity.editActivity(newName, activity.getBudgetHours(), activity.getStartWeek(), activity.getEndWeek());
		} catch (IllegalArgumentException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@When("the employee tries to edit the activity with name {string}, changing expected hours to {int}, and rescheduling from week {int} to week {int}")
	public void theEmployeeTriesToEditTheActivityWithNameChangingExpectedHoursToAndReschedulingFromWeekToWeek(String oldName, Integer newBudgetHours, Integer newStartWeek, Integer newEndWeek) {
		try {
			activity = project.findActivityByName(oldName);
			if (activity == null) {
				throw new IllegalArgumentException("Activity with this name does not exist in the project");
			}
			activity.editActivity(oldName, newBudgetHours, newStartWeek, newEndWeek);
		} catch (IllegalArgumentException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Given("there exists an activity with the following details")
	public void thereExistsAnActivityWithTheFollowingDetails(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			int projectID = Integer.parseInt(columns.get("projectID"));
			String name = columns.get("name");
			String budgetHours = columns.get("budgetHours");
			String startWeek = columns.get("startWeek");
			String endWeek = columns.get("endWeek");
			try {
				Project project = app.getProjectWithID(projectID);
				Activity activity = new Activity(project, name, budgetHours, startWeek, endWeek);
				project.appendActivity(activity);
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
			}
		}
	}

	@When("the employee {string} registers {int} hours on the activity {string} on {string}")
	public void theEmployeeRegistersHoursOnTheActivityOnDate(String initials, Integer hours, String activityName, String dateStr) throws Exception {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.setTime(format.parse(dateStr));
			employee = app.findEmployeeByInitials(initials);
			activity = project.findActivityByName(activityName);
			employee.getActivityLog().registerHours(date, activity, hours);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the employee {string}'s time spent on {string} on activity {string} should be {int} hours")
	public void theEmployeeSTimeSpentOnDateOnActivityShouldBeHours(String initials, String dateStr, String activityName, int expectedHours) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(format.parse(dateStr));
		employee = app.findEmployeeByInitials(initials);
		activity = project.findActivityByName(activityName);
		assertThat(employee.getActivityLog().getActivityHours(date).get(activity), is(expectedHours));
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
}
