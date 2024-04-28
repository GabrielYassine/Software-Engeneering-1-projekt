package example.cucumber;

import dtu.app.ui.ApplicationProjects;
import dtu.app.ui.domain.*;
import dtu.app.ui.errorMessageHolders.ErrorMessageHolder;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.*;
import static org.junit.Assert.*;

public class ActivitySteps {
	private final ApplicationProjects application;
    private final ErrorMessageHolder errorMessage;
    public ActivitySteps(ApplicationProjects application) {
		this.application = application;
		this.errorMessage = application.getErrorMessage();
	}

	@When("the user creates an activity with the following details")
	public void theUserCreatesAnActivityWithTheFollowingDetails(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String name = columns.get("Name");
			String budgetHours = columns.get("Budget Hours");
			String startWeek = columns.get("Start Week");
			String endWeek = columns.get("End Week");
			String startYear = columns.get("Start Year");
			String endYear = columns.get("End Year");
			String initials = columns.get("Initials");

			List<EmployeeInfo> employees = new ArrayList<>();
			try {
				if (initials != null && !initials.isEmpty()) {
					for (String initial : initials.split(", ")) {
						try {
							employees.add(application.getEmployee(initial));
						} catch (Exception e) {
							errorMessage.setErrorMessage(e.getMessage());
						}
					}
				}
				ActivityInfo activityInfo = new ActivityInfo(application.createActivity(application.getSelectedProject(), name, budgetHours, startWeek, endWeek, employees, startYear, endYear));
				application.setActivity(activityInfo);
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
			}
		}
	}

	@Then("the activity with name {string} should have the following details")
	public void theActivityShouldHaveTheFollowingDetails(String expectedName, DataTable table) throws Exception {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String expectedBudgetHours = columns.get("Budget Hours");
			String expectedStartWeek = columns.get("Start Week");
			String expectedEndWeek = columns.get("End Week");
			String expectedStartYear = columns.get("Start Year");
			String expectedEndYear = columns.get("End Year");
			String expectedInitials = columns.get("Initials");

			ActivityInfo activityInfo = application.getActivity(application.getSelectedProject(), expectedName);

			assertEquals(Double.parseDouble(expectedBudgetHours), activityInfo.getBudgetHours(), 0);
			assertEquals(Integer.parseInt(expectedStartWeek), activityInfo.getStartWeek());
			assertEquals(Integer.parseInt(expectedEndWeek), activityInfo.getEndWeek());
			assertEquals(Integer.parseInt(expectedStartYear), activityInfo.getStartYear());
			assertEquals(Integer.parseInt(expectedEndYear), activityInfo.getEndYear());

			List<Employee> employees = activityInfo.getEmployees();
			for (Employee employee : employees) {
				if (employee.getInitials().equals(expectedInitials)) {
					assertEquals(expectedInitials, employee.getInitials());
					break;
				}
			}
		}
	}

	@Then("the activity should be created")
	public void theActivityShouldBeCreated() throws Exception {
		assertNotNull(application.getSelectedActivity());
	}

	@Then("the activity should not be created")
	public void theActivityShouldNotBeCreated() throws Exception {
		assertNull(application.getSelectedActivity());
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	@When("the user edits the activity with name {string} with the following details")
	public void theEmployeeEditsTheActivityWithTheFollowingDetails(String name, DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			String newName = columns.get("Name");
			String newBudgetHours = columns.get("Budget Hours");
			String newStartWeek = columns.get("Start Week");
			String newEndWeek = columns.get("End Week");
			String newStartYear = columns.get("Start Year");
			String newEndYear = columns.get("End Year");
			String newInitials = columns.get("Initials");

			List<EmployeeInfo> employees = new ArrayList<>();
			if (newInitials != null && !newInitials.isEmpty()) {
				for (String initial : newInitials.split(", ")) {
					try {
						employees.add(application.getEmployee(initial));
					} catch (Exception e) {
						errorMessage.setErrorMessage(e.getMessage());
					}
				}
			}
			try {
				ProjectInfo projectInfo = application.getSelectedProject();
				ActivityInfo activityInfo = application.getActivity(projectInfo, name);
				application.editActivity(activityInfo, newName, newBudgetHours, newStartWeek, newEndWeek, employees, newStartYear, newEndYear);
			} catch (Exception e) {
				errorMessage.setErrorMessage(e.getMessage());
			}
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////

	// Feature: Complete activity

	@When("the user switching completion state of the activity with name {string}")
	public void theUserSwitchingCompletionStateOfTheActivityWithName(String activityName) throws Exception {
		ActivityInfo activity = application.getActivity(application.getSelectedProject(), activityName);
		application.switchActivityCompletion(application.getSelectedProject(), activity);
	}

	@Then("the activity with name {string} is completed")
	public void theActivityWithNameIsCompleted(String activityName) throws Exception {
		assertEquals("Completed", application.getActivity(application.getSelectedProject(), activityName).getCompletionStatus());
	}

	@Then("the activity with name {string} is not completed")
	public void theActivityWithNameIsNotCompleted(String activityName) throws Exception {
		assertEquals("Not completed", application.getActivity(application.getSelectedProject(), activityName).getCompletionStatus());
	}
}
