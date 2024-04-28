package dtu.app.ui.domain;

public class Email {
    private String subject;
    private String text;

    public Email(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
