package com.example.api_gateway.modules.auth.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.api_gateway.modules.auth.dto.AuthResponseDto;
import com.example.api_gateway.modules.auth.dto.SignInRequestDto;
import com.example.api_gateway.modules.auth.dto.SignUpRequestDto;
import com.example.api_gateway.modules.auth.models.AuthResult;
import com.example.api_gateway.modules.auth.models.SignInCredentials;
import com.example.api_gateway.modules.auth.models.SignUpCredentials;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthMapper {

    private final ModelMapper modelMapper;

    public SignInCredentials toSignInCredentials(SignInRequestDto signInRequestDto) {
        return modelMapper.map(signInRequestDto, SignInCredentials.class);
    }

    public SignUpCredentials toSignUpCredentials(SignUpRequestDto signUpRequestDto) {
        return modelMapper.map(signUpRequestDto, SignUpCredentials.class);
    }

    public AuthResponseDto toAuthResponseDto(AuthResult authResult) {
        return modelMapper.map(authResult, AuthResponseDto.class);
    }
}
