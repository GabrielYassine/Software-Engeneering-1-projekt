package dtu.example.ui;

public class Employee {

    private final App app;
    private final String initials;
    private final ActivityLog activityLog;

    public Employee(App app, String initials) {
        if (app == null) {
            throw new IllegalArgumentException("App cannot be null");
        }
        if (initials == null || initials.isEmpty()) {
            throw new IllegalArgumentException("Initials cannot be null or empty");
        }
        this.app = app;
        this.initials = initials;
        this.activityLog = new ActivityLog();
        app.appendEmployee(this);
    }

    public String getInitials() {
        return initials;
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }
}