package dtu.app.ui.classes;

public class Employee {

    private final String initials;
    private int activityCount;
    private final ActivityLog activityLog;

    public Employee(Database database, String initials) {
        if (initials == null || initials.isEmpty()) {
            throw new IllegalArgumentException("Initials cannot be null or empty");
        }
        this.initials = initials;
        this.activityLog = new ActivityLog();
        this.activityCount = 0;
        database.appendEmployee(this);
    }
    public String getInitials() {
        return initials;
    }

    public int getActivityCount() {
        return activityCount;
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }

    @Override
    public String toString() {
        return initials;
    }
}