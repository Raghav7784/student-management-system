package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO addUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    void deleteUser(Long id);
}