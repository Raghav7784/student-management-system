package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PerformanceRequestDTO;
import com.example.demo.dto.PerformanceResponseDTO;
import com.example.demo.service.PerformanceService;

@RestController
@RequestMapping("/api/performances")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @PostMapping
    public ResponseEntity<PerformanceResponseDTO> addPerformance(
            @RequestBody PerformanceRequestDTO performanceRequestDTO) {

        return new ResponseEntity<>(
                performanceService.addPerformance(performanceRequestDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceResponseDTO> getPerformanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                performanceService.getPerformanceById(id));
    }

    @GetMapping
    public ResponseEntity<List<PerformanceResponseDTO>> getAllPerformances() {

        return ResponseEntity.ok(
                performanceService.getAllPerformances());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceResponseDTO> updatePerformance(
            @PathVariable Long id,
            @RequestBody PerformanceRequestDTO performanceRequestDTO) {

        return ResponseEntity.ok(
                performanceService.updatePerformance(id,
                        performanceRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerformance(
            @PathVariable Long id) {

        performanceService.deletePerformance(id);

        return ResponseEntity.ok("Performance deleted successfully");
    }
}