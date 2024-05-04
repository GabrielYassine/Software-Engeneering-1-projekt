package example.junit;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.EmployeeInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestCreateProjectMethod {

    /**
     * Test the createProject method in the projectApp class
     * @Author: Taemur
     */

    private final ProjectApp projectApp = new ProjectApp();
    private final List<EmployeeInfo> employeeList = new ArrayList<>();
    private final List<EmployeeInfo> emptyEmployeeList = new ArrayList<>();
    private String projectName;
    private EmployeeInfo projectLeaderInfo;

    @Before
    public void setUp() {
        Employee employee1 = projectApp.createEmployee("Huba");
        Employee employee2 = projectApp.createEmployee("Abed");
        Employee employee3 = projectApp.createEmployee("Lisa");
        EmployeeInfo employeeInfo1 = new EmployeeInfo(employee1);
        EmployeeInfo employeeInfo2 = new EmployeeInfo(employee2);
        EmployeeInfo employeeInfo3 = new EmployeeInfo(employee3);
        employeeList.add(employeeInfo1);
        employeeList.add(employeeInfo2);
        employeeList.add(employeeInfo3);
        projectLeaderInfo = employeeInfo1;
    }

    @Test
    public void testInputDataSetA() {
        try {
            projectApp.createProject(projectName, employeeList, projectLeaderInfo);
        } catch (Exception e) {
            assertEquals("Name missing", e.getMessage());
        }
    }

    @Test
    public void testInputDataSetB() {
        projectName = "";
        try {
            projectApp.createProject(projectName, employeeList, projectLeaderInfo);
        } catch (Exception e) {
            assertEquals("Name missing", e.getMessage());
        }
    }

    @Test
    public void testInputDataSetC() {
        projectName = "Project";
        try {
            Project project = projectApp.createProject(projectName, emptyEmployeeList, projectLeaderInfo);
            assertEquals("Project", project.getName());
            assertTrue(project.getEmployees().isEmpty());
            assertNotNull(project.getProjectLeader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInputDataSetD() {
        projectName = "Project";
        try {
            Project project = projectApp.createProject(projectName, employeeList, projectLeaderInfo);
            assertEquals("Project", project.getName());
            assertFalse(project.getEmployees().isEmpty());
            assertNotNull(project.getProjectLeader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInputDataSetE() {
        projectName = "Project";
        try {
            Project project = projectApp.createProject(projectName, emptyEmployeeList, null);
            assertEquals("Project", project.getName());
            assertTrue(project.getEmployees().isEmpty());
            assertNull(project.getProjectLeader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInputDataSetF() {
        projectName = "Project";
        try {
            Project project = projectApp.createProject(projectName, employeeList, null);
            assertEquals("Project", project.getName());
            assertFalse(project.getEmployees().isEmpty());
            assertNull(project.getProjectLeader());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
