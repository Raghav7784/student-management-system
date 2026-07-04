package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.demo.dto.PerformanceRequestDTO;
import com.example.demo.dto.PerformanceResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Performance;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PerformanceRepository;
import com.example.demo.service.impl.PerformanceServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceServiceImplTest {

    @Mock
    private PerformanceRepository performanceRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private PerformanceServiceImpl performanceService;

    private Employee employee;
    private Performance performance;
    private PerformanceRequestDTO requestDTO;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstname("Varun");
        employee.setLastname("MR");

        performance = new Performance();
        performance.setPerformanceId(1L);
        performance.setEmployee(employee);
        performance.setReviewPeriod("Q1 2026");
        performance.setRating(4.5);
        performance.setFeedback("Excellent work");
        performance.setGoals("Lead a new project");

        requestDTO = new PerformanceRequestDTO();
        requestDTO.setEmployeeId(1L);
        requestDTO.setReviewPeriod("Q1 2026");
        requestDTO.setRating(4.5);
        requestDTO.setFeedback("Excellent work");
        requestDTO.setGoals("Lead a new project");
    }

    @Test
    void testAddPerformanceSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(performanceRepository.save(any(Performance.class)))
                .thenReturn(performance);

        PerformanceResponseDTO response =
                performanceService.addPerformance(requestDTO);

        assertNotNull(response);
        assertEquals(4.5, response.getRating());
        assertEquals("Q1 2026", response.getReviewPeriod());

        verify(performanceRepository)
                .save(any(Performance.class));
    }

    @Test
    void testGetPerformanceByIdSuccess() {

        when(performanceRepository.findById(1L))
                .thenReturn(Optional.of(performance));

        PerformanceResponseDTO response =
                performanceService.getPerformanceById(1L);

        assertNotNull(response);
        assertEquals("Varun MR", response.getEmployeeName());
    }

    @Test
    void testGetPerformanceByIdNotFound() {

        when(performanceRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> performanceService.getPerformanceById(1L));
    }

    @Test
    void testGetAllPerformances() {

        when(performanceRepository.findAll())
                .thenReturn(Arrays.asList(performance));

        assertEquals(
                1,
                performanceService.getAllPerformances().size());
    }

    @Test
    void testUpdatePerformanceSuccess() {

        when(performanceRepository.findById(1L))
                .thenReturn(Optional.of(performance));

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(performanceRepository.save(any(Performance.class)))
                .thenReturn(performance);

        PerformanceResponseDTO response =
                performanceService.updatePerformance(
                        1L,
                        requestDTO);

        assertNotNull(response);
        assertEquals(4.5, response.getRating());

        verify(performanceRepository)
                .save(any(Performance.class));
    }

    @Test
    void testDeletePerformanceSuccess() {

        when(performanceRepository.findById(1L))
                .thenReturn(Optional.of(performance));

        performanceService.deletePerformance(1L);

        verify(performanceRepository)
                .delete(performance);
    }
}