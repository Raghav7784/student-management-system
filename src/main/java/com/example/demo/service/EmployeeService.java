package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;

public interface EmployeeService {

    EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO getEmployeeById(Long employeeId);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO updateEmployee(Long employeeId,
                                       EmployeeRequestDTO employeeRequestDTO);

    void deleteEmployee(Long employeeId);

}