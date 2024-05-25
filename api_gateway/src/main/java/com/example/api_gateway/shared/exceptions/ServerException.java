package com.example.api_gateway.shared.exceptions;

public abstract class ServerException extends AppException {

    private static final int statusCode = 500;

    public ServerException(String errorCode, String message) {
        super(errorCode, message, statusCode);
    }

}
