package dtu.app.ui.info;

import dtu.app.ui.domain.FixedActivity;

/**
 * @Author Aland Ali Ahmad
 */
public class FixedActivityInfo {
    private final String name;
    private final int startWeek;
    private final int endWeek;
    private final int startYear;
    private final int endYear;

    public FixedActivityInfo(FixedActivity fixedActivity) {
        this.name = fixedActivity.getName();
        this.startWeek = fixedActivity.getStartWeek();
        this.endWeek = fixedActivity.getEndWeek();
        this.startYear = fixedActivity.getStartYear();
        this.endYear = fixedActivity.getEndYear();
    }

    public String getName() {
        return name;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

}