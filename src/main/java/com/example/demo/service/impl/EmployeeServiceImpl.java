package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {

        if (employeeRepository.findByPhone(employeeRequestDTO.getPhone()).isPresent()) {
            throw new RuntimeException("Phone number already exists");
        }

       
        User user = userRepository.findById(employeeRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        
        Employee employee = new Employee();
        employee.setFirstname(employeeRequestDTO.getFirstName());
        employee.setLastname(employeeRequestDTO.getLastName());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setExperience(employeeRequestDTO.getExperience());
        employee.setAvailability(true);
        employee.setUser(user);

        
        Employee savedEmployee = employeeRepository.save(employee);

        
        return mapToResponseDTO(savedEmployee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return mapToResponseDTO(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long employeeId,
            EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        User user = userRepository.findById(employeeRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        employee.setFirstname(employeeRequestDTO.getFirstName());
        employee.setLastname(employeeRequestDTO.getLastName());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setDepartment(employeeRequestDTO.getDepartment());
        employee.setExperience(employeeRequestDTO.getExperience());
        employee.setUser(user);

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponseDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
    }

    
    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();

        responseDTO.setEmployeeId(employee.getEmployeeId());
        responseDTO.setFirstName(employee.getFirstname());
        responseDTO.setLastName(employee.getLastname());
        responseDTO.setPhone(employee.getPhone());
        responseDTO.setDepartment(employee.getDepartment());
        responseDTO.setExperience(employee.getExperience());
        responseDTO.setAvailability(employee.getAvailability());

        return responseDTO;
    }
}