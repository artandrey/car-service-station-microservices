package com.example.api_gateway.shared.exceptions;

import lombok.Getter;

@Getter
public abstract class AppException extends RuntimeException {

    private String errorCode;
    private int statusCode;

    public AppException(String errorCode, String message, int statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}
