package example.junit;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.ActivityInfo;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestValidateNameMethod {
    /**
     * Test the validateName method in the projectApp class
     * @Author: Mathias
     */
    private Employee employee;
    private Project project;
    private ProjectApp projectApp = new ProjectApp();
    private ProjectInfo projectInfo;
    private List<EmployeeInfo> employeeInfoList;

    @Before
    public void setUp() throws Exception {
        employee = projectApp.createEmployee("Huba");
        EmployeeInfo employeeInfo = new EmployeeInfo(employee);
        employeeInfoList = new ArrayList<>();
        employeeInfoList.add(employeeInfo);
        project = projectApp.createProject("Project", employeeInfoList, employeeInfo);
        projectInfo = new ProjectInfo(project);
    }

    //Name is null
    @Test
    public void testInputDatasetA() {
        try {
            projectApp.validateName(null,project);
        } catch (Exception e) {
            assertEquals("Name missing", e.getMessage());
        }
    }
    //Name is an empty string
    @Test
    public void testInputDatasetB() {
        try {
            projectApp.validateName("",project);
        } catch (Exception e) {
            assertEquals("Name missing", e.getMessage());
        }
    }
    //Project is null and no errors occur
    @Test
    public void testInputDatasetC() {
        projectApp.validateName("n1",null);
    }
    //Name is the selected activities name and no errors occur
    @Test
    public void testInputDatasetD() throws Exception {
        Activity activity = projectApp.createActivity(projectInfo,"Activity", "3", "3", "5", employeeInfoList, "2024", "2024");
        ActivityInfo activityInfo = new ActivityInfo(activity);
        projectApp.setActivity(activityInfo);
        projectApp.validateName("Activity", project);
    }
    //No activities exist in the project and no errors occur
    @Test
    public void testInputDatasetE() {
        projectApp.validateName("n1", project);
    }
    //Activity with this name already exists in the project
    @Test
    public void testInputDatasetF() throws Exception {
        Activity activity = projectApp.createActivity(projectInfo,"Activity", "3", "3", "5", employeeInfoList, "2024", "2024");
        try {
            projectApp.validateName("Activity",project);
        } catch (Exception e) {
            assertEquals("Activity with this name already exists in the project", e.getMessage());
        }
    }
    //No activities exist with the given name and no errors occur
    @Test
    public void testInputDatasetG() throws Exception {
        Activity activity = projectApp.createActivity(projectInfo,"Activity", "3", "3", "5", employeeInfoList, "2024", "2024");
        projectApp.validateName("n1", project);
    }
}
