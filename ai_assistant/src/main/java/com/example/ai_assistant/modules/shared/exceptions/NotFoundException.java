package com.example.ai_assistant.modules.shared.exceptions;

public class NotFoundException extends AppException {
    private final static int statusCode = 404;

    public NotFoundException(String errorCode, String message) {
        super(errorCode, message, statusCode, null);
    }
}
