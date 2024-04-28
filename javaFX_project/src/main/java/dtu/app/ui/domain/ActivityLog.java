package dtu.app.ui.domain;

import java.time.LocalDate;
import java.util.*;

public class ActivityLog {
    private final Map<LocalDate, Map<Activity, Double>> dateLog;
    private final List<LocalDate> registeredDates;

    public ActivityLog() {
        this.dateLog = new HashMap<>();
        this.registeredDates = new ArrayList<>();
    }

    public void addActivity(LocalDate date, Activity activity, double hours) {
        Map<Activity, Double> hoursLog = dateLog.getOrDefault(date, new HashMap<>());
        if (hoursLog.containsKey(activity)) {
            double existingHours = hoursLog.get(activity);
            hoursLog.put(activity, existingHours + hours);
        } else {
            hoursLog.put(activity, hours);
        }
        dateLog.put(date, hoursLog);
    }

    public void editActivity(LocalDate date, Activity activity, double hours) {
        Map<Activity, Double> hoursLog = dateLog.get(date);
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public void registerHours(LocalDate date, Activity activity, double hours) throws Exception {
        addActivity(date, activity, hours);
        hasRegistered(date);
    }

    public void hasRegistered(LocalDate date) {
        registeredDates.add(date);
    }

    public Map<LocalDate, Map<Activity, Double>> getDateLog() {
        return dateLog;
    }

    public List<LocalDate> getRegisteredDates() {
        return registeredDates;
    }

    public int getSize() {
        return dateLog.size();
    }

    public Map<Activity, Double> getDateActivities(LocalDate date) {
        return dateLog.get(date);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<LocalDate, Map<Activity, Double>> entry : dateLog.entrySet()) {
            sb.append("Date: ").append(entry.getKey()).append("\n");

            for (Map.Entry<Activity, Double> activityEntry : entry.getValue().entrySet()) {
                sb.append("Activity: ").append(activityEntry.getKey().getName()).append("\n")
                        .append("Hours: ").append(activityEntry.getValue()).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}