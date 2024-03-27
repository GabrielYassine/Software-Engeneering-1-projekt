package dtu.example.ui;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityLog {
    private Map<Calendar, Map<String, Integer>> dateLog;

    public ActivityLog() {
        this.dateLog = new HashMap<>();
    }

    public void addActivity(Calendar date, String activity, int hours) {
        Map<String, Integer> hoursLog = dateLog.getOrDefault(date, new HashMap<>());
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public Map<String, Integer> getActivityHours(Calendar date) {
        return dateLog.get(date);
    }

    public void editActivity(Calendar date, String activity, int hours) {
        Map<String, Integer> hoursLog = dateLog.get(date);
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public Map<Calendar, Map<String, Integer>> getDateLog() {
        return dateLog;
    }
}