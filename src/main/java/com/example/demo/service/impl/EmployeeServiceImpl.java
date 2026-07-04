package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger =
            LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               UserRepository userRepository,
                               DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {

        logger.info("Adding employee with phone: {}",
                employeeRequestDTO.getPhone());

        if (employeeRepository.findByPhone(employeeRequestDTO.getPhone()).isPresent()) {

            logger.warn("Employee creation failed. Phone already exists: {}",
                    employeeRequestDTO.getPhone());

            throw new RuntimeException("Phone number already exists");
        }

        User user = userRepository.findById(employeeRequestDTO.getUserId())
                .orElseThrow(() -> {

                    logger.error("User not found with ID: {}",
                            employeeRequestDTO.getUserId());

                    return new ResourceNotFoundException("User not found");
                });

        Department department = departmentRepository
                .findById(employeeRequestDTO.getDepartmentId())
                .orElseThrow(() -> {

                    logger.error("Department not found with ID: {}",
                            employeeRequestDTO.getDepartmentId());

                    return new ResourceNotFoundException("Department not found");
                });

        Employee employee = new Employee();

        employee.setFirstname(employeeRequestDTO.getFirstName());
        employee.setLastname(employeeRequestDTO.getLastName());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setDepartment(department);
        employee.setExperience(employeeRequestDTO.getExperience());
        employee.setAvailability(true);
        employee.setUser(user);

        Employee savedEmployee = employeeRepository.save(employee);

        logger.info("Employee created successfully with ID: {}",
                savedEmployee.getEmployeeId());

        return mapToResponseDTO(savedEmployee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long employeeId) {

        logger.info("Fetching employee with ID: {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {

                    logger.error("Employee not found with ID: {}",
                            employeeId);

                    return new ResourceNotFoundException("Employee not found");
                });

        return mapToResponseDTO(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        logger.info("Fetching all employees");

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO updateEmployee(
            Long employeeId,
            EmployeeRequestDTO employeeRequestDTO) {

        logger.info("Updating employee with ID: {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {

                    logger.error("Employee not found with ID: {}",
                            employeeId);

                    return new ResourceNotFoundException("Employee not found");
                });

        User user = userRepository.findById(employeeRequestDTO.getUserId())
                .orElseThrow(() -> {

                    logger.error("User not found with ID: {}",
                            employeeRequestDTO.getUserId());

                    return new ResourceNotFoundException("User not found");
                });

        Department department = departmentRepository
                .findById(employeeRequestDTO.getDepartmentId())
                .orElseThrow(() -> {

                    logger.error("Department not found with ID: {}",
                            employeeRequestDTO.getDepartmentId());

                    return new ResourceNotFoundException("Department not found");
                });

        employee.setFirstname(employeeRequestDTO.getFirstName());
        employee.setLastname(employeeRequestDTO.getLastName());
        employee.setPhone(employeeRequestDTO.getPhone());
        employee.setDepartment(department);
        employee.setExperience(employeeRequestDTO.getExperience());
        employee.setUser(user);

        Employee updatedEmployee = employeeRepository.save(employee);

        logger.info("Employee updated successfully with ID: {}",
                updatedEmployee.getEmployeeId());

        return mapToResponseDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        logger.info("Deleting employee with ID: {}", employeeId);

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {

                    logger.error("Employee not found with ID: {}",
                            employeeId);

                    return new ResourceNotFoundException("Employee not found");
                });

        employeeRepository.delete(employee);

        logger.info("Employee deleted successfully with ID: {}",
                employeeId);
    }

    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();

        responseDTO.setEmployeeId(employee.getEmployeeId());
        responseDTO.setFirstName(employee.getFirstname());
        responseDTO.setLastName(employee.getLastname());
        responseDTO.setPhone(employee.getPhone());
        responseDTO.setDepartment(
                employee.getDepartment().getDepartmentName());
        responseDTO.setExperience(employee.getExperience());
        responseDTO.setAvailability(employee.getAvailability());

        return responseDTO;
    }

	@Override
	public Page<EmployeeResponseDTO> getEmployees(int page, int size, String sortBy, String direction, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}