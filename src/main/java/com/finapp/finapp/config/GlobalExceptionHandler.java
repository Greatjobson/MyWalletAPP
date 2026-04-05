package com.finapp.finapp.config;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<AppError> handle(ResponseStatusException e) {
        String message = e.getReason() != null ? e.getReason() : "Unexpected error";

        return ResponseEntity
                .status(e.getStatusCode())
                .body(new AppError(
                        e.getStatusCode().value(),
                        message
                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AppError> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()
                .body(new AppError(
                        400,
                        ex.getMessage()
                ));
    }
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<AppError> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity
                .badRequest()
                .body(new AppError(400, ex.getMessage()));
    }
}

// потом как нить изучим это дело
// @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<?> handleInvalidEnum(HttpMessageNotReadableException ex) {
//
//        return ResponseEntity
//                .badRequest()
//                .body(Map.of(
//                        "error", "Invalid request",
//                        "message", "Ты ввёл некорректное значение (например, валюта должна быть: KZT или USD)"
//                ));
//    }