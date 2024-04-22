package dtu.app.ui.classes;

import java.time.YearMonth;
import java.util.*;
import java.util.stream.Stream;

public class Employee {

    private final String initials;
    private final ActivityLog activityLog;
    private final List<Activity> activities = new ArrayList<>();
    private final List<Email> inbox = new ArrayList<>();

    public Employee(Database database, String initials) {
        if (initials == null || initials.isEmpty()) {
            throw new IllegalArgumentException("Initials cannot be null or empty");
        }
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

    public String getInitials() {
        return initials;
    }

    public Stream<Email> getInboxStream() {
        return inbox.stream();
    }

    public List<Email> getInbox() {
        return inbox;
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

    public Map<Integer, Integer> getAvailability(String year, String month) {
        Map<Integer, Integer> availabilityForMonth = new HashMap<>();

        // Convert year and month to integers
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);

        // Subtract 1 from monthInt if it's in the range 1-12 (January-December)
        if (monthInt >= 1 && monthInt <= 12) {
            monthInt -= 1;
        }

        // Get the number of weeks in the specified month and year
        YearMonth yearMonthObject = YearMonth.of(yearInt, monthInt + 1); // Add 1 to monthInt because YearMonth uses 1-based months
        int daysInMonth = yearMonthObject.lengthOfMonth();

        // Initialize the calendar to the start of the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(yearInt, monthInt, 1);

        // Iterate over each day of the month
        for (int day = 1; day <= daysInMonth; day++) {
            // Set the calendar to the current day
            calendar.set(Calendar.DAY_OF_MONTH, day);

            // Get the week of the year for the current day
            int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

            // Get the activities for the current day
            List<Activity> activitiesForDay = activityLog.getActivitiesForDay(calendar.getTime());

            // If there are any activities for the current day, increment the count for the current week
            if (activitiesForDay != null && !activitiesForDay.isEmpty()) {
                availabilityForMonth.put(weekOfYear, availabilityForMonth.getOrDefault(weekOfYear, 0) + 1);
            }
        }

        return availabilityForMonth;
    }
}

