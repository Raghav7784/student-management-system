package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO dto) {

        logger.info(
                "Creating user with email: {}",
                dto.getEmail());

        if (userRepository.findByEmail(
                dto.getEmail()).isPresent()) {

            logger.warn(
                    "User creation failed. Email already exists: {}",
                    dto.getEmail());

            throw new RuntimeException(
                    "Email already exists");
        }

        Role role = roleRepository.findById(
                dto.getRoleId())
                .orElseThrow(() -> {

                    logger.error(
                            "Role not found with ID: {}",
                            dto.getRoleId());

                    return new ResourceNotFoundException(
                            "Role not found");
                });

        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setEnabled(true);
        user.setRole(role);

        User savedUser =
                userRepository.save(user);

        logger.info(
                "User created successfully: {}",
                savedUser.getUsername());

        return mapToDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        logger.info(
                "Fetching user with ID: {}",
                id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {

                    logger.error(
                            "User not found with ID: {}",
                            id);

                    return new ResourceNotFoundException(
                            "User not found");
                });

        return mapToDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        logger.info(
                "Fetching all users");

        return userRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(
            Long id,
            UserRequestDTO dto) {

        logger.info(
                "Updating user with ID: {}",
                id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {

                    logger.error(
                            "User not found with ID: {}",
                            id);

                    return new ResourceNotFoundException(
                            "User not found");
                });

        Role role = roleRepository.findById(
                dto.getRoleId())
                .orElseThrow(() -> {

                    logger.error(
                            "Role not found with ID: {}",
                            dto.getRoleId());

                    return new ResourceNotFoundException(
                            "Role not found");
                });

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(role);

        User updatedUser =
                userRepository.save(user);

        logger.info(
                "User updated successfully: {}",
                updatedUser.getUsername());

        return mapToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        logger.info(
                "Deleting user with ID: {}",
                id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {

                    logger.error(
                            "User not found with ID: {}",
                            id);

                    return new ResourceNotFoundException(
                            "User not found");
                });

        userRepository.delete(user);

        logger.info(
                "User deleted successfully with ID: {}",
                id);
    }

    private UserResponseDTO mapToDTO(User user) {

        UserResponseDTO dto =
                new UserResponseDTO();

        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());

        if (user.getRole() != null) {

            dto.setRoleName(
                    user.getRole().getRoleName());
        }

        return dto;
    }
}