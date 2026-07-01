package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AttendanceRequestDTO;
import com.example.demo.dto.AttendanceResponseDTO;
import com.example.demo.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> markAttendance(
            @RequestBody AttendanceRequestDTO dto) {

        return new ResponseEntity<>(
                attendanceService.markAttendance(dto),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(attendanceService.getAttendanceById(id));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponseDTO>> getAllAttendance() {

        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceRequestDTO dto) {

        return ResponseEntity.ok(attendanceService.updateAttendance(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.ok("Attendance deleted successfully");
    }
}