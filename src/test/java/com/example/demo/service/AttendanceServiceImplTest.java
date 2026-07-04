package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

import com.example.demo.dto.AttendanceRequestDTO;
import com.example.demo.dto.AttendanceResponseDTO;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.impl.AttendanceServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceImplTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    private Employee employee;
    private Attendance attendance;
    private AttendanceRequestDTO requestDTO;

    @BeforeEach
    void setUp() {

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstname("Varun");
        employee.setLastname("MR");

        attendance = new Attendance();
        attendance.setAttendanceId(1L);
        attendance.setEmployee(employee);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.of(9, 0));
        attendance.setCheckOutTime(LocalTime.of(18, 0));
        attendance.setStatus("PRESENT");

        requestDTO = new AttendanceRequestDTO();
        requestDTO.setEmployeeId(1L);
        requestDTO.setAttendanceDate(LocalDate.now());
        requestDTO.setCheckInTime(LocalTime.of(9, 0));
        requestDTO.setCheckOutTime(LocalTime.of(18, 0));
        requestDTO.setStatus("PRESENT");
    }

    @Test
    void testMarkAttendanceSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(attendanceRepository.save(any(Attendance.class)))
                .thenReturn(attendance);

        AttendanceResponseDTO response =
                attendanceService.markAttendance(requestDTO);

        assertNotNull(response);

        assertEquals(
                "PRESENT",
                response.getStatus());

        verify(attendanceRepository)
                .save(any(Attendance.class));
    }

    @Test
    void testGetAttendanceByIdSuccess() {

        when(attendanceRepository.findById(1L))
                .thenReturn(Optional.of(attendance));

        AttendanceResponseDTO response =
                attendanceService.getAttendanceById(1L);

        assertNotNull(response);

        assertEquals(
                1L,
                response.getEmployeeId());
    }

    @Test
    void testGetAttendanceByIdNotFound() {

        when(attendanceRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> attendanceService.getAttendanceById(1L));
    }

    @Test
    void testGetAllAttendance() {

        when(attendanceRepository.findAll())
                .thenReturn(Arrays.asList(attendance));

        assertEquals(
                1,
                attendanceService.getAllAttendance().size());
    }

    @Test
    void testUpdateAttendanceSuccess() {

        when(attendanceRepository.findById(1L))
                .thenReturn(Optional.of(attendance));

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(attendanceRepository.save(any(Attendance.class)))
                .thenReturn(attendance);

        AttendanceResponseDTO response =
                attendanceService.updateAttendance(
                        1L,
                        requestDTO);

        assertNotNull(response);

        verify(attendanceRepository)
                .save(any(Attendance.class));
    }

    @Test
    void testDeleteAttendanceSuccess() {

        when(attendanceRepository.findById(1L))
                .thenReturn(Optional.of(attendance));

        attendanceService.deleteAttendance(1L);

        verify(attendanceRepository)
                .delete(attendance);
    }
}