package dtu.app.ui.domain;

import java.time.LocalDate;

public class Email {
    private final String subject;
    private final String text;
    private final String date;

    public Email(String subject, String text, String date) {
        this.subject = subject;
        this.text = text;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
