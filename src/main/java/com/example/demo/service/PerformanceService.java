package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PerformanceRequestDTO;
import com.example.demo.dto.PerformanceResponseDTO;

public interface PerformanceService {

    PerformanceResponseDTO addPerformance(PerformanceRequestDTO performanceRequestDTO);

    PerformanceResponseDTO getPerformanceById(Long id);

    List<PerformanceResponseDTO> getAllPerformances();

    PerformanceResponseDTO updatePerformance(Long id,
            PerformanceRequestDTO performanceRequestDTO);

    void deletePerformance(Long id);

}