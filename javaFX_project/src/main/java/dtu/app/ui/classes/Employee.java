package dtu.app.ui.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Employee {

    private final String initials;
    private final ActivityLog activityLog;

    private List<Activity> activities = new ArrayList<>();

    public Employee(Database database, String initials) {
        if (initials == null || initials.isEmpty()) {
            throw new IllegalArgumentException("Initials cannot be null or empty");
        }
        this.initials = initials;
        this.activityLog = new ActivityLog();
        database.appendEmployee(this);
    }

    public void sendEmailNotification(Email email, String text) {
        email.sendEmail(initials, "Work", text);
    }

    public boolean hasRegistered(Calendar date) {
        return activityLog.getRegisteredDates().contains(date);
    }

    public String getInitials() {
        return initials;
    }



    public boolean isActivityActive(Activity activity, int selectedWeek) {
        // Note: Grunden til at noget af det her er lidt mærkeligt, er fordi jeg prøver at tage hensyn til at en aktivitet der starter uge 52 og slutter uge 7 skal tages hensyn til.
        int endWeekTemp = activity.getEndWeek();
        endWeekTemp = activity.getStartWeek() > endWeekTemp ? endWeekTemp + 52 : endWeekTemp;

        return activity.getStartWeek() <= selectedWeek && endWeekTemp >= selectedWeek;
    }

    public void updateActivityCount(int n) {
        // activityCount += n; // FIX DET HER
    }

    public int getActivityCount() {
        return 0; // FIX DET HER
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }

    @Override
    public String toString() {
        return initials;
    }
}