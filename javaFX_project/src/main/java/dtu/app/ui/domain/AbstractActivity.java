package dtu.app.ui.domain;

public abstract class AbstractActivity {
    protected String name;
    protected int startWeek;
    protected int endWeek;
    protected int startYear;
    protected int endYear;

    public abstract String getName();
    public abstract int getStartWeek();
    public abstract int getEndWeek();
    public abstract int getStartYear();
    public abstract int getEndYear();
}