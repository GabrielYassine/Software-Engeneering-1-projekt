package example.cucumber;

import dtu.app.ui.ApplicationProjects;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.errorMessageHolders.ErrorMessageHolder;
import dtu.app.ui.info.ActivityLogInfo;
import dtu.app.ui.info.EmployeeInfo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EmployeeScheduleSteps {

    private final ApplicationProjects application;
    private final ErrorMessageHolder errorMessage;
    private ActivityLogInfo weekLogInfo;

    public EmployeeScheduleSteps(ApplicationProjects application) {
        this.application = application;
        this.errorMessage = application.getErrorMessage();
    }

    @When("the user searches for the schedule of the employee with initials {string} for the year {string} and week {string}")
    public void theUserSearchesForTheScheduleOfTheEmployeeWithInitialsForTheYearAndWeek(String initials, String year, String week) {
        try {
            EmployeeInfo employee = application.getEmployee(initials);
            this.weekLogInfo = application.getEmployeeWeekLog(employee, year, week);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the following details about the specified week should be displayed")
    public void theFollowingDetailsAboutTheSpecifiedWeekShouldBeDisplayed(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String expectedDate = columns.get("Date");
            String expectedActivityName = columns.get("Activity Name");
            String expectedHours = columns.get("Hours");

            if (weekLogInfo.isEmpty()) {
                assertNull(expectedDate);
                assertNull(expectedActivityName);
                assertNull(expectedHours);
            } else {
                assertEquals("Date: " + expectedDate + "\n" +
                        "Activity: " + expectedActivityName + "\n" +
                        "Hours: " + expectedHours + "\n", weekLogInfo.toString());
            }
        }
    }

}
