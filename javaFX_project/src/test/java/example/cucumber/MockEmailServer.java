package example.cucumber;

import dtu.app.ui.classes.Database;
import dtu.app.ui.classes.Email;

import static org.mockito.Mockito.mock;

public class MockEmailServer {
    private Email email = mock(Email.class);

    public MockEmailServer(Database database) {
        database.setEmailServer(email);
    }

    public Email getMockEmailServer() {
        return email;
    }
}
