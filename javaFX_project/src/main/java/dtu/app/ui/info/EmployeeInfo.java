package dtu.app.ui.info;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.Employee;
import dtu.app.ui.domain.ActivityLog;
import dtu.app.ui.domain.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EmployeeInfo {
    private final String initials;
    private final List<Email> inbox;

    public EmployeeInfo(Employee employee) {
        this.initials = employee.getInitials();
        this.inbox = new ArrayList<>(employee.getInbox());
    }

    public String getInitials() {
        return initials;
    }

    public List<Email> getInbox() {
        return new ArrayList<>(inbox);
    }

    //////////////////////////// GETTERS FOR GUI (And compare elements for GUI)  ////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInfo that = (EmployeeInfo) o;
        return Objects.equals(initials, that.initials);
    }
}