package dtu.app.ui.domain;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateServer {
    public LocalDate getDate() {
        return LocalDate.now();
    }

    public int getWeek() {
        return LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }

    public int getYear() {
        return LocalDate.now().getYear();
    }
}