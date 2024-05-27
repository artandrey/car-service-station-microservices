package com.example.api_gateway.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for signing in")
public class SignInRequestDto {

    @Schema(description = "Username of the user", example = "john.doe")
    private String username;

    @Schema(description = "Password of the user", example = "password123")
    private String password;
}
