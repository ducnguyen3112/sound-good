package com.starscream.soundgood.exceptions;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationException  extends RuntimeException {

    List<String> errors;

    public ValidationException(List<String> errorMessages) {
        super("Validation failed");
        this.errors = errorMessages;
    }

    public ValidationException(String message) {
        super(message);
    }


    public List<String> getErrorMessages() {
        return errors;
    }
}
