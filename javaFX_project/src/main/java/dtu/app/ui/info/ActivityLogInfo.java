package dtu.app.ui.info;

import dtu.app.ui.domain.Activity;
import dtu.app.ui.domain.ActivityLog;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

public class ActivityLogInfo {
    private final Map<LocalDate, Map<Activity, Double>> dateLog;
    private final List<LocalDate> registeredDates;

    public ActivityLogInfo(ActivityLog activityLog) {
        this.dateLog = activityLog.getDateLog();
        this.registeredDates = activityLog.getRegisteredDates();
    }

    public Map<LocalDate, Map<Activity, Double>> getDateLog() {
        return dateLog;
    }

    public ActivityLog getWeekActivities(int year, int week) {
        ActivityLog weekActivities = new ActivityLog();

        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        for (Map.Entry<LocalDate, Map<Activity, Double>> entry : dateLog.entrySet()) {
            LocalDate date = entry.getKey();
            if (date.getYear() == year && date.get(weekFields.weekOfWeekBasedYear()) == week) {
                for (Map.Entry<Activity, Double> activityEntry : entry.getValue().entrySet()) {
                    weekActivities.addActivity(date, activityEntry.getKey(), activityEntry.getValue());
                }
            }
        }
        return weekActivities;
    }

    public Map<Activity, Double> getDateActivities(LocalDate date) {
        return dateLog.get(date);
    }

    public Map<Activity, Double> getActivtyAndHours(Map<Activity, Double> allActivities, Activity activity) {
        Map<Activity, Double> activityHours = new HashMap<>();
        for (Map.Entry<Activity, Double> entry : allActivities.entrySet()) {
            if (entry.getKey().getName().equals(activity.getName())) {
                activityHours.put(entry.getKey(), entry.getValue());
            }
        }
        return activityHours;
    }

    public List<LocalDate> getRegisteredDates() {
        return registeredDates;
    }

    public boolean isEmpty() {
        return dateLog.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<LocalDate, Map<Activity, Double>> entry : dateLog.entrySet()) {
            sb.append("Date: ").append(entry.getKey()).append("\n");

            for (Map.Entry<Activity, Double> activityEntry : entry.getValue().entrySet()) {
                sb.append("Activity: ").append(activityEntry.getKey().getName()).append("\n")
                        .append("Hours: ").append(activityEntry.getValue());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}