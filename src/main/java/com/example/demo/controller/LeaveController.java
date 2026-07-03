package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.service.LeaveService;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping
    public ResponseEntity<LeaveResponseDTO> applyLeave(
            @RequestBody LeaveRequestDTO dto) {

        return ResponseEntity.ok(leaveService.applyLeave(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<LeaveResponseDTO>> getAllLeaves() {

        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(
            @PathVariable Long id) {

        return ResponseEntity.ok(leaveService.getLeaveById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveResponseDTO> approveLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(leaveService.approveLeave(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(
            @PathVariable Long id) {

        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeave(
            @PathVariable Long id) {

        leaveService.deleteLeave(id);

        return ResponseEntity.ok("Leave deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveResponseDTO>> getEmployeeLeaves(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                leaveService.getLeavesByEmployee(employeeId));
    }
}