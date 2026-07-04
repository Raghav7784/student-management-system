package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ChangePasswordRequestDTO;
import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.dto.LoginResponseDTO;
import com.example.demo.dto.RegisterRequestDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JWTService;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JWTService jwtService) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public String register(RegisterRequestDTO request) {

        if (userRepository.existsByUsername(
                request.getUsername())) {

            throw new RuntimeException(
                    "Username already exists");
        }

        if (userRepository.existsByEmail(
                request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists");
        }

        Role role = roleRepository.findById(
                request.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        user.setEnabled(true);
        user.setRole(role);

        userRepository.save(user);

        logger.info(
                "User registered successfully: {}",
                user.getUsername());

        return "User Registered Successfully";
    }

    @Override
    public LoginResponseDTO login(
            LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = userRepository
                .findByUsername(
                        request.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        String token;

        try {

            token = jwtService.generateToken(
                    user.getUsername());

        } catch (Exception e) {

            logger.error(
                    "Token generation failed", e);

            throw new RuntimeException(
                    "Token generation failed");
        }

        logger.info(
                "User logged in: {}",
                user.getUsername());

        return new LoginResponseDTO(
                token,
                user.getUsername(),
                user.getRole().getRoleName());
    }

    @Override
    public String changePassword(
            ChangePasswordRequestDTO request) {

        User user = userRepository
                .findByUsername(
                        request.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        boolean matches =
                passwordEncoder.matches(
                        request.getOldPassword(),
                        user.getPassword());

        if (!matches) {

            throw new RuntimeException(
                    "Old password is incorrect");
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        userRepository.save(user);

        logger.info(
                "Password changed successfully for user: {}",
                user.getUsername());

        return "Password changed successfully";
    }

    @Override
    public String logout() {

        logger.info(
                "User logged out successfully");

        return "Logout successful. Please remove the JWT token from the client.";
    }
}