package example.junit;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.EmployeeInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestUpdateEmployees {

    /**
     * Test the createProject method in the projectApp class
     *
     * @Author: Ilias
     */

    private ProjectApp projectApp;
    private Project project;
    private Employee projectLeader;

    @Before
    public void setUp() throws Exception {
        projectApp = new ProjectApp();
        projectLeader = projectApp.createEmployee("Huba");
        List<Employee> employees = new ArrayList<>();
        employees.add(projectLeader);

        // Convert Employee list to EmployeeInfo list
        List<EmployeeInfo> employeeInfoList = employees.stream()
                .map(EmployeeInfo::new)
                .collect(Collectors.toList());

        project = projectApp.createProject("Project", employeeInfoList, new EmployeeInfo(projectLeader));
    }

    @Test
    public void testInputDataSetA() {
        project.updateEmployees();
        assertEquals(0, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetB() {
        // Set up the conditions for test case B
        project.setProjectLeader(null);
        project.updateEmployees();
        assertEquals(0, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetC() {
        // Set up the conditions for test case C
        project.setProjectLeader(null);
        // Add an activity to the project
        project.addActivity(new Activity("Activity1"));
        project.updateEmployees();
        assertEquals(0, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetD() {
        // Set up the conditions for test case D
        // Add an activity to the project
        project.addActivity(new Activity("Activity1"));
        project.updateEmployees();
        assertEquals(1, project.getEmployees().size());
        assertEquals(projectLeader, project.getProjectLeader());
    }

// Continue with the rest of the test cases E to N in a similar manner

    @Test
    public void testInputDataSetE() {
        // Set up the conditions for test case E
        project.addEmployee(projectApp.createEmployee("Test"));
        project.updateEmployees();
        assertEquals(2, project.getEmployees().size());
        assertEquals(projectLeader, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetF() {
        // Set up the conditions for test case F
        project.addEmployee(projectApp.createEmployee("Test"));
        projectLeader = projectApp.createEmployee("NewLeader");
        project.updateEmployees();
        assertEquals(2, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetG() {
        // Set up the conditions for test case G
        project.addEmployee(projectApp.createEmployee("Test"));
        projectLeader = projectApp.createEmployee("NewLeader");
        project.addActivity(new Activity("Activity1"));
        project.updateEmployees();
        assertEquals(2, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetH() {
        // Set up the conditions for test case H
        project.addActivity(new Activity("Activity1"));
        project.addActivity(new Activity("Activity2"));
        project.updateEmployees();
        assertEquals(1, project.getEmployees().size());
        assertEquals(projectLeader, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetI() {
        // Set up the conditions for test case I
        project.addActivity(new Activity("Activity1"));
        project.addActivity(new Activity("Activity2"));
        project.setProjectLeader(null);
        project.updateEmployees();
        assertEquals(1, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetJ() {
        // Set up the conditions for test case J
        project.addActivity(new Activity("Activity1"));
        project.addActivity(new Activity("Activity2"));
        project.setProjectLeader(null);
        project.updateEmployees();
        assertEquals(1, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetK() {
        // Set up the conditions for test case K
        project.addActivity(new Activity("Activity1"));
        project.addActivity(new Activity("Activity2"));
        project.updateEmployees();
        assertEquals(1, project.getEmployees().size());
        assertEquals(projectLeader, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetL() {
        // Set up the conditions for test case L
        project.addEmployee(projectApp.createEmployee("Test"));
        project.addActivity(new Activity("Activity1"));
        project.addActivity(new Activity("Activity2"));
        project.updateEmployees();
        assertEquals(2, project.getEmployees().size());
        assertEquals(projectLeader, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetM() {
        // Set up the conditions for test case M
        project.addEmployee(projectApp.createEmployee("Test"));
        projectLeader = projectApp.createEmployee("NewLeader");
        project.addActivity(new Activity("Activity1"));
        project.addActivity(new Activity("Activity2"));
        project.updateEmployees();
        assertEquals(2, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }

    @Test
    public void testInputDataSetN() {
        // Set up the conditions for test case N
        project.addEmployee(projectApp.createEmployee("Test"));
        projectLeader = projectApp.createEmployee("NewLeader");
        project.addActivity(new Activity("Activity1"));
        project.addActivity(new Activity("Activity2"));
        project.updateEmployees();
        assertEquals(2, project.getEmployees().size());
        assertEquals(null, project.getProjectLeader());
    }
}