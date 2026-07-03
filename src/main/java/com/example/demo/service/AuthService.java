package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.RegisterRequestDTO;

public interface AuthService {

    String register(RegisterRequestDTO request);

    LoginResponseDTO login(LoginRequestDTO request);

}