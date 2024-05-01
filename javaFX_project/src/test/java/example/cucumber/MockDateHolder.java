package example.cucumber;

import dtu.app.ui.ProjectApp;
import dtu.app.ui.domain.DateServer;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockDateHolder {
    DateServer dateServer = mock(DateServer.class);

    public MockDateHolder(ProjectApp projectApp) {
        LocalDate calendar = LocalDate.now();
        setDate(calendar);
        projectApp.setDateServer(dateServer);
    }

    public void setDate(LocalDate calendar) {
        LocalDate c = LocalDate.of(calendar.getYear(),calendar.getMonth(),calendar.getDayOfMonth());
        when(this.dateServer.getDate()).thenReturn(c);
    }

    public void advanceDateByDays(int days) {
        LocalDate currentDate = dateServer.getDate();
        LocalDate nextDate = currentDate.plusDays(days);
        setDate(nextDate);
    }
}
