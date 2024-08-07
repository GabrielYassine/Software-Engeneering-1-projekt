package dtu.app.ui.domain;

/**
 * @Author Aland Ali Ahmad
 */

public class FixedActivity extends AbstractActivity {
    public FixedActivity(Employee employee, String name, int startWeek, int endWeek, int startYear, int endYear) {
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.startYear = startYear;
        this.endYear = endYear;
        employee.addFixedActivity(this);
    }

    @Override
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