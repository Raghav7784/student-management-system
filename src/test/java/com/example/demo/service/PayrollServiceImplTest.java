package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import com.example.demo.dto.PayrollRequestDTO;
import com.example.demo.dto.PayrollResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Payroll;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PayrollRepository;
import com.example.demo.service.impl.PayrollServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PayrollServiceImplTest {

    @Mock
    private PayrollRepository payrollRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private PayrollServiceImpl payrollService;

    private Employee employee;
    private Payroll payroll;
    private PayrollRequestDTO requestDTO;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstname("Varun");
        employee.setLastname("MR");

        payroll = new Payroll();
        payroll.setPayrollId(1L);
        payroll.setEmployee(employee);
        payroll.setBasicSalary(50000.0);
        payroll.setBonus(5000.0);
        payroll.setDeductions(2000.0);
        payroll.setNetSalary(53000.0);
        payroll.setPaymentDate(LocalDate.now());

        requestDTO = new PayrollRequestDTO();
        requestDTO.setEmployeeId(1L);
        requestDTO.setBasicSalary(50000.0);
        requestDTO.setBonus(5000.0);
        requestDTO.setDeductions(2000.0);
        requestDTO.setPaymentDate(LocalDate.now());
    }

    @Test
    void testAddPayrollSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(payrollRepository.save(any(Payroll.class)))
                .thenReturn(payroll);

        PayrollResponseDTO response =
                payrollService.addPayroll(requestDTO);

        assertNotNull(response);

        assertEquals(
                53000.0,
                response.getNetSalary());

        verify(payrollRepository)
                .save(any(Payroll.class));
    }

    @Test
    void testGetPayrollByIdSuccess() {

        when(payrollRepository.findById(1L))
                .thenReturn(Optional.of(payroll));

        PayrollResponseDTO response =
                payrollService.getPayrollById(1L);

        assertNotNull(response);

        assertEquals(
                "Varun MR",
                response.getEmployeeName());
    }

    @Test
    void testGetPayrollByIdNotFound() {

        when(payrollRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> payrollService.getPayrollById(1L));
    }

    @Test
    void testGetAllPayrolls() {

        when(payrollRepository.findAll())
                .thenReturn(Arrays.asList(payroll));

        assertEquals(
                1,
                payrollService.getAllPayrolls().size());
    }

    @Test
    void testUpdatePayrollSuccess() {

        when(payrollRepository.findById(1L))
                .thenReturn(Optional.of(payroll));

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(payrollRepository.save(any(Payroll.class)))
                .thenReturn(payroll);

        PayrollResponseDTO response =
                payrollService.updatePayroll(
                        1L,
                        requestDTO);

        assertNotNull(response);

        assertEquals(
                53000.0,
                response.getNetSalary());

        verify(payrollRepository)
                .save(any(Payroll.class));
    }

    @Test
    void testDeletePayrollSuccess() {

        when(payrollRepository.findById(1L))
                .thenReturn(Optional.of(payroll));

        payrollService.deletePayroll(1L);

        verify(payrollRepository)
                .delete(payroll);
    }
}