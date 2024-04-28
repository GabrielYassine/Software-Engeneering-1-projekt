package dtu.app.ui.domain;


public class FixedActivity extends AbstractActivity {
    private final String name;

    private final Employee employee;

    public FixedActivity(Employee employee, String name, int startWeek, int endWeek, int startYear, int endYear) {
        this.employee = employee;
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startYear = startYear;
        this.endYear = endYear;
        employee.addFixedActivity(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getStartWeek() {
        return startWeek;
    }

    @Override
    public int getEndWeek() {
        return endWeek;
    }

    @Override
    public int getStartYear() {
        return startYear;
    }

    @Override
    public int getEndYear() {
        return endYear;
    }
}