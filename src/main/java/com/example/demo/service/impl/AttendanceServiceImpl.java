package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AttendanceRequestDTO;
import com.example.demo.dto.AttendanceResponseDTO;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public AttendanceResponseDTO markAttendance(AttendanceRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = new Attendance();

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setStatus(dto.getStatus());

        Attendance saved = attendanceRepository.save(attendance);

        return mapToDTO(saved);
    }

    @Override
    public AttendanceResponseDTO getAttendanceById(Long attendanceId) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return mapToDTO(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceResponseDTO updateAttendance(Long attendanceId,
                                                  AttendanceRequestDTO dto) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setStatus(dto.getStatus());

        Attendance updated = attendanceRepository.save(attendance);

        return mapToDTO(updated);
    }

    @Override
    public void deleteAttendance(Long attendanceId) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceRepository.delete(attendance);
    }

    private AttendanceResponseDTO mapToDTO(Attendance attendance) {

        AttendanceResponseDTO dto = new AttendanceResponseDTO();

        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setEmployeeId(attendance.getEmployee().getEmployeeId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setStatus(attendance.getStatus());

        return dto;
    }
}