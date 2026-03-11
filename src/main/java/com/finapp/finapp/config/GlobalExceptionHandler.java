package com.finapp.finapp.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<AppError> handle(ResponseStatusException e) {
        return new ResponseEntity<>(new AppError(e.getStatusCode().value(), e.getReason()), e.getStatusCode());
    }
}