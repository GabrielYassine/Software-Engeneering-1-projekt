package dtu.app.ui.domain;

import java.time.LocalDate;

public class Email {
    private final String subject;
    private final String text;
    private final LocalDate localDate;

    public Email(String subject, String text, LocalDate localDate) {
        this.subject = subject;
        this.text = text;
        this.localDate = localDate;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public LocalDate getEmailDate() {
        return localDate;
    }
}
