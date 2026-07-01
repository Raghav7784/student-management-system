package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PerformanceRequestDTO;
import com.example.demo.dto.PerformanceResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Performance;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PerformanceRepository;
import com.example.demo.service.PerformanceService;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final EmployeeRepository employeeRepository;

    public PerformanceServiceImpl(PerformanceRepository performanceRepository,
                                  EmployeeRepository employeeRepository) {
        this.performanceRepository = performanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public PerformanceResponseDTO addPerformance(PerformanceRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Performance performance = new Performance();

        performance.setEmployee(employee);
        performance.setReviewPeriod(dto.getReviewPeriod());
        performance.setRating(dto.getRating());
        performance.setFeedback(dto.getFeedback());
        performance.setGoals(dto.getGoals());

        Performance savedPerformance = performanceRepository.save(performance);

        return mapToResponse(savedPerformance);
    }

    @Override
    public PerformanceResponseDTO getPerformanceById(Long id) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance not found"));

        return mapToResponse(performance);
    }

    @Override
    public List<PerformanceResponseDTO> getAllPerformances() {

        return performanceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PerformanceResponseDTO updatePerformance(Long id,
            PerformanceRequestDTO dto) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance not found"));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        performance.setEmployee(employee);
        performance.setReviewPeriod(dto.getReviewPeriod());
        performance.setRating(dto.getRating());
        performance.setFeedback(dto.getFeedback());
        performance.setGoals(dto.getGoals());

        Performance updatedPerformance = performanceRepository.save(performance);

        return mapToResponse(updatedPerformance);
    }

    @Override
    public void deletePerformance(Long id) {

        Performance performance = performanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance not found"));

        performanceRepository.delete(performance);
    }

    private PerformanceResponseDTO mapToResponse(Performance performance) {

        PerformanceResponseDTO dto = new PerformanceResponseDTO();

        dto.setPerformanceId(performance.getPerformanceId());
        dto.setEmployeeName(performance.getEmployee().getEmployeeId());
        dto.setReviewPeriod(performance.getReviewPeriod());
        dto.setRating(performance.getRating());
        dto.setFeedback(performance.getFeedback());
        dto.setGoals(performance.getGoals());

        return dto;
    }

}