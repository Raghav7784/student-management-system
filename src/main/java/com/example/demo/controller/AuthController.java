package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ChangePasswordRequestDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    AuthController.class);

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequestDTO request) {

        return ResponseEntity.ok(
                authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request) {

        logger.info(
                "User login attempt: {}",
                request.getUsername());

        LoginResponseDTO response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid
            @RequestBody
            ChangePasswordRequestDTO request) {

        return ResponseEntity.ok(
                authService.changePassword(
                        request));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        return ResponseEntity.ok(
                authService.logout());
    }
}