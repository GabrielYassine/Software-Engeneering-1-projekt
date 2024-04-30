package dtu.app.ui.domain;

import java.time.YearMonth;
import java.time.temporal.IsoFields;
import java.util.*;
import java.util.stream.Stream;

public class Employee {

    private final String initials;
    private final ActivityLog activityLog;
    private final List<Activity> activities = new ArrayList<>();
    private final List<FixedActivity> fixedActivities = new ArrayList<>();
    private final List<Email> inbox = new ArrayList<>();

    public Employee(Database database, String initials) {
        this.initials = initials;
        this.activityLog = new ActivityLog();
        database.appendEmployee(this);
    }

    public void sendEmailNotification(String subject, String text) {
        inbox.add(new Email(subject, text));
    }

    public boolean hasRegistered(Calendar date) {
        return activityLog.getRegisteredDates().contains(date);
    }

    public void addActivity(Activity a) {
        activities.add(a);
    }

    public void addFixedActivity(FixedActivity a) {
        fixedActivities.add(a);
    }

    public boolean isActivityActive(Activity activity, int selectedWeek, int year) {
        int startYear = activity.getStartYear();
        int endYear = activity.getEndYear();
        int startWeek = activity.getStartWeek();
        int endWeek = activity.getEndWeek();

        if (year < startYear || year > endYear) {
            return false;
        }
        if (year == startYear && selectedWeek < startWeek) {
            return false;
        }
        if (year == endYear && selectedWeek > endWeek) {
            return false;
        }
        return true;
    }

    public int getActiveActivityCount(int year, int month, int weekOfMonth) {
        int count = 0;
        int weekOfYear = YearMonth.of(year, month).atDay(1).plusWeeks(weekOfMonth - 1).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        for (Activity activity : activities) {
            if (isActivityActive(activity, weekOfYear, year)) {
                count++;
            }
        }
        return count;
    }

    public ActivityLog getActivityLog() {
        return activityLog;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<FixedActivity> getFixedActivities() {
        return fixedActivities;
    }


    public String getInitials() {
        return initials;
    }

    public Stream<Email> getInboxStream() {
        return inbox.stream();
    }

    public List<Email> getInbox() {
        return inbox;
    }

}

