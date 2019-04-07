package io.team21.calendarservice.exceptions;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<FieldError> errors;

    BadRequestException(Errors fieldErrors) {
        this.errors = fieldErrors.getFieldErrors();
    }

    public List<FieldError> getErrors() {
        return this.errors;
    }
}
