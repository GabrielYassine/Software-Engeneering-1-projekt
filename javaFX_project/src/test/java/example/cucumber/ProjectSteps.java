package example.cucumber;

import dtu.app.ui.classes.*;
import dtu.app.ui.pages.App;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ProjectSteps {

	private final App app;
	private Database database;
	private final ErrorMessageHolder errorMessage;
	private Employee employee;
	private Project project;
	private Activity activity;
	private ActivityLog weekActivities;
	private String selectedWeek;

	public ProjectSteps(App app) {
		this.app = app;
		this.errorMessage = new ErrorMessageHolder();
		this.database = new Database();
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	// Feature: Create a project

	@Given("there are employees with the following initials")
	public void thereAreEmployeesWithTheFollowingInitials(List<String> initials) throws Exception {
		for (String initial : initials) {
			employee = new Employee(database, initial);
		}
	}

	@When("the user creates a project with the following details")
	public void theUserCreatesAProjectWithTheFollowingDetails(DataTable table) throws Exception {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String name = columns.get("Name");
			String initials = columns.get("Initials");
			String projectLeaderInitials = columns.get("ProjectLeader");
			Employee projectLeader = database.getEmployee(projectLeaderInitials);
			List<Employee> employees = new ArrayList<>();
			if (initials != null && !initials.isEmpty()) {
				for (String initial : initials.split(", ")) {
					employees.add(database.getEmployee(initial));
				}
			}
			try {
				project = new Project(database, name, employees, projectLeader);
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
			}
		}
	}

	@Then("the project should have the following details")
	public void theProjectShouldHaveTheFollowingDetails(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String name = columns.get("Name");
			String projectLeaderInitials = columns.get("ProjectLeader");
			String employeeInitials = columns.get("Initials");
			assertEquals(name, project.getName());

			if (projectLeaderInitials == null || projectLeaderInitials.isEmpty()) {
				assertNull(project.getProjectLeader());
			} else {
				assertEquals(projectLeaderInitials, project.getProjectLeader().getInitials());
			}

			List<Employee> employees = project.getEmployees();
			for (Employee employee : employees) {
				if (employee.getInitials().equals(employeeInitials)) {
					assertEquals(employeeInitials, employee.getInitials());
					break;
				}
			}
		}
	}

	@Then("the project should be created")
	public void theProjectShouldBeCreated() {
		assertThat(database.getProjects(), hasItem(project));
	}

	@Then("the project should not be created")
	public void theProjectShouldNotBeCreated() {
		assertThat(database.getProjects(), not(hasItem(project)));
	}

	@Then("an error message {string} should be given")
	public void anErrorMessageShouldBeGiven(String expectedErrorMessage) {
		assertEquals(expectedErrorMessage, errorMessage.getErrorMessage());
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	// Feature: Edit a project

	@Given("there is a project with name {string}")
	public void thereIsAProjectWithName(String name) throws Exception {
		project = new Project(database, name, new ArrayList<>(), null);
	}


	@Given("there are employees with the following initials in the project")
	public void thereAreEmployeesWithTheFollowingInitialsInTheProject(List<String> initials) {
		for (String initial : initials) {
			Employee employee = new Employee(database, initial);
			project.addEmployee(employee);
		}
	}

	@When("the employee edits the project name to {string}, the project leader to {string}, and the project members to {string}")
	public void theEmployeeEditsTheProjectNameToTheProjectLeaderToAndTheProjectMembersTo(String newName, String newProjectLeaderInitials, String newEmployeeInitials) throws Exception {
		List<Employee> employees = new ArrayList<>();
		if (newEmployeeInitials != null && !newEmployeeInitials.isEmpty()) {
			for (String initial : newEmployeeInitials.split(", ")) {
				employees.add(database.getEmployee(initial));
			}
		}
		try {
			project.editProject(newName, newProjectLeaderInitials, employees);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project name should be {string}, the project leader should be {string}, and the project members should be {string}")
	public void theProjectNameShouldBeTheProjectLeaderShouldBeAndTheProjectMembersShouldBe(String expectedProjectName, String expectedProjectLeaderInitials, String expectedEmployeeInitials) {
		assertEquals(expectedProjectName, project.getName());
		if (expectedProjectLeaderInitials == null || expectedProjectLeaderInitials.isEmpty()) {
			assertNull(project.getProjectLeader());
		} else {
			assertEquals(expectedProjectLeaderInitials, project.getProjectLeader().getInitials());
		}
		List<Employee> employees = project.getEmployees();
		for (Employee employee : employees) {
			if (employee.getInitials().equals(expectedEmployeeInitials)) {
				assertEquals(expectedEmployeeInitials, employee.getInitials());
				break;
			}
		}
	}

	@Given("there are project with following details")
	public void thereAreProjectWithFollowingDetails(DataTable table) throws Exception {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String id = columns.get("ID");
			String name = columns.get("Name");
			String initials = columns.get("Initials");
			String projectLeaderInitials = columns.get("ProjectLeader");
			Employee projectLeader = database.getEmployee(projectLeaderInitials);
			List<Employee> employees = new ArrayList<>();
			if (initials != null && !initials.isEmpty()) {
				for (String initial : initials.split(", ")) {
					employees.add(database.getEmployee(initial));
				}
			}
			project = new Project(database, name, employees, projectLeader);
		}
	}

	@When("the employee edits the project with ID {string}'s name to {string}, the project leader to {string}, and the project members to {string}")
	public void theEmployeeEditsTheProjectWithIDSNameToTheProjectLeaderToAndTheProjectMembersTo(String id, String newName, String newProjectLeaderInitials, String newEmployeeInitials) throws Exception {
		Project projectToEdit = database.getProject(Integer.parseInt(id));
		List<Employee> employees = new ArrayList<>();
		if (newEmployeeInitials != null && !newEmployeeInitials.isEmpty()) {
			for (String initial : newEmployeeInitials.split(", ")) {
				employees.add(database.getEmployee(initial));
			}
		}
		try {
			projectToEdit.editProject(newName, newProjectLeaderInitials, employees);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project with ID {string}'s name should be {string}, the project leader should be {string}, and the project members should be {string}")
	public void theProjectWithIDSNameShouldBeTheProjectLeaderShouldBeAndTheProjectMembersShouldBe(String id, String expectedProjectName, String expectedProjectLeaderInitials, String expectedEmployeeInitials) throws Exception {
		Project projectToCheck = database.getProject(Integer.parseInt(id));
		assertEquals(expectedProjectName, projectToCheck.getName());
		assertEquals(expectedProjectLeaderInitials, projectToCheck.getProjectLeader().getInitials());

		List<Employee> employees = projectToCheck.getEmployees();
		for (Employee employee : employees) {
			if (employee.getInitials().equals(expectedEmployeeInitials)) {
				assertEquals(expectedEmployeeInitials, employee.getInitials());
				break;
			}
		}
	}

	@Then("the project with ID {string}'s name should still be {string}")
	public void theProjectWithIDSNameShouldStillBe(String id, String expectedProjectName) throws Exception {
		Project projectToCheck = database.getProject(Integer.parseInt(id));
		assertEquals(expectedProjectName, projectToCheck.getName());
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	// Feature: Create an activity

	@When("the user creates an activity with the following details")
	public void theUserCreatesAnActivityWithTheFollowingDetails(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String name = columns.get("Name");
			String budgetHours = columns.get("Budget Hours");
			String startWeek = columns.get("Start Week");
			String endWeek = columns.get("End Week");
			String initials = columns.get("Initials");
			List<Employee> employees = new ArrayList<>();
			if (initials != null && !initials.isEmpty()) {
				for (String initial : initials.split(", ")) {
					try {
						employees.add(database.getEmployee(initial));
					} catch (Exception e) {
						errorMessage.setErrorMessage(e.getMessage());
					}
				}
			}
			try {
				activity = new Activity(project, name, budgetHours, startWeek, endWeek, employees);
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
			}
		}
	}

	@And("the activity should have the following details")
	public void theActivityShouldHaveTheFollowingDetails(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String expectedName = columns.get("Name");
			String expectedBudgetHours = columns.get("Budget Hours");
			String expectedStartWeek = columns.get("Start Week");
			String expectedEndWeek = columns.get("End Week");
			String expectedInitials = columns.get("Initials");
			assertEquals(expectedName, activity.getName());
			assertEquals(Integer.parseInt(expectedBudgetHours), activity.getBudgetHours());
			assertEquals(Integer.parseInt(expectedStartWeek), activity.getStartWeek());
			assertEquals(Integer.parseInt(expectedEndWeek), activity.getEndWeek());
			List<Employee> employees = activity.getEmployees();
			for (Employee employee : employees) {
				if (employee.getInitials().equals(expectedInitials)) {
					assertEquals(expectedInitials, employee.getInitials());
					break;
				}
			}
		}
	}

	@Then("the activity should be created")
	public void theActivityShouldBeCreated() {
		assertThat(project.getActivities(), hasItem(activity));
	}

	@Then("the activity should not be created")
	public void theActivityShouldNotBeCreated() {
		assertThat(project.getActivities(), not(hasItem(activity)));
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	// Feature: Edit an activity

	@And("there is an activity with name {string} in the project, with the following details")
	public void thereIsAnActivityWithNameInTheProjectWithTheFollowingDetails(String name, DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String budgetHours = columns.get("Budget Hours");
			String startWeek = columns.get("Start Week");
			String endWeek = columns.get("End Week");
			String initials = columns.get("Initials");

			List<Employee> employees = new ArrayList<>();
			if (initials != null && !initials.isEmpty()) {
				for (String initial : initials.split(", ")) {
					try {
						employees.add(database.getEmployee(initial));
					} catch (Exception e) {
						errorMessage.setErrorMessage(e.getMessage());
					}
				}
			}

			try {
				activity = new Activity(project, name, budgetHours, startWeek, endWeek, employees);
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
			}
		}
	}

	@When("the user edits the activity with the following details")
	public void theEmployeeEditsTheActivityWithTheFollowingDetails(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String newName = columns.get("Name");
			String newBudgetHours = columns.get("Budget Hours");
			String newStartWeek = columns.get("Start Week");
			String newEndWeek = columns.get("End Week");

			try {
				activity.editActivity(newName, newBudgetHours, newStartWeek, newEndWeek);
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	// Feature: Register hours

	@When("the employee with initials {string} registers {int} hours on the activity {string} on the date {string}")
	public void theEmployeeWithInitialsRegistersHoursOnTheActivityOnTheDate(String initials, int hours, String activityName, String date) throws Exception {
		try {
			Employee employee = project.getEmployee(initials);
			Activity activity = project.getActivity(activityName);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = format.parse(date);
			calendar.setTime(parsedDate);
			employee.getActivityLog().registerHours(calendar, activity, String.valueOf(hours));
			activity.registerHours(hours);
		} catch (ParseException e) {
			if (date.isEmpty()) {
				errorMessage.setErrorMessage("Date missing");
			} else {
				errorMessage.setErrorMessage(e.getMessage());
			}
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity {string} should have {int} hours registered by {string} on the date {string}")
	public void theActivityShouldHaveHoursRegisteredByOnTheDate(String activityName, int expectedHours, String initials, String date) throws Exception {
		try {
			Activity activity = project.getActivity(activityName);
			Employee employee = project.getEmployee(initials);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = format.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parsedDate);

			Map<Activity, Integer> hoursLog = employee.getActivityLog().getDateActivities(calendar);
			int registeredHours = hoursLog.get(activity);
			assertEquals(expectedHours, registeredHours);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the activity {string} should have {int} hours registered in total")
	public void theActivityShouldHaveHoursRegisteredInTotal(String activityName, int totalHours) {
		Activity activity = project.getActivity(activityName);
		int registeredHours = activity.getHoursSpent();
		assertEquals(totalHours, registeredHours);
	}

	////////////////////////////////////////////////////////////////////////////////

	// Feature: Complete activity

	@Given("an activity is open")
	public void anActivityIsOpen() {
		if (activity.getCompletedStatus()) {
			activity.completeActivity();
		}
		assertFalse(activity.getCompletedStatus());
	}

	@When("the user closes the activity")
	public void theUserClosesTheActivity() {
		activity.completeActivity();
	}

	@Then("the activity is completed")
	public void theActivityIsCompleted() {
		assertTrue(activity.getCompletedStatus());
	}

	@Given("an activity is closed")
	public void anActivityIsClosed() {
		if (!activity.getCompletedStatus()) {
			activity.completeActivity();
		}
		assertTrue(activity.getCompletedStatus());
	}

	@When("the user opens the activity")
	public void theUserOpensTheActivity() {
		activity.completeActivity();
	}

	@Then("the activity is not completed")
	public void theActivityIsNotCompleted() {
		assertFalse(activity.getCompletedStatus());
	}

	////////////////////////////////////////////////////////////////////////////////

	// Feature: Show completion status on project

	@When("the user checks the completion status")
	public void theUserChecksTheCompletionStatus() {

	}

	@Then("the user gets the number of activities completed and the total number of activities")
	public void theUserGetsTheNumberOfActivitiesCompletedAndTheTotalNumberOfActivities() {
		assertEquals("0/0", project.getActivitiesCompleted());
	}

	@Given("the employee with initials {string} has registered {string} hours for the activity {string} on the date {string}")
	public void theEmployeeWithInitialsHasRegisteredHoursForTheActivityOnTheDate(String initials, String hours, String activityName, String date) {
		try {
			Employee employee = database.getEmployee(initials);
			Activity activity = project.getActivity(activityName);

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = format.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parsedDate);

			employee.getActivityLog().registerHours(calendar, activity, hours);
			activity.registerHours(Integer.parseInt(hours));
		} catch (ParseException e) {
			if (date.isEmpty()) {
				errorMessage.setErrorMessage("Date missing");
			} else {
				errorMessage.setErrorMessage(e.getMessage());
			}
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@When("the employee searches for the schedule of the employee(s) with initials {string} for the year {int} and week {int}")
	public void theEmployeeSearchesForTheScheduleOfTheEmployeeSWithInitialsForTheYearAndWeek(String initials, int year, int week) throws Exception {
		try {
			Employee employee = database.getEmployee(initials);
			this.weekActivities = employee.getActivityLog().getWeekActivities(String.valueOf(year), String.valueOf(week));
			this.selectedWeek = String.valueOf(week);
		}
		catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the selected week for employee with initials {string} should contain the following details")
	public void theSelectedWeekForEmployeeWithInitialsShouldContainTheFollowingDetails(String initials, DataTable table) throws ParseException {
		try {
			List<Map<String, String>> rows = table.asMaps(String.class, String.class);
			for (Map<String, String> columns : rows) {
				String expectedDate = columns.get("Date");
				String expectedActivityName = columns.get("Activity Name");
				String expectedHours = columns.get("Hours");

				// If expectedDate is null or empty, skip this iteration
				if (expectedDate == null || expectedDate.isEmpty()) {
					continue;
				}

				// Convert the expectedDate to a Calendar instance
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = format.parse(expectedDate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(parsedDate);

				// Get the activities for the expected date
				Map<Activity, Integer> dateActivities = weekActivities.getDateActivities(calendar);

				// Check that the activity with the expected name exists and has the expected hours
				boolean activityFound = false;
				for (Map.Entry<Activity, Integer> entry : dateActivities.entrySet()) {
					if (entry.getKey().getName().equals(expectedActivityName) && entry.getValue().equals(Integer.parseInt(expectedHours))) {
						activityFound = true;
						break;
					}
				}

				assertTrue("Activity with name '" + expectedActivityName + "' and hours '" + expectedHours + "' not found for date '" + expectedDate + "'", activityFound);
			}
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the weekday {int} for the employee with initials {string} should contain the following details")
	public void theDayForEmployeeWithInitialsShouldContainTheFollowingDetails(Integer dayOfWeek, String initials, DataTable table) throws Exception {
		try {
			// Convert the expected details into a list of maps for easier comparison
			List<Map<String, String>> rows = table.asMaps(String.class, String.class);
			for (Map<String, String> columns : rows) {
				String expectedActivityName = columns.get("Activity Name");
				String expectedHours = columns.get("Hours");

				// Retrieve the employee
				Employee employee = database.getEmployee(initials);

				int weekOfYear = Integer.parseInt(selectedWeek);

				// Get the activities for the expected day of the week
				List<String> dateActivities = employee.getActivityLog().getDayActivities(weekOfYear, dayOfWeek);

				// Check that the activity with the expected name exists and has the expected hours
				boolean activityFound = false;
				for (String activityInfo : dateActivities) {
					String[] activityInfoParts = activityInfo.split("-");
					if (activityInfoParts[0].equals(expectedActivityName) && activityInfoParts[1].equals(expectedHours)) {
						activityFound = true;
						break;
					}
				}

				// If no activities were found, assert that dateActivities is empty
				if (!activityFound) {
					assertTrue(dateActivities.isEmpty());
				}
			}
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

    @When("the employee searches for the schedule of the employee with initials {string} for the year {int} and week {string}")
    public void theEmployeeSearchesForTheScheduleOfTheEmployeeWithInitialsForTheYearAndWeek(String initials, int year, String week) {

		try {
			Employee employee = database.getEmployee(initials);
			this.weekActivities = employee.getActivityLog().getWeekActivities(String.valueOf(year), week);
			this.selectedWeek = week;
		} catch (Exception e){
			errorMessage.setErrorMessage(e.getMessage());
		}

    }

	@When("the employee searches for the schedule of the employee with initials {string} for the year {string} and week {int}")
	public void theEmployeeSearchesForTheScheduleOfTheEmployeeWithInitialsForTheYearAndWeek(String initials, String year, int week) {

		try {
			Employee employee = database.getEmployee(initials);
			this.weekActivities = employee.getActivityLog().getWeekActivities(year, String.valueOf(week));
			this.selectedWeek = year;
		} catch (Exception e){
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

}
