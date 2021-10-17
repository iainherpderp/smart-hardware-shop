package io.recruitment.assessment.api.exception.handler;

import io.recruitment.assessment.api.cart.exception.EmptyCartException;
import io.recruitment.assessment.api.exception.UnauthorisedAccessException;
import io.recruitment.assessment.api.user.exception.EmailExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<String> handleEmailExistsException(EmailExistsException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(UnauthorisedAccessException.class)
    public ResponseEntity<String> handleUnauthorisedAccessException(UnauthorisedAccessException e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<String> handleEmptyCartException(EmptyCartException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
