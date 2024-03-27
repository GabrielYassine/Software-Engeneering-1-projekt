package dtu.example.ui;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityLog {
    private Map<Calendar, Map<Activity, Integer>> dateLog;

    public ActivityLog() {
        this.dateLog = new HashMap<>();
    }

    public void addActivity(Calendar date, Activity activity, int hours) {
        Map<Activity, Integer> hoursLog = dateLog.getOrDefault(date, new HashMap<>());
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public Map<Activity, Integer> getActivityHours(Calendar date) {
        return dateLog.get(date);
    }

    public void editActivity(Calendar date, Activity activity, int hours) {
        Map<Activity, Integer> hoursLog = dateLog.get(date);
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public Map<Calendar, Map<Activity, Integer>> getDateLog() {
        return dateLog;
    }


    public void registerHours(Calendar date, Activity activity, int hours) throws Exception{
        if (date == null || activity == null || hours < 0) {
            throw new Exception("Insufficient or incorrect information given");
        }
        addActivity(date, activity, hours);
    }
}