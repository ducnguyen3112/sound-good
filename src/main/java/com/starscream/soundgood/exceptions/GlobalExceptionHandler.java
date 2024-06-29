package com.starscream.soundgood.exceptions;

import com.starscream.soundgood.dtos.reponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException (ValidationException ex) {
        return new ResponseEntity<>(ApiResponse.responseMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<?> handleValidationException (UnexpectedException ex) {
        return new ResponseEntity<>(ApiResponse.responseMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
