package com.example.demo.service;

import com.example.demo.service.impl.EmployeeServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private User user;
    private Department department;
    private EmployeeRequestDTO requestDTO;

    @BeforeEach
    void setUp() {

        Role role = new Role();
        role.setRoleid(1L);
        role.setRoleName("EMPLOYEE");

        user = new User();
        user.setUserId(1L);
        user.setUsername("varun");
        user.setEmail("varun@gmail.com");
        user.setPassword("password123");
        user.setEnabled(true);
        user.setRole(role);

        department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("IT");
        department.setDescription("Information Technology");

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstname("Varun");
        employee.setLastname("MR");
        employee.setPhone("9876543210");
        employee.setDepartment(department);
        employee.setExperience(2);
        employee.setAvailability(true);
        employee.setUser(user);

        requestDTO = new EmployeeRequestDTO();
        requestDTO.setFirstName("Varun");
        requestDTO.setLastName("MR");
        requestDTO.setPhone("9876543210");
        requestDTO.setDepartmentId(1L);
        requestDTO.setExperience(2);
        requestDTO.setUserId(1L);
    }

    @Test
    void testAddEmployeeSuccess() {

        when(employeeRepository.findByPhone("9876543210"))
                .thenReturn(Optional.empty());

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponseDTO response =
                employeeService.addEmployee(requestDTO);

        assertNotNull(response);
        assertEquals("Varun", response.getFirstName());
        assertEquals("IT", response.getDepartment());

        verify(employeeRepository)
                .save(any(Employee.class));
    }

    @Test
    void testAddEmployeePhoneAlreadyExists() {

        when(employeeRepository.findByPhone("9876543210"))
                .thenReturn(Optional.of(employee));

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> employeeService.addEmployee(requestDTO));

        assertEquals(
                "Phone number already exists",
                exception.getMessage());
    }

    @Test
    void testGetEmployeeByIdSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponseDTO response =
                employeeService.getEmployeeById(1L);

        assertEquals(
                "Varun",
                response.getFirstName());
    }

    @Test
    void testGetEmployeeByIdNotFound() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.getEmployeeById(1L));
    }

    @Test
    void testGetAllEmployees() {

        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee));

        assertEquals(
                1,
                employeeService.getAllEmployees().size());
    }

    @Test
    void testUpdateEmployeeSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponseDTO response =
                employeeService.updateEmployee(
                        1L,
                        requestDTO);

        assertEquals(
                "Varun",
                response.getFirstName());

        verify(employeeRepository)
                .save(any(Employee.class));
    }

    @Test
    void testDeleteEmployeeSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository)
                .delete(employee);
    }
}