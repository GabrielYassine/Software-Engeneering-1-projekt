package example.cucumber;

import dtu.example.ui.App;
import dtu.example.ui.Employee;
import dtu.example.ui.Project;

import io.cucumber.java.en.And;
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
			project = new Project(app, "", employees);
		} catch (IllegalArgumentException e) {
			errorMessage.setErrorMessage(e.getMessage());
		}
	}

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

	@Then("the employee {string} should be succesfully appointed as the project leader for the project with ID {int}")
	public void theEmployeeShouldBeSuccesfullyAppointedAsTheProjectLeaderForTheProjectWithID(String initial, int ID) throws Exception {
		Project project = app.getProjectWithID(ID);
		assertThat(project.getProjectLeader().getInitials(), is(equalTo(initial)));
	}
}
