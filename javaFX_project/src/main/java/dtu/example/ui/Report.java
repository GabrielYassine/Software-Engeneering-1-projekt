package dtu.example.ui;

import java.util.Date;
import java.util.Map;

public class Report {
    private int id;
    private String title;
    private String description;
    private Date dateCreated;
    private Map<String, Object> data; // Map to store report data (e.g., key-value pairs)

    // Constructor
    public Report(int id, String title, String description, Date dateCreated, Map<String, Object> data) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.data = data;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    // Methods
    // Add any additional methods as needed

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", data=" + data +
                '}';
    }
}

