package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;

public interface LeaveService {

    LeaveResponseDTO applyLeave(LeaveRequestDTO dto);

    LeaveResponseDTO getLeaveById(Long leaveId);

    List<LeaveResponseDTO> getAllLeaves();

    List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId);

    LeaveResponseDTO approveLeave(Long leaveId);

    LeaveResponseDTO rejectLeave(Long leaveId);

    void deleteLeave(Long leaveId);
}