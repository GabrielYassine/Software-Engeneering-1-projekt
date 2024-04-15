package example.cucumber;

import dtu.app.ui.classes.Database;
import dtu.app.ui.classes.DateServer;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockDateServer {
    DateServer dateServer = mock(DateServer.class);

    public MockDateServer(Database database) {
        GregorianCalendar calendar = new GregorianCalendar();
        setDate(calendar);
        database.setDateServer(dateServer);
    }

    public void setDate(Calendar calendar) {
        Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        when(this.dateServer.getDate()).thenReturn(c);
    }
}
