package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Leave;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.service.impl.LeaveServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeaveServiceImplTest {

    @Mock
    private LeaveRepository leaveRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private LeaveServiceImpl leaveService;

    private Employee employee;
    private Leave leave;
    private LeaveRequestDTO requestDTO;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstname("Varun");
        employee.setLastname("MR");

        leave = new Leave();
        leave.setLeaveId(1L);
        leave.setEmployee(employee);
        leave.setStartDate(LocalDate.of(2026, 7, 10));
        leave.setEndDate(LocalDate.of(2026, 7, 12));
        leave.setLeaveType("SICK");
        leave.setReason("Fever");
        leave.setStatus("PENDING");

        requestDTO = new LeaveRequestDTO();
        requestDTO.setEmployeeId(1L);
        requestDTO.setStartDate(LocalDate.of(2026, 7, 10));
        requestDTO.setEndDate(LocalDate.of(2026, 7, 12));
        requestDTO.setLeaveType("SICK");
        requestDTO.setReason("Fever");
    }

    @Test
    void testApplyLeaveSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(leaveRepository.save(any(Leave.class)))
                .thenReturn(leave);

        LeaveResponseDTO response =
                leaveService.applyLeave(requestDTO);

        assertNotNull(response);

        assertEquals(
                "SICK",
                response.getLeaveType());

        assertEquals(
                "PENDING",
                response.getStatus());

        verify(leaveRepository)
                .save(any(Leave.class));
    }

    @Test
    void testGetLeaveByIdSuccess() {

        when(leaveRepository.findById(1L))
                .thenReturn(Optional.of(leave));

        LeaveResponseDTO response =
                leaveService.getLeaveById(1L);

        assertNotNull(response);

        assertEquals(
                "Varun MR",
                response.getEmployeeName());
    }

    @Test
    void testGetLeaveByIdNotFound() {

        when(leaveRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> leaveService.getLeaveById(1L));
    }

    @Test
    void testGetAllLeaves() {

        when(leaveRepository.findAll())
                .thenReturn(Arrays.asList(leave));

        assertEquals(
                1,
                leaveService.getAllLeaves().size());
    }

    @Test
    void testGetLeavesByEmployee() {

        when(leaveRepository.findByEmployeeEmployeeId(1L))
                .thenReturn(Arrays.asList(leave));

        assertEquals(
                1,
                leaveService.getLeavesByEmployee(1L).size());
    }

    @Test
    void testApproveLeaveSuccess() {

        when(leaveRepository.findById(1L))
                .thenReturn(Optional.of(leave));

        when(leaveRepository.save(any(Leave.class)))
                .thenReturn(leave);

        LeaveResponseDTO response =
                leaveService.approveLeave(1L);

        assertNotNull(response);

        verify(leaveRepository)
                .save(any(Leave.class));
    }

    @Test
    void testRejectLeaveSuccess() {

        when(leaveRepository.findById(1L))
                .thenReturn(Optional.of(leave));

        when(leaveRepository.save(any(Leave.class)))
                .thenReturn(leave);

        LeaveResponseDTO response =
                leaveService.rejectLeave(1L);

        assertNotNull(response);

        verify(leaveRepository)
                .save(any(Leave.class));
    }

    @Test
    void testDeleteLeaveSuccess() {

        when(leaveRepository.findById(1L))
                .thenReturn(Optional.of(leave));

        leaveService.deleteLeave(1L);

        verify(leaveRepository)
                .delete(leave);
    }
}