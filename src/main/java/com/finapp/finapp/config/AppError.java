package com.finapp.finapp.config;

import lombok.Data;

@Data
public class AppError {
    private int statusCode;
    private String message;


    public AppError() {
    }

    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
