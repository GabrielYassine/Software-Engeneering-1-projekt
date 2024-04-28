//package example.cucumber;
//
//import dtu.app.ui.ApplicationProjects;
//import dtu.app.ui.domain.Employee;
//import dtu.app.ui.errorMessageHolders.ErrorMessageHolder;
//import dtu.app.ui.info.EmployeeInfo;
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//
//public class AvailabilityScheduleSteps {
//
//    private final ApplicationProjects application;
//    private final ErrorMessageHolder errorMessage;
//    private Map<String, Map<Integer, Integer>> availabilitySchedule;
//
//    public AvailabilityScheduleSteps(ApplicationProjects application) {
//        this.application = application;
//        this.errorMessage = application.getErrorMessage();
//    }
//
//
//    @When("the user searches for the availability schedule for year {string} and month {string}")
//    public void theUserSearchesForTheAvailabilityScheduleForYearAndMonth(String year, String month) {
//        try {
//            this.availabilitySchedule = application.getAvailabilitySchedule(year, month);
//        } catch (Exception e) {
//            errorMessage.setErrorMessage(e.getMessage());
//        }
//    }
//
//    @Then("the system shows the following availability calendar for the specified month")
//    public void theSystemShowsTheFollowingAvailabilityCalendarForTheSpecifiedMonth(DataTable table) throws Exception {
//        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
//        for (Map<String, String> columns : rows) {
//            String initials = columns.get("Initials");
//            String firstWeek = columns.get("First Week");
//            String secondWeek = columns.get("Second Week");
//            String thirdWeek = columns.get("Third Week");
//            String fourthWeek = columns.get("Fourth Week");
//            String fifthWeek = columns.get("Fifth Week");
//
//            EmployeeInfo employee = application.getEmployee(initials);
//            Map<Integer, Integer> employeeAvailabilitySchedule = availabilitySchedule.get(employee.getInitials());
//
//            int firstKey = employeeAvailabilitySchedule.keySet().stream().findFirst().get();
//            int secondKey = employeeAvailabilitySchedule.keySet().stream().skip(1).findFirst().get();
//            int thirdKey = employeeAvailabilitySchedule.keySet().stream().skip(2).findFirst().get();
//            int fourthKey = employeeAvailabilitySchedule.keySet().stream().skip(3).findFirst().get();
//            int fifthKey = employeeAvailabilitySchedule.keySet().stream().skip(4).findFirst().orElse(0);
//
//            assertEquals(firstWeek, employeeAvailabilitySchedule.get(firstKey).toString());
//            assertEquals(secondWeek, employeeAvailabilitySchedule.get(secondKey).toString());
//            assertEquals(thirdWeek, employeeAvailabilitySchedule.get(thirdKey).toString());
//            assertEquals(fourthWeek, employeeAvailabilitySchedule.get(fourthKey).toString());
//
//            if (employeeAvailabilitySchedule.size() == 5) {
//                assertEquals(fifthWeek, employeeAvailabilitySchedule.get(fifthKey).toString());
//            } else {
//                assertEquals(fifthWeek, "0");
//            }
//        }
//    }
//}
