package com.example.api_gateway.modules.auth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInCredentials {
    private String username;
    private String password;
}
