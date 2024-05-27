package com.example.api_gateway.modules.auth.exceptions;

import com.example.api_gateway.shared.exceptions.ServerException;

public class UnexpectedSignUpException extends ServerException {

    public UnexpectedSignUpException() {
        super("UNEXPECTED_SIGN_UP_EXCEPTION", "Unexpected sign up exception");
    }

}
