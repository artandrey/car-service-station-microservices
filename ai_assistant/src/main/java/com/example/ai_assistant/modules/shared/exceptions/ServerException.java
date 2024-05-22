package com.example.ai_assistant.modules.shared.exceptions;

public class ServerException extends AppException {
    private final static int statusCode = 500;

    public ServerException(String errorCode, String message, Exception exception) {
        super(errorCode, message, statusCode, exception);
    }

}
