package example.cucumber;

import dtu.app.ui.ProjectApp;
import io.cucumber.java.en.Given;

public class TimeSteps {

    MockDateHolder dateHolder;

    private final ProjectApp application;
    private final ErrorMessageHolder errorMessage;


    public TimeSteps(ProjectApp application, ErrorMessageHolder errorMessage, MockDateHolder dateHolder) {
        this.application = application;
        this.errorMessage = errorMessage;
        this.dateHolder = dateHolder;
    }

    @Given("{int} day has passed")
    public void dayHasPassed(int days) {
        dateHolder.advanceDateByDays(days);
    }
}
