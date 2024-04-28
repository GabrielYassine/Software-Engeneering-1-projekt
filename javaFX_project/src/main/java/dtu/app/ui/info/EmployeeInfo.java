package dtu.app.ui.info;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.ActivityLog;
import dtu.app.ui.domain.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeInfo {
    private final String initials;
    private final List<Activity> activities;
    private final List<Email> inbox;

    public EmployeeInfo(Employee employee) {
        this.initials = employee.getInitials();
        this.activities = new ArrayList<>(employee.getActivities());
        this.inbox = new ArrayList<>(employee.getInbox());
    }

    public String getInitials() {
        return initials;
    }

    public List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }
    public List<Email> getInbox() {
        return new ArrayList<>(inbox);
    }

    public String toString() {
        return initials;
    }
}