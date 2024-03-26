package example.cucumber;

import dtu.example.ui.App;
import dtu.example.ui.Employee;
import dtu.example.ui.Project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProjectSteps {

	private final App app;
	private ErrorMessageHolder errorMessage;
	private Employee employee;
	private Project project;

	public ProjectSteps(App app) {
		this.app = app;
		this.errorMessage = new ErrorMessageHolder();
	}

	@Given("there is an employee with initials {string}")
	public void thereIsAnEmployeeWithInitials(String initials) throws Exception {
		employee = new Employee(initials);
		assertThat(employee.getInitials(),is(equalTo(initials)));
		app.appendEmployee(employee);
	}

	@When("the company creates a project with the employee {string} and the project name {string}")
	public void theCompanyCreatesAProjectWithTheEmployeeAndTheProjectName(String employeeInitials, String projectName) throws Exception {
		try {
			List<Employee> employees = new ArrayList<>();
			project = new Project(app, projectName, employees);
			app.appendProject(project);

			Employee employee = app.findEmployeeByInitials(employeeInitials);
			employees.add(employee);
		} catch (Exception e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

	@Then("the project should be created successfully")
	public void theProjectShouldBeCreatedSuccessfully() {
		assertThat(app.getProjects(), hasItem(project));
	}

	@Then("the project does not have employee {string} assigned")
	public void theProjectDoesNotHaveEmployeeAssigned(String initials) {
		boolean employeeFound = false;
		for (Employee employee : project.getEmployees()) {
			if (employee.getInitials().equals(initials)) {
				employeeFound = true;
				break;
			}
		}
		assertThat(employeeFound, is(false));
	}

	@Then("an error message {string} should be given")
	public void anErrorMessageShouldBeGiven(String errorMessage) throws Exception {
		assertEquals(errorMessage, this.errorMessage.getErrorMessage());
	}

	@When("the company creates a project with the employee {string} and no name")
	public void theCompanyCreatesAProjectWithTheEmployeeAndNoName(String employeeInitials) throws Exception {
		try {
			List<Employee> employees = new ArrayList<>();
			Employee employee = app.findEmployeeByInitials(employeeInitials);
			employees.add(employee);

			project = new Project(app, "", employees);
			app.appendProject(project);
		} catch (IllegalArgumentException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

}
