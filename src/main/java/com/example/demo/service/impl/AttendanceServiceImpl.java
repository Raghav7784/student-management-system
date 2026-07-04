package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AttendanceRequestDTO;
import com.example.demo.dto.AttendanceResponseDTO;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private static final Logger logger =
            LoggerFactory.getLogger(AttendanceServiceImpl.class);

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AttendanceResponseDTO markAttendance(
            AttendanceRequestDTO dto) {

        logger.info(
                "Marking attendance for Employee ID: {}",
                dto.getEmployeeId());

        Employee employee = employeeRepository.findById(
                dto.getEmployeeId())
                .orElseThrow(() -> {

                    logger.error(
                            "Employee not found with ID: {}",
                            dto.getEmployeeId());

                    return new ResourceNotFoundException(
                            "Employee not found");
                });

        Attendance attendance = new Attendance();

        attendance.setEmployee(employee);

        attendance.setAttendanceDate(
                dto.getAttendanceDate());

        attendance.setCheckInTime(
                dto.getCheckInTime());

        attendance.setCheckOutTime(
                dto.getCheckOutTime());

        attendance.setStatus(
                dto.getStatus());

        Attendance saved =
                attendanceRepository.save(attendance);

        logger.info(
                "Attendance marked successfully for Employee ID: {}",
                dto.getEmployeeId());

        return mapToDTO(saved);
    }

    @Override
    public AttendanceResponseDTO getAttendanceById(
            Long attendanceId) {

        logger.info(
                "Fetching attendance with ID: {}",
                attendanceId);

        Attendance attendance =
                attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> {

                    logger.error(
                            "Attendance record not found with ID: {}",
                            attendanceId);

                    return new ResourceNotFoundException(
                            "Attendance record not found");
                });

        return mapToDTO(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendance() {

        logger.info("Fetching all attendance records");

        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponseDTO updateAttendance(
            Long attendanceId,
            AttendanceRequestDTO dto) {

        logger.info(
                "Updating attendance with ID: {}",
                attendanceId);

        Attendance attendance =
                attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> {

                    logger.error(
                            "Attendance record not found with ID: {}",
                            attendanceId);

                    return new ResourceNotFoundException(
                            "Attendance record not found");
                });

        Employee employee = employeeRepository.findById(
                dto.getEmployeeId())
                .orElseThrow(() -> {

                    logger.error(
                            "Employee not found with ID: {}",
                            dto.getEmployeeId());

                    return new ResourceNotFoundException(
                            "Employee not found");
                });

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(
                dto.getAttendanceDate());
        attendance.setStatus(dto.getStatus());

        Attendance updated =
                attendanceRepository.save(attendance);

        logger.info(
                "Attendance updated successfully with ID: {}",
                attendanceId);

        return mapToDTO(updated);
    }

    @Override
    public void deleteAttendance(Long attendanceId) {

        logger.info(
                "Deleting attendance with ID: {}",
                attendanceId);

        Attendance attendance =
                attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> {

                    logger.error(
                            "Attendance record not found with ID: {}",
                            attendanceId);

                    return new ResourceNotFoundException(
                            "Attendance record not found");
                });

        attendanceRepository.delete(attendance);

        logger.info(
                "Attendance deleted successfully with ID: {}",
                attendanceId);
    }

    private AttendanceResponseDTO mapToDTO(
            Attendance attendance) {

        AttendanceResponseDTO dto =
                new AttendanceResponseDTO();

        dto.setAttendanceId(
                attendance.getAttendanceId());

        dto.setEmployeeId(
                attendance.getEmployee().getEmployeeId());

        dto.setAttendanceDate(
                attendance.getAttendanceDate());

        dto.setStatus(
                attendance.getStatus());

        return dto;
    }
}