package example.cucumber;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.info.EmployeeInfo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AvailabilityScheduleSteps {

    private final ProjectApp application;
    private final ErrorMessageHolder errorMessage;
    private Map<EmployeeInfo, List<Integer>> availabilitySchedule;

    public AvailabilityScheduleSteps(ProjectApp application, ErrorMessageHolder errorMessage) {
        this.application = application;
        this.errorMessage = errorMessage;
    }

    @When("the user searches for the availability schedule for year {string} and month {string}")
    public void theUserSearchesForTheAvailabilityScheduleForYearAndMonthForTheEmployeeWithInitials(String year, String month) {
        try {
            this.availabilitySchedule = application.getAvailability(year, month);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the system shows the following availability calendar for the specified month")
    public void theSystemShowsTheFollowingAvailabilityCalendarForTheSpecifiedMonth(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String initials = columns.get("Initials");
            String firstWeek = columns.get("First Week");
            String secondWeek = columns.get("Second Week");
            String thirdWeek = columns.get("Third Week");
            String fourthWeek = columns.get("Fourth Week");
            String fifthWeek = columns.get("Fifth Week");

            String expectedAvailability = firstWeek + secondWeek + thirdWeek + fourthWeek + fifthWeek;
            for (Map.Entry<EmployeeInfo, List<Integer>> entry : availabilitySchedule.entrySet()) {
                EmployeeInfo employee = entry.getKey();
                if (employee.getInitials().equals(initials)) {
                    List<Integer> availability = entry.getValue();
                    StringBuilder availabilityString = new StringBuilder();
                    for (Integer integer : availability) {
                        availabilityString.append(integer);
                    }
                    assertEquals(expectedAvailability, availabilityString.toString());
                }
            }
        }
    }

}
