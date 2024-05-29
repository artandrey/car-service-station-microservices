package com.example.api_gateway.modules.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api_gateway.modules.auth.dto.AuthResponseDto;
import com.example.api_gateway.modules.auth.dto.SignInRequestDto;
import com.example.api_gateway.modules.auth.dto.SignUpRequestDto;
import com.example.api_gateway.modules.auth.mappers.AuthMapper;
import com.example.api_gateway.modules.auth.services.auth.IAuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Auth")

public class AuthController {

        private final IAuthService authService;
        private final AuthMapper authMapper;

        @Operation(summary = "Sign in a user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully signed in"),
                        @ApiResponse(responseCode = "400", description = "Invalid input data"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized")
        })
        @PostMapping("/sign-in")
        public ResponseEntity<AuthResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto) {
                AuthResponseDto authResponseDto = authMapper.toAuthResponseDto(
                                authService.signIn(authMapper.toSignInCredentials(signInRequestDto)));
                return ResponseEntity.ok(authResponseDto);
        }

        @Operation(summary = "Sign up a new user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully signed up"),
                        @ApiResponse(responseCode = "400", description = "Invalid input data"),
                        @ApiResponse(responseCode = "409", description = "User already exists")
        })
        @PostMapping("/sign-up")
        public ResponseEntity<AuthResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
                AuthResponseDto authResponseDto = authMapper.toAuthResponseDto(
                                authService.signUp(authMapper.toSignUpCredentials(signUpRequestDto)));
                return ResponseEntity.ok(authResponseDto);
        }

        @Operation(summary = "Refresh access token")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successfully refreshed token"),
                        @ApiResponse(responseCode = "400", description = "Invalid input data"),
                        @ApiResponse(responseCode = "401", description = "Unauthorized")
        })
        @PostMapping("/refresh")
        public ResponseEntity<AuthResponseDto> refresh(@RequestParam String refreshToken) {
                AuthResponseDto authResponseDto = authMapper.toAuthResponseDto(authService.refresh(refreshToken));
                return ResponseEntity.ok(authResponseDto);
        }
}
