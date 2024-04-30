package example.cucumber;

import io.cucumber.java.en.Given;

public class TimeSteps {

    MockDateHolder dateHolder;

    public TimeSteps(MockDateHolder dateHolder) {
        this.dateHolder = dateHolder;
    }

    @Given("{int} day has passed")
    public void dayHasPassed(int days) {
        dateHolder.advanceDateByDays(days);
    }
}
