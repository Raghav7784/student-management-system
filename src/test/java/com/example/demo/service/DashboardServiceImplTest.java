package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.example.demo.dto.UtilizationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.impl.DashboardServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DashboardServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AllocationRepository allocationRepository;

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    private Employee employee;
    private Allocation allocation;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstname("Varun");
        employee.setLastname("MR");

        allocation = new Allocation();
        allocation.setAllocationId(1L);
        allocation.setEmployee(employee);
        allocation.setAllocationPercentage(70);
    }

    @Test
    void testGetUtilizationReport() {

        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee));

        when(allocationRepository.findAll())
                .thenReturn(Arrays.asList(allocation));

        List<UtilizationDTO> result =
                dashboardService.getUtilizationReport();

        assertNotNull(result);
        assertEquals(1, result.size());

        UtilizationDTO dto = result.get(0);

        assertEquals(
                "Varun MR",
                dto.getEmployeeName());

        assertEquals(
                70,
                dto.getBillablePercentage());

        assertEquals(
                30,
                dto.getBenchPercentage());
    }

    @Test
    void testGetUtilizationReportNoAllocations() {

        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee));

        when(allocationRepository.findAll())
                .thenReturn(Arrays.asList());

        List<UtilizationDTO> result =
                dashboardService.getUtilizationReport();

        assertEquals(1, result.size());

        UtilizationDTO dto = result.get(0);

        assertEquals(0, dto.getBillablePercentage());
        assertEquals(100, dto.getBenchPercentage());
    }
}