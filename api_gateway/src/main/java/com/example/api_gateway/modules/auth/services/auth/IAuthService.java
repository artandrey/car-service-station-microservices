package com.example.api_gateway.modules.auth.services.auth;

import com.example.api_gateway.modules.auth.models.AuthResult;
import com.example.api_gateway.modules.auth.models.SignInCredentials;
import com.example.api_gateway.modules.auth.models.SignUpCredentials;

public interface IAuthService {
    AuthResult signIn(SignInCredentials credentials);

    AuthResult signUp(SignUpCredentials credentials);

    AuthResult refresh(String refreshToken);
}
