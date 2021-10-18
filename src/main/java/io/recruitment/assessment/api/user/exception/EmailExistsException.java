package io.recruitment.assessment.api.user.exception;

public class EmailExistsException extends Exception {

    public EmailExistsException(String msg) {
        super(msg);
    }
}
