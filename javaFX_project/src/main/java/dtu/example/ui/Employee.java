package dtu.example.ui;

public class Employee {

    private final App app;

    private String initials;

    public Employee(App app, String initials) {
        this.app = app;
        this.initials = initials;
        app.appendEmployee(this);
    }

    public String getInitials() {
        return initials;
    }
}

