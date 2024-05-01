package example.cucumber;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.*;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import java.util.*;
import static org.junit.Assert.*;

public class ProjectSteps {

    private final ProjectApp application;
    private final ErrorMessageHolder errorMessage;

    public ProjectSteps(ProjectApp application, ErrorMessageHolder errorMessage) {
        this.application = application;
        this.errorMessage = errorMessage;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    // Feature: Create project

    @Given("there are employees with the following initials")
    public void thereAreEmployeesWithTheFollowingInitials(List<String> initials) throws Exception {
        for (String initial : initials) {
            Employee employee = application.createEmployee(initial);
        }
    }

    @When("the user creates a project with the following details")
    public void theUserCreatesAProjectWithTheFollowingDetails(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String name = columns.get("Name");
            String initials = columns.get("Initials");
            String projectLeaderInitials = columns.get("ProjectLeader");

            List<EmployeeInfo> employees = new ArrayList<>();
            try {
                if (initials != null && !initials.isEmpty()) {
                    for (String initial : initials.split(", ")) {
                        employees.add(application.getEmployee(initial));
                    }
                }
                EmployeeInfo projectLeader = null;
                if (projectLeaderInitials != null && !projectLeaderInitials.isEmpty()) {
                    projectLeader = application.getEmployee(projectLeaderInitials);
                }
                application.setProject(new ProjectInfo(application.createProject(name, employees, projectLeader)));
            } catch (Exception e) {
                errorMessage.setErrorMessage(e.getMessage());
            }
        }
    }

    @Then("the project should have the following details")
    public void theProjectShouldHaveTheFollowingDetails(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String id = columns.get("ID");
            String name = columns.get("Name");
            String initials = columns.get("Initials");
            String projectLeaderInitials = columns.get("ProjectLeader");
            List<Employee> employees = new ArrayList<>();
            try {
                ProjectInfo projectInfo = application.getProject(id);

                assertEquals(id, String.valueOf(projectInfo.getID()));
                assertEquals(name, projectInfo.getName());

                if (projectLeaderInitials != null && !projectLeaderInitials.isEmpty()) {
                    assertEquals(projectLeaderInitials, projectInfo.getProjectLeader().getInitials());
                } else {
                    assertNull(projectInfo.getProjectLeader());
                }

                List<String> employeeInitials = projectInfo.getEmployeeInitials();
                List<String> expectedEmployeeInitialsList = new ArrayList<>();
                if (initials != null && !initials.isEmpty()) {
                    expectedEmployeeInitialsList = Arrays.asList(initials.split(", "));
                }
                assertEquals(expectedEmployeeInitialsList, employeeInitials);
            } catch (Exception e) {
                errorMessage.setErrorMessage(e.getMessage());
            }
        }
    }

    @Then("the project should be created")
    public void theProjectShouldBeCreated() throws Exception {
        assertNotNull(application.getSelectedProject());
    }

    @Then("the project should not be created")
    public void theProjectShouldNotBeCreated() throws Exception {
        assertNull(application.getSelectedProject());
    }

    @Then("an error message {string} should be given")
    public void anErrorMessageShouldBeGiven(String expectedErrorMessage) {
        assertEquals(expectedErrorMessage, errorMessage.getErrorMessage());
    }

    //////////////////////////////////////////////////////////////////////////////////////

    // Feature: Edit project

    @Given("there is a project with name {string}")
	public void thereIsAProjectWithName(String name) throws Exception {
        application.setProject(new ProjectInfo(application.createProject(name, new ArrayList<>(), null)));;
	}

	@Given("there are employees with the following initials in the project")
	public void thereAreEmployeesWithTheFollowingInitialsInTheProject(List<String> initials) throws Exception {
		for (String initial : initials) {
            Employee employee = application.createEmployee(initial);
            application.addEmployeeToProject(application.getSelectedProject(), employee);
		}
	}
    @When("the employee edits the project with ID {string} name to {string}, the project leader to {string}, and the project members to {string}")
    public void theEmployeeEditsTheProjectWithIDNameToTheProjectLeaderToAndTheProjectMembersTo(String id, String newName, String newProjectLeaderInitials, String newEmployeeInitials) throws Exception {
        ProjectInfo projectToEdit = application.getProject(id);
        List<EmployeeInfo> employees = new ArrayList<>();

        if (newEmployeeInitials != null && !newEmployeeInitials.isEmpty()) {
            for (String initial : newEmployeeInitials.split(", ")) {
                employees.add(application.getEmployee(initial));
            }
        }
        try {
            application.editProject(projectToEdit, newName, newProjectLeaderInitials, employees);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project with ID {string} name should be {string}, the project leader should be {string}, and the project members should be {string}")
    public void theProjectWithIDNameShouldBeTheProjectLeaderShouldBeAndTheProjectMembersShouldBe(String id, String expectedProjectName, String expectedProjectLeaderInitials, String expectedEmployeeInitials) throws Exception {
        ProjectInfo projectInfo = application.getProject(id);

        assertEquals(expectedProjectName, projectInfo.getName());

        if (expectedProjectLeaderInitials == null || expectedProjectLeaderInitials.isEmpty()) {
            assertNull(projectInfo.getProjectLeader());
        } else {
            assertEquals(expectedProjectLeaderInitials, projectInfo.getProjectLeader().getInitials());
        }

        List<String> employeeInitials = projectInfo.getEmployeeInitials();
        List<String> expectedEmployeeInitialsList = new ArrayList<>();
        if (expectedEmployeeInitials != null && !expectedEmployeeInitials.isEmpty()) {
            expectedEmployeeInitialsList = Arrays.asList(expectedEmployeeInitials.split(", "));
        }
        assertEquals(expectedEmployeeInitialsList, employeeInitials);
    }

    @Given("there are projects with following details")
    public void thereAreProjectWithFollowingDetails(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String name = columns.get("Name");
            String initials = columns.get("Initials");
            String projectLeaderInitials = columns.get("ProjectLeader");

            EmployeeInfo projectLeader = null;
            if (projectLeaderInitials != null && !projectLeaderInitials.isEmpty()) {
                projectLeader = application.getEmployee(projectLeaderInitials);
            }

            List<EmployeeInfo> employees = new ArrayList<>();
            if (initials != null && !initials.isEmpty()) {
                for (String initial : initials.split(", ")) {
                    employees.add(application.getEmployee(initial));
                }
            }
            application.createProject(name, employees, projectLeader);
        }
    }

    @When("the employee edits the projects name to {string}, the project leader to {string}, and the project members to {string}")
    public void theEmployeeEditsTheProjectWithIDSNameToTheProjectLeaderToAndTheProjectMembersTo(String newName, String newProjectLeaderInitials, String newEmployeeInitials) throws Exception {
        try {
            ProjectInfo projectToEdit = application.getSelectedProject();
            List<EmployeeInfo> employees = new ArrayList<>();
            if (newEmployeeInitials != null && !newEmployeeInitials.isEmpty()) {
                for (String initial : newEmployeeInitials.split(", ")) {
                    employees.add(application.getEmployee(initial));
                }
            }
            application.editProject(projectToEdit, newName, newProjectLeaderInitials, employees);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project with ID {string}'s name should be {string}, the project leader should be {string}, and the project members should be {string}")
    public void theProjectWithIDSNameShouldBeTheProjectLeaderShouldBeAndTheProjectMembersShouldBe(String id, String expectedProjectName, String expectedProjectLeaderInitials, String expectedEmployeeInitials) throws Exception {
        ProjectInfo projectInfo = application.getProject(id);
        assertEquals(expectedProjectName, projectInfo.getName());

        if (expectedProjectLeaderInitials == null || expectedProjectLeaderInitials.isEmpty()) {
            assertNull(projectInfo.getProjectLeader());
        } else {
            assertEquals(expectedProjectLeaderInitials, projectInfo.getProjectLeader().getInitials());
        }

        List<String> employeeInitials = projectInfo.getEmployeeInitials();
        List<String> expectedEmployeeInitialsList = new ArrayList<>();
        if (expectedEmployeeInitials != null && !expectedEmployeeInitials.isEmpty()) {
            expectedEmployeeInitialsList = Arrays.asList(expectedEmployeeInitials.split(", "));
        }
        assertEquals(expectedEmployeeInitialsList, employeeInitials);
    }

    //////////////////////////////////////////////////////////////////////////////////////

	// Feature: Show completion status on project

    @Then("the completion status should be {string}")
    public void theCompletionStatusShouldBe(String expectedCompletionStatus) throws Exception {
        ProjectInfo projectInfo = application.getSelectedProject();
        String completionStatus = application.getProjectCompletionStatus(projectInfo);
        assertEquals(expectedCompletionStatus, completionStatus);
    }

    //////////////////////////////////////////////////////////////////////////////////////

}

