package dtu.app.ui.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}