package dtu.app.ui.classes;

public class EmailServer {
    public void sendEmail(String initials, String subject, String text) {
        System.out.format("Email recieved: %s\n%s\n%s\n", initials, subject, text);
    }
}
