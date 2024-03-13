package dtu.example.ui;

import java.util.HashMap;
import java.util.Map;

public class Employee {
    private String name;
    private Map<Integer, Double> dailyTimeSheets; // Map<date, hours> We can use a different alternative class

    // Constructor
    public Employee(String name) {
        this.name = name;
        this.dailyTimeSheets = new HashMap<>();
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods
    public void registerTime(int date, double hours) {
        dailyTimeSheets.put(date, hours);
    }

    public Map<Integer, Double> getDailyTimeSheets() {
        return dailyTimeSheets;
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", dailyTimeSheets=" + dailyTimeSheets +
                '}';
    }
}

