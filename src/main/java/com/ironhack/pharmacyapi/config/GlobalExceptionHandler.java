package com.ironhack.pharmacyapi.config;

import com.ironhack.pharmacyapi.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationExceptions(final MethodArgumentNotValidException ex,
                                                        final WebRequest request) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    var fieldName = error.getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintViolationException(
            final ConstraintViolationException ex,
            final WebRequest request) {
        var errors = new HashMap<String, String>();
        var cv = ex.getConstraintViolations();
        errors.put("message", cv.iterator().next().getMessage()); // output only first violation
        errors.put("request", request.getDescription(false));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({EntityNotFoundException.class, ResourceNotFoundException.class})
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleEntityNotFoundException(final EntityNotFoundException ex,
                   final ResourceNotFoundException reEx , final WebRequest request) {
        var errors = new HashMap<String, String>();
        errors.put("message", ex.getMessage());
        errors.put("msg", reEx.getMessage());
        errors.put("request", request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
