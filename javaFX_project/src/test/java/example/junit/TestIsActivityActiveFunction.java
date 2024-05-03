package example.junit;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.Project;
import dtu.app.ui.info.EmployeeInfo;
import dtu.app.ui.info.ProjectInfo;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestIsActivityActiveFunction {

	/**
	 * Test the isActivityActive function in the Employee class, the method has been made public for testing purposes
	 */
	private Employee employee;
	private Activity activity;

	@Before
	public void setUp() throws Exception {
		ProjectApp projectApp = new ProjectApp();
		employee = projectApp.createEmployee("Huba");
		EmployeeInfo employeeInfo = new EmployeeInfo(employee);
		List<EmployeeInfo> employeeInfoList = new ArrayList<>();
		employeeInfoList.add(employeeInfo);
		Project project = projectApp.createProject("Project", employeeInfoList, employeeInfo);
		ProjectInfo projectInfo = new ProjectInfo(project);
		activity = projectApp.createActivity(projectInfo,"Activity", "3", "3", "5", employeeInfoList, "2024", "2024");
	}


	// Selected Year is less than the activities start year
	@Test
	public void testInputDataSetA() {
		assertFalse(employee.isActivityActive(activity, 3, 2023));
	}

	// Selected Year is greater than the activities end year
	@Test
	public void testInputDataSetB() {
		assertFalse(employee.isActivityActive(activity, 3, 2025));
	}

	// Selected Year is the as the activities start year but the selected week is less than the activities start week
	@Test
	public void testInputDataSetC() {
		assertFalse(employee.isActivityActive(activity, 2, 2024));
	}

	// Selected Year is the as the activities end year but the selected week is greater than the activities end week
	@Test
	public void testInputDataSetD() {
		assertFalse(employee.isActivityActive(activity, 6, 2024));
	}

	// Selected Year and week is within the activities start and end year and week
	@Test
	public void testInputDataSetE() {
		assertTrue(employee.isActivityActive(activity, 4, 2024));
	}

}