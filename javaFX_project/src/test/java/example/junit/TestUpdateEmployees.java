package example.junit;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class TestUpdateEmployees {

    /**
     * Test the createProject method in the projectApp class
     *
     * @Author: Ilias
     */

    private ProjectApp projectApp;
    private Project project;
    private Employee projectLeader;

    private List<EmployeeInfo> employeeInfoList;

    private List<EmployeeInfo> emptyList= new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        projectApp = new ProjectApp();
        projectLeader = projectApp.createEmployee("Huba");
        List<Employee> employees = new ArrayList<>();
        employees.add(projectLeader);

        // Convert Employee list to EmployeeInfo list
        employeeInfoList = employees.stream()
                .map(EmployeeInfo::new)
                .collect(Collectors.toList());
    }

    @Test
    public void testInputDataSetA() throws Exception {
        project = projectApp.createProject("Project", emptyList, new EmployeeInfo(projectLeader));
        project.updateEmployees();
        assertTrue(project.getEmployees().isEmpty());
        assertNull(project.getProjectLeader());
        assertTrue(project.getActivities().isEmpty());
    }

    @Test
    public void testInputDataSetB() throws Exception {
        // Set up the conditions for test case B
        project = projectApp.createProject("Project", emptyList, null);
        project.updateEmployees();
        assertTrue(project.getEmployees().isEmpty());
        assertNull(project.getProjectLeader());
        assertTrue(project.getActivities().isEmpty());
    }
    @Test
    public void testInputDataSetC() throws Exception {
        // Set up the conditions for test case E
        project = projectApp.createProject("Project", employeeInfoList, new EmployeeInfo(projectLeader));
        project.addEmployee(projectApp.createEmployee("Test"));
        project.updateEmployees();
        assertFalse(project.getEmployees().isEmpty());
        assertEquals(projectLeader, project.getProjectLeader());
        assertTrue(project.getActivities().isEmpty());
    }
    @Test
    public void testInputDataSetD() throws Exception {
        // Set up the conditions for test case H
        project = projectApp.createProject("Project", emptyList, new EmployeeInfo(projectLeader));
        project.addActivity(new Activity(project,"activity1", 10.0, 1, 52, 2024, 2024));
        project.addActivity(new Activity(project,"activity2", 10.0, 1, 52, 2024, 2024));
        project.updateEmployees();
        assertFalse(project.getActivities().isEmpty());
        assertNull(project.getProjectLeader());
    }

    @Test
    public void testInputDataSetE() throws Exception {
        // Set up the conditions for test case I
        project = projectApp.createProject("Project", emptyList, new EmployeeInfo(projectLeader));
        project.addActivity(new Activity(project,"activity1", 10.0, 1, 52, 2024, 2024));
        project.addActivity(new Activity(project,"activity2", 10.0, 1, 52, 2024, 2024));
        project.updateEmployees();
        assertFalse(project.getActivities().isEmpty());
        assertNull(project.getProjectLeader());
    }
    @Test
    public void testInputDataSetF() throws Exception {
        // Set up the conditions for test case L
        project = projectApp.createProject("Project", employeeInfoList, new EmployeeInfo(projectLeader));
        project.addEmployee(projectApp.createEmployee("Test"));
        project.addActivity(new Activity(project,"activity1", 10.0, 1, 52, 2024, 2024));
        project.addActivity(new Activity(project,"activity2", 10.0, 1, 52, 2024, 2024));
        project.updateEmployees();
        assertEquals(projectLeader, project.getProjectLeader());
        assertFalse(project.getActivities().isEmpty());
        assertFalse(project.getEmployees().isEmpty());
    }
}