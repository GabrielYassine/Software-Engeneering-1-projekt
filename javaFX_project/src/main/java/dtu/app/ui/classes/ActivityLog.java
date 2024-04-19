package dtu.app.ui.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

public class ActivityLog {
    private final Map<Calendar, Map<Activity, Integer>> dateLog;
    private final List<Calendar> registeredDates;

    public ActivityLog() {
        this.dateLog = new HashMap<>();
        this.registeredDates = new ArrayList<>();
    }

    public void addActivity(Calendar date, Activity activity, int hours) {
        Map<Activity, Integer> hoursLog = dateLog.getOrDefault(date, new HashMap<>());
        if (hoursLog.containsKey(activity)) {
            int existingHours = hoursLog.get(activity);
            hoursLog.put(activity, existingHours + hours);
        } else {
            hoursLog.put(activity, hours);
        }
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
        hasRegistered(date);
    }

    public void hasRegistered(Calendar date) {
        registeredDates.add(date);
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

    public ActivityLog getWeekActivities(String year, String week) {
        int yearInt = Integer.parseInt(year);
        int weekInt = Integer.parseInt(week);

        ActivityLog weekActivities = new ActivityLog();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        for (Map.Entry<Calendar, Map<Activity, Integer>> entry : dateLog.entrySet()) {

            LocalDate date = entry.getKey().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.getYear() == yearInt && date.get(weekFields.weekOfWeekBasedYear()) == weekInt) {

                for (Map.Entry<Activity, Integer> activityEntry : entry.getValue().entrySet()) {
                    weekActivities.addActivity(entry.getKey(), activityEntry.getKey(), activityEntry.getValue());
                }
            }
        }
        return weekActivities;
    }

    public List<String> getMondayActivities() {
        return getDayActivities(Calendar.MONDAY);
    }

    public List<String> getTuesdayActivities() {
        return getDayActivities(Calendar.TUESDAY);
    }

    public List<String> getWednesdayActivities() {
        return getDayActivities(Calendar.WEDNESDAY);
    }

    public List<String> getThursdayActivities() {
        return getDayActivities(Calendar.THURSDAY);
    }

    public List<String> getFridayActivities() {
        return getDayActivities(Calendar.FRIDAY);
    }

    public List<String> getSaturdayActivities() {
        return getDayActivities(Calendar.SATURDAY);
    }

    public List<String> getSundayActivities() {
        return getDayActivities(Calendar.SUNDAY);
    }

    public List<String> getDayActivities(int dayOfWeek) {
        List<String> activities = new ArrayList<>();
        for (Map.Entry<Calendar, Map<Activity, Integer>> entry : dateLog.entrySet()) {
            if (entry.getKey().get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
                for (Map.Entry<Activity, Integer> activityEntry : entry.getValue().entrySet()) {
                    String activityInfo = activityEntry.getKey().getName() + "-" +
                            activityEntry.getValue();
                    activities.add(activityInfo);
                }
            }
        }
        return activities;
    }

    public Map<Activity, Integer> getDateActivities(Calendar date) {
        return dateLog.get(date);
    }

    public Map<Calendar, Map<Activity, Integer>> getDateLog() {
        return dateLog;
    }

    public List<Calendar> getRegisteredDates() {
        return registeredDates;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<Calendar, Map<Activity, Integer>> entry : dateLog.entrySet()) {
            sb.append("Date: ").append(sdf.format(entry.getKey().getTime())).append("\n");

            for (Map.Entry<Activity, Integer> activityEntry : entry.getValue().entrySet()) {
                sb.append("Activity: ").append(activityEntry.getKey().getName()).append("\n")
                        .append("Hours: ").append(activityEntry.getValue()).append("\n");
            }
            sb.append("\n");
        }
        System.out.print("Hello");
        return sb.toString();
    }

    public int getSize() {
        return dateLog.size();
    }
}