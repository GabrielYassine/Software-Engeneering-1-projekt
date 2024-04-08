package dtu.app.ui.classes;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityLog {
    private final Map<Calendar, Map<Activity, Integer>> dateLog;

    public ActivityLog() {
        this.dateLog = new HashMap<>();
    }

    public void addActivity(Calendar date, Activity activity, int hours) {
        Map<Activity, Integer> hoursLog = dateLog.getOrDefault(date, new HashMap<>());
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public void editActivity(Calendar date, Activity activity, int hours) {
        Map<Activity, Integer> hoursLog = dateLog.get(date);
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public void registerHours(Calendar date, Activity activity, String hours) throws Exception {
        int hoursInt = parseAndValidateHours(hours);
        if (date == null || activity == null) {
            throw new Exception("Insufficient or incorrect information given");
        }
        addActivity(date, activity, hoursInt);
    }

    private int parseAndValidateHours(String registeredHours) {
        try {
            int hours = Integer.parseInt(registeredHours);
            if (hours == 0) {
                throw new IllegalArgumentException("Hours missing");
            }
            return hours;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Registered hours value error");
        }
    }

    public Map<Activity, Integer> getDateActivities(Calendar date) {
        return dateLog.get(date);
    }

    public Map<Calendar, Map<Activity, Integer>> getDateLog() {
        return dateLog;
    }
}