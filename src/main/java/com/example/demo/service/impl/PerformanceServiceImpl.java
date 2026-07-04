package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PerformanceRequestDTO;
import com.example.demo.dto.PerformanceResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Performance;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PerformanceRepository;
import com.example.demo.service.PerformanceService;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    private static final Logger logger =
            LoggerFactory.getLogger(PerformanceServiceImpl.class);

    private final PerformanceRepository performanceRepository;
    private final EmployeeRepository employeeRepository;

    public PerformanceServiceImpl(
            PerformanceRepository performanceRepository,
            EmployeeRepository employeeRepository) {

        this.performanceRepository = performanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public PerformanceResponseDTO addPerformance(
            PerformanceRequestDTO dto) {

        logger.info(
                "Adding performance review for Employee ID: {}",
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

        Performance performance = new Performance();

        performance.setEmployee(employee);
        performance.setReviewPeriod(dto.getReviewPeriod());
        performance.setRating(dto.getRating());
        performance.setFeedback(dto.getFeedback());
        performance.setGoals(dto.getGoals());

        Performance savedPerformance =
                performanceRepository.save(performance);

        logger.info(
                "Performance review added successfully for Employee ID: {}",
                dto.getEmployeeId());

        return mapToResponse(savedPerformance);
    }

    @Override
    public PerformanceResponseDTO getPerformanceById(Long id) {

        logger.info("Fetching performance with ID: {}", id);

        Performance performance = performanceRepository
                .findById(id)
                .orElseThrow(() -> {

                    logger.error(
                            "Performance not found with ID: {}",
                            id);

                    return new ResourceNotFoundException(
                            "Performance not found");
                });

        return mapToResponse(performance);
    }

    @Override
    public List<PerformanceResponseDTO> getAllPerformances() {

        logger.info("Fetching all performance records");

        return performanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PerformanceResponseDTO updatePerformance(
            Long id,
            PerformanceRequestDTO dto) {

        logger.info("Updating performance with ID: {}", id);

        Performance performance = performanceRepository
                .findById(id)
                .orElseThrow(() -> {

                    logger.error(
                            "Performance not found with ID: {}",
                            id);

                    return new ResourceNotFoundException(
                            "Performance not found");
                });

        Employee employee = employeeRepository
                .findById(dto.getEmployeeId())
                .orElseThrow(() -> {

                    logger.error(
                            "Employee not found with ID: {}",
                            dto.getEmployeeId());

                    return new ResourceNotFoundException(
                            "Employee not found");
                });

        performance.setEmployee(employee);
        performance.setReviewPeriod(dto.getReviewPeriod());
        performance.setRating(dto.getRating());
        performance.setFeedback(dto.getFeedback());
        performance.setGoals(dto.getGoals());

        Performance updatedPerformance =
                performanceRepository.save(performance);

        logger.info(
                "Performance updated successfully with ID: {}",
                id);

        return mapToResponse(updatedPerformance);
    }

    @Override
    public void deletePerformance(Long id) {

        logger.info("Deleting performance with ID: {}", id);

        Performance performance = performanceRepository
                .findById(id)
                .orElseThrow(() -> {

                    logger.error(
                            "Performance not found with ID: {}",
                            id);

                    return new ResourceNotFoundException(
                            "Performance not found");
                });

        performanceRepository.delete(performance);

        logger.info(
                "Performance deleted successfully with ID: {}",
                id);
    }

    private PerformanceResponseDTO mapToResponse(
            Performance performance) {

        PerformanceResponseDTO dto =
                new PerformanceResponseDTO();

        dto.setPerformanceId(
                performance.getPerformanceId());

        dto.setEmployeeName(
                performance.getEmployee().getFirstname()
                + " "
                + performance.getEmployee().getLastname());

        dto.setReviewPeriod(
                performance.getReviewPeriod());

        dto.setRating(
                performance.getRating());

        dto.setFeedback(
                performance.getFeedback());

        dto.setGoals(
                performance.getGoals());

        return dto;
    }
}