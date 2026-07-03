package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto);

    DepartmentResponseDTO getDepartmentById(Long id);

    List<DepartmentResponseDTO> getAllDepartments();

    DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto);

    void deleteDepartment(Long id);
}