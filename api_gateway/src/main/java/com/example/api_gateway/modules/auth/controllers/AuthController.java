package com.example.api_gateway.modules.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth/v1")

public class AuthController {

    @GetMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> signIn(Principal principal) {
        System.out.println(principal.getName());
        return ResponseEntity.ok(principal.getName());
    }
}
