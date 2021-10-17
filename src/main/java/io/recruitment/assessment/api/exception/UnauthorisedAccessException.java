package io.recruitment.assessment.api.exception;

public class UnauthorisedAccessException extends Exception {

    public UnauthorisedAccessException(String msg) {
        super(msg);
    }
}
