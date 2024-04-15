package example.cucumber;

import dtu.app.ui.classes.Database;
import dtu.app.ui.classes.EmailServer;

import static org.mockito.Mockito.mock;

public class MockEmailServer {
    private EmailServer emailServer = mock(EmailServer.class);

    public MockEmailServer(Database database) {
        database.setEmailServer(emailServer);
    }

    public EmailServer getMockEmailServer() {
        return emailServer;
    }
}
