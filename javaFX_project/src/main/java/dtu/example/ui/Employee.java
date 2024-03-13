package dtu.example.ui;

import java.util.HashMap;
import java.util.Map;

public class Employee {
    private int id;
    private String name;
    private Map<Integer, Double> dailyTimeSheets; // Map<date, hours> We can use a different alternative class

    // Constructor
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.dailyTimeSheets = new HashMap<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                "id=" + id +
                ", name='" + name + '\'' +
                ", dailyTimeSheets=" + dailyTimeSheets +
                '}';
    }
}

