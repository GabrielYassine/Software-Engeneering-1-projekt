package dtu.app.ui.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.*;

public class ActivityLog {
    private final Map<Calendar, Map<Activity, Double>> dateLog;
    private final List<Calendar> registeredDates;

    public ActivityLog() {
        this.dateLog = new HashMap<>();
        this.registeredDates = new ArrayList<>();
    }

    public void addActivity(Calendar date, Activity activity, double hours) {
        Map<Activity, Double> hoursLog = dateLog.getOrDefault(date, new HashMap<>());
        if (hoursLog.containsKey(activity)) {
            double existingHours = hoursLog.get(activity);
            hoursLog.put(activity, existingHours + hours);
        } else {
            hoursLog.put(activity, hours);
        }
        dateLog.put(date, hoursLog);
    }

    public void editActivity(Calendar date, Activity activity, double hours) {
        Map<Activity, Double> hoursLog = dateLog.get(date);
        hoursLog.put(activity, hours);
        dateLog.put(date, hoursLog);
    }

    public void registerHours(Calendar date, Activity activity, String hours) throws Exception {
        double hoursDouble = parseAndValidateHours(hours);
        if (date == null || activity == null) {
            throw new Exception("Insufficient or incorrect information given");
        }
        addActivity(date, activity, hoursDouble);
        hasRegistered(date);
    }

    public void hasRegistered(Calendar date) {
        registeredDates.add(date);
    }

    private double parseAndValidateHours(String registeredHours) {
        try {
            double hours = Double.parseDouble(registeredHours);
            if (hours == 0) {
                throw new IllegalArgumentException("Hours missing");
            }
            return hours;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Registered hours value error");
        }
    }
    private int parseAndValidateWeek(String week) {
        if (week == null || week.isEmpty()) {
            throw new IllegalArgumentException("No week is given");
        }
        try {
            int weekNumber = Integer.parseInt(week);
            if (weekNumber == 0 || weekNumber > 52) {
                throw new IllegalArgumentException("Week value error");
            }
            return weekNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Week value error");
        }
    }
    private void parseAndValidateYear(String year) {
        if (year == null || year.isEmpty()) {
            throw new IllegalArgumentException("No year is given");
        }
    }


    public ActivityLog getWeekActivities(String year, String week) {
        parseAndValidateYear(year);
        int yearInt = Integer.parseInt(year);
        int weekInt = parseAndValidateWeek(week);

        ActivityLog weekActivities = new ActivityLog();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        for (Map.Entry<Calendar, Map<Activity, Double>> entry : dateLog.entrySet()) {

            LocalDate date = entry.getKey().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.getYear() == yearInt && date.get(weekFields.weekOfWeekBasedYear()) == weekInt) {

                for (Map.Entry<Activity, Double> activityEntry : entry.getValue().entrySet()) {
                    weekActivities.addActivity(entry.getKey(), activityEntry.getKey(), activityEntry.getValue());
                }
            }
        }
        return weekActivities;
    }

    public List<String> getMondayActivities(int weekOfYear) {
        return getDayActivities(weekOfYear, Calendar.MONDAY);
    }

    public List<String> getTuesdayActivities(int weekOfYear) {
        return getDayActivities(weekOfYear, Calendar.TUESDAY);
    }

    public List<String> getWednesdayActivities(int weekOfYear) {
        return getDayActivities(weekOfYear, Calendar.WEDNESDAY);
    }

    public List<String> getThursdayActivities(int weekOfYear) {
        return getDayActivities(weekOfYear, Calendar.THURSDAY);
    }

    public List<String> getFridayActivities(int weekOfYear) {
        return getDayActivities(weekOfYear, Calendar.FRIDAY);
    }

    public List<String> getSaturdayActivities(int weekOfYear) {
        return getDayActivities(weekOfYear, Calendar.SATURDAY);
    }

    public List<String> getSundayActivities(int weekOfYear) {
        return getDayActivities(weekOfYear, Calendar.SUNDAY);
    }

    public List<String> getDayActivities(int weekOfYear, int dayOfWeek) {
        parseAndValidateWeek(String.valueOf(weekOfYear));
        List<String> activities = new ArrayList<>();
        for (Map.Entry<Calendar, Map<Activity, Double>> entry : dateLog.entrySet()) {
            if (entry.getKey().get(Calendar.DAY_OF_WEEK) == dayOfWeek && entry.getKey().get(Calendar.WEEK_OF_YEAR) == weekOfYear) {
                for (Map.Entry<Activity, Double> activityEntry : entry.getValue().entrySet()) {
                    String activityInfo = activityEntry.getKey().getName() + "-" +
                            activityEntry.getValue();
                    activities.add(activityInfo);
                }
            }
        }

        return activities;
    }

    public Map<Activity, Double> getDateActivities(Calendar date) {
        return dateLog.get(date);
    }

    public Map<Calendar, Map<Activity, Double>> getDateLog() {
        return dateLog;
    }

    public List<Calendar> getRegisteredDates() {
        return registeredDates;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Map.Entry<Calendar, Map<Activity, Double>> entry : dateLog.entrySet()) {
            sb.append("Date: ").append(sdf.format(entry.getKey().getTime())).append("\n");

            for (Map.Entry<Activity, Double> activityEntry : entry.getValue().entrySet()) {
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