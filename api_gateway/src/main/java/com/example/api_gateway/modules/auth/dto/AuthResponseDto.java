package com.example.api_gateway.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object containing authentication information")
public class AuthResponseDto {

    @Schema(description = "Access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;

    @Schema(description = "Refresh token", example = "dGhpc19pcyBhIHJlZnJlc2ggdG9rZW4gdGhhdCB5b3UgY2FuIHVzZQ==")
    private String refreshToken;

    @Schema(description = "Access token expiration time in seconds", example = "3600")
    private Integer expiresIn;

    @Schema(description = "Refresh token expiration time in seconds", example = "7200")
    private Integer refreshExpiresIn;
}
