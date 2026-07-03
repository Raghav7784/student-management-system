package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Leave;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository,
                            EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LeaveResponseDTO applyLeave(LeaveRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Leave leave = new Leave();

        leave.setEmployee(employee);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setLeaveType(dto.getLeaveType());
        leave.setReason(dto.getReason());
        leave.setStatus("PENDING");

        return mapToDTO(leaveRepository.save(leave));
    }

    @Override
    public LeaveResponseDTO getLeaveById(Long leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        return mapToDTO(leave);
    }

    @Override
    public List<LeaveResponseDTO> getAllLeaves() {

        List<Leave> leaves = leaveRepository.findAll();

        return leaves.stream()
                .map(leave -> mapToDTO(leave))
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId) {

        List<Leave> leaves =
                leaveRepository.findByEmployeeEmployeeId(employeeId);

        return leaves.stream()
                .map(leave -> mapToDTO(leave))
                .collect(Collectors.toList());
    }

    @Override
    public LeaveResponseDTO approveLeave(Long leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("APPROVED");

        return mapToDTO(leaveRepository.save(leave));
    }

    @Override
    public LeaveResponseDTO rejectLeave(Long leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("REJECTED");

        return mapToDTO(leaveRepository.save(leave));
    }

    @Override
    public void deleteLeave(Long leaveId) {

        leaveRepository.deleteById(leaveId);
    }

    private LeaveResponseDTO mapToDTO(Leave leave) {

        LeaveResponseDTO dto = new LeaveResponseDTO();

        dto.setLeaveId(leave.getLeaveId());

        if (leave.getEmployee() != null) {
            dto.setEmployeeName(
                    leave.getEmployee().getFirstName() + " "
                    + leave.getEmployee().getLastname());
        }

        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setLeaveType(leave.getLeaveType());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());

        return dto;
    }
}