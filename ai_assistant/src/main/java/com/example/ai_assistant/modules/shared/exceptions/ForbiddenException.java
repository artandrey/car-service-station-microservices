package com.example.ai_assistant.modules.shared.exceptions;

public abstract class ForbiddenException extends AppException {
    private final static int statusCode = 403;

    public ForbiddenException(String errorCode, String message) {
        super(errorCode, message, statusCode, null);
    }
}
