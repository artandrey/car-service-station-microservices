package com.example.api_gateway.modules.auth.exceptions;

import com.example.api_gateway.shared.exceptions.ClientException;

public class SignUpFailedException extends ClientException {

    public SignUpFailedException(String message) {
        super("SIGN_UP_FAILED", message);
    }

}
