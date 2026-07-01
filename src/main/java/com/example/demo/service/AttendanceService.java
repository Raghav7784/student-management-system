package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AttendanceRequestDTO;
import com.example.demo.dto.AttendanceResponseDTO;

public interface AttendanceService {

    AttendanceResponseDTO markAttendance(AttendanceRequestDTO attendanceRequestDTO);

    AttendanceResponseDTO getAttendanceById(Long attendanceId);

    List<AttendanceResponseDTO> getAllAttendance();

    AttendanceResponseDTO updateAttendance(Long attendanceId,
                                           AttendanceRequestDTO attendanceRequestDTO);

    void deleteAttendance(Long attendanceId);
}