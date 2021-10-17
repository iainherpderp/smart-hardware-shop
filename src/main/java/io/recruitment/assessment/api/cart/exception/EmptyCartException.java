package io.recruitment.assessment.api.cart.exception;

public class EmptyCartException extends RuntimeException {

    public EmptyCartException(String msg) {
        super(msg);
    }
}
