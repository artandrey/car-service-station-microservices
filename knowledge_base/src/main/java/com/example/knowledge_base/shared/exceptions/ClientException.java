package com.example.knowledge_base.shared.exceptions;

public abstract class ClientException extends AppException {

    private final static int statusCode = 400;

    public ClientException(String errorCode, String message) {
        super(errorCode, message, statusCode, null);
    }

}
