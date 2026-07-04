package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Leave;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

    private static final Logger logger =
            LoggerFactory.getLogger(LeaveServiceImpl.class);

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveServiceImpl(LeaveRepository leaveRepository,
                            EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LeaveResponseDTO applyLeave(LeaveRequestDTO dto) {

        logger.info(
                "Applying leave for employee ID: {}",
                dto.getEmployeeId());

        Employee employee = employeeRepository
                .findById(dto.getEmployeeId())
                .orElseThrow(() -> {

                    logger.error(
                            "Employee not found with ID: {}",
                            dto.getEmployeeId());

                    return new ResourceNotFoundException(
                            "Employee not found");
                });

        Leave leave = new Leave();

        leave.setEmployee(employee);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setLeaveType(dto.getLeaveType());
        leave.setReason(dto.getReason());
        leave.setStatus("PENDING");

        Leave savedLeave =
                leaveRepository.save(leave);

        logger.info(
                "Leave applied successfully with ID: {}",
                savedLeave.getLeaveId());

        return mapToDTO(savedLeave);
    }

    @Override
    public LeaveResponseDTO getLeaveById(Long leaveId) {

        logger.info(
                "Fetching leave with ID: {}",
                leaveId);

        Leave leave = leaveRepository
                .findById(leaveId)
                .orElseThrow(() -> {

                    logger.error(
                            "Leave not found with ID: {}",
                            leaveId);

                    return new ResourceNotFoundException(
                            "Leave not found");
                });

        return mapToDTO(leave);
    }

    @Override
    public List<LeaveResponseDTO> getAllLeaves() {

        logger.info(
                "Fetching all leave records");

        List<Leave> leaves =
                leaveRepository.findAll();

        return leaves.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeaveResponseDTO> getLeavesByEmployee(
            Long employeeId) {

        logger.info(
                "Fetching leaves for employee ID: {}",
                employeeId);

        List<Leave> leaves =
                leaveRepository
                        .findByEmployeeEmployeeId(
                                employeeId);

        return leaves.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LeaveResponseDTO approveLeave(
            Long leaveId) {

        logger.info(
                "Approving leave with ID: {}",
                leaveId);

        Leave leave = leaveRepository
                .findById(leaveId)
                .orElseThrow(() -> {

                    logger.error(
                            "Leave not found with ID: {}",
                            leaveId);

                    return new ResourceNotFoundException(
                            "Leave not found");
                });

        leave.setStatus("APPROVED");

        Leave updatedLeave =
                leaveRepository.save(leave);

        logger.info(
                "Leave approved successfully with ID: {}",
                leaveId);

        return mapToDTO(updatedLeave);
    }

    @Override
    public LeaveResponseDTO rejectLeave(
            Long leaveId) {

        logger.info(
                "Rejecting leave with ID: {}",
                leaveId);

        Leave leave = leaveRepository
                .findById(leaveId)
                .orElseThrow(() -> {

                    logger.error(
                            "Leave not found with ID: {}",
                            leaveId);

                    return new ResourceNotFoundException(
                            "Leave not found");
                });

        leave.setStatus("REJECTED");

        Leave updatedLeave =
                leaveRepository.save(leave);

        logger.info(
                "Leave rejected successfully with ID: {}",
                leaveId);

        return mapToDTO(updatedLeave);
    }

    @Override
    public void deleteLeave(Long leaveId) {

        logger.info(
                "Deleting leave with ID: {}",
                leaveId);

        Leave leave = leaveRepository
                .findById(leaveId)
                .orElseThrow(() -> {

                    logger.error(
                            "Leave not found with ID: {}",
                            leaveId);

                    return new ResourceNotFoundException(
                            "Leave not found");
                });

        leaveRepository.delete(leave);

        logger.info(
                "Leave deleted successfully with ID: {}",
                leaveId);
    }

    private LeaveResponseDTO mapToDTO(
            Leave leave) {

        LeaveResponseDTO dto =
                new LeaveResponseDTO();

        dto.setLeaveId(
                leave.getLeaveId());

        if (leave.getEmployee() != null) {

            dto.setEmployeeName(
                    leave.getEmployee().getFirstname()
                    + " "
                    + leave.getEmployee().getLastname());
        }

        dto.setStartDate(
                leave.getStartDate());

        dto.setEndDate(
                leave.getEndDate());

        dto.setLeaveType(
                leave.getLeaveType());

        dto.setReason(
                leave.getReason());

        dto.setStatus(
                leave.getStatus());

        return dto;
    }
}