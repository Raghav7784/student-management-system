package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CertificationRequestDTO;
import com.example.demo.dto.CertificationResponseDTO;
import com.example.demo.service.CertificationService;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(
            CertificationService certificationService) {

        this.certificationService = certificationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CertificationResponseDTO addCertification(
            @RequestBody CertificationRequestDTO dto) {

        return certificationService.addCertification(dto);
    }

    @GetMapping("/{id}")
    public CertificationResponseDTO getCertificationById(
            @PathVariable Long id) {

        return certificationService.getCertificationById(id);
    }

    @GetMapping
    public List<CertificationResponseDTO> getAllCertifications() {

        return certificationService.getAllCertifications();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CertificationResponseDTO updateCertification(
            @PathVariable Long id,
            @RequestBody CertificationRequestDTO dto) {

        return certificationService
                .updateCertification(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCertification(
            @PathVariable Long id) {

        certificationService.deleteCertification(id);
    }
}