package com.example.knowledge_base.shared.exceptions;

import lombok.Getter;

@Getter
public abstract class AppException extends RuntimeException {

    private String errorCode;
    private int statusCode;
    private Exception exception;

    public AppException(String errorCode, String message, int statusCode, Exception exception) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.exception = exception;
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

}
