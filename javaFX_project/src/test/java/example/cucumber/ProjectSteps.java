package example.cucumber;

import dtu.example.ui.App;
import dtu.example.ui.Employee;
import dtu.example.ui.Project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ProjectSteps {

	private final App app;

	public ProjectSteps(App app) {
		this.app = app;
	}
	private Employee employee;
	private Project project;

	@Given("there is an employee with initials {string}")
	public void thereIsAnEmployeeWithInitials(String initials) throws Exception {
		employee = new Employee(initials);
		assertThat(employee.getInitials(),is(equalTo(initials)));
		app.appendEmployee(employee);
	}

	@When("the company creates a project with the employee {string} and the project name {string}")
	public void theCompanyCreatesAProjectWithTheEmployeeAndTheProjectName(String employeeInitials, String projectName) {
		Employee employee = app.findEmployeeByInitials(employeeInitials);
		if (employee == null) {
			throw new IllegalArgumentException("Employee with initials " + employeeInitials + " not found.");
		}
		int projectID = generateID();
		project = new Project(projectID, projectName, employee);
		app.appendProject(project);
	}

	@Then("the project should be created successfully")
	public void theProjectShouldBeCreatedSuccessfully() {
		assertThat(app.getProjects(), hasItem(project));
	}


	@Then("the project id should be assigned the project number {int}")
	public void theProjectNumberShouldBeAssignedByTheSystemInTheFormat(int projectNumber) {
		assertThat(project.getID(), is(equalTo(projectNumber)));
	}

	@Then("the project should have a name")
	public void theProjectShouldHaveAName() {
		assertThat(project.getName(), is(notNullValue()));
	}


	public int generateID() {
		int defaultID = 24001;
		if (app.getProjects().isEmpty()) {
			return defaultID;
		} else {
			int maxID = 0;
			for (Project p : app.getProjects()) {
				if (p.getID() > maxID) {
					maxID = p.getID();
				}
			}
			return maxID + 1;
		}
	}
}
