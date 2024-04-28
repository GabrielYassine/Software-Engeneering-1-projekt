package example.cucumber;

import dtu.app.ui.ApplicationProjects;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.errorMessageHolders.ErrorMessageHolder;
import dtu.app.ui.info.EmployeeInfo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AvailabilityScheduleSteps {

    private final ApplicationProjects application;
    private final ErrorMessageHolder errorMessage;
    private List<Integer> availabilitySchedule;

    public AvailabilityScheduleSteps(ApplicationProjects application) {
        this.application = application;
        this.errorMessage = application.getErrorMessage();
    }

    @When("the user searches for the availability schedule for year {string} and month {string} for the employee with initials {string}")
    public void theUserSearchesForTheAvailabilityScheduleForYearAndMonthForTheEmployeeWithInitials(String year, String month, String initials) {
        try {
            EmployeeInfo e = application.getEmployee(initials);
            this.availabilitySchedule = application.getAvailability(e, year, month);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system shows the following availability calendar for the specified month")
    public void theSystemShowsTheFollowingAvailabilityCalendarForTheSpecifiedMonth(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String firstWeek = columns.get("First Week");
            String secondWeek = columns.get("Second Week");
            String thirdWeek = columns.get("Third Week");
            String fourthWeek = columns.get("Fourth Week");
            String fifthWeek = columns.get("Fifth Week");

            String expectedAvailability = firstWeek + secondWeek + thirdWeek + fourthWeek + fifthWeek;
            StringBuilder actualAvailability = new StringBuilder();
            for (Integer integer : availabilitySchedule) {
                actualAvailability.append(integer);
            }
            assertEquals(expectedAvailability, actualAvailability.toString());
        }
    }
}
