package dtu.app.ui.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;

public class Employee {

    private final String initials;
    private final ActivityLog activityLog;
    private List<Activity> activities = new ArrayList<>();
    private List<Email> inbox = new ArrayList<>();

    public Employee(Database database, String initials) {
        if (initials == null || initials.isEmpty()) {
            throw new IllegalArgumentException("Initials cannot be null or empty");
        }
        this.initials = initials;
        this.activityLog = new ActivityLog();
        database.appendEmployee(this);
    }

    public void sendEmailNotification(String subject, String text) {
        Email email = new Email(subject, text);
        inbox.add(email);
    }

    public boolean hasRegistered(Calendar date) {
        return activityLog.getRegisteredDates().contains(date);
    }

    public String getInitials() {
        return initials;
    }

    public Stream<Email> getInboxStream() {
        return inbox.stream();
    }

    public boolean isActivityActive(Activity activity, int selectedWeek) {
        int endWeekTemp = activity.getEndWeek();
        endWeekTemp = activity.getStartWeek() > endWeekTemp ? endWeekTemp + 52 : endWeekTemp;

        return activity.getStartWeek() <= selectedWeek && endWeekTemp >= selectedWeek;
    }

    public int getActiveActivityCount(int selectedWeek) {
        int count = 0;
        for (Activity a : activities) {
            if (isActivityActive(a, selectedWeek)) {
                count++;
            }
        }
        return count;
    }

    public void addActivity(Activity a) {
        activities.add(a);
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }

    @Override
    public String toString() {
        return initials;
    }
}