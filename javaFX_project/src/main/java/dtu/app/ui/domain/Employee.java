package dtu.app.ui.domain;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

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

    public boolean hasRegisteredDailyWork(LocalDate currentDate) {
        List<LocalDate> registeredDates = activityLog.getRegisteredDates();
        return registeredDates.contains(currentDate);
    }

    public boolean isDoingFixedActivity(int currentWeek, int currentYear) {
        return getFixedActivities().stream().anyMatch(fa -> fa.getStartYear() <= currentYear && fa.getEndYear() >= currentYear && fa.getStartWeek() <= currentWeek && fa.getEndWeek() >= currentWeek);
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

    public void addEmail(Email email) {
        inbox.add(email);
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

    public List<FixedActivity> getFixedActivities() {
        return fixedActivities;
    }

    public String getInitials() {
        return initials;
    }

    public List<Email> getInbox() {
        return inbox;
    }

}

