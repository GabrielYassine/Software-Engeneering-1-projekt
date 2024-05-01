package dtu.app.ui.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class DateServer {
    public Calendar getDate() {
        Calendar calendar = new GregorianCalendar();
        return new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
    }

    public int getWeek() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public int getYear() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.YEAR);
    }
}
