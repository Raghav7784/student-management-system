package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CertificationRequestDTO;
import com.example.demo.dto.CertificationResponseDTO;

public interface CertificationService {

    CertificationResponseDTO addCertification(
            CertificationRequestDTO dto);

    CertificationResponseDTO getCertificationById(
            Long certificationId);

    List<CertificationResponseDTO> getAllCertifications();

    CertificationResponseDTO updateCertification(
            Long certificationId,
            CertificationRequestDTO dto);

    void deleteCertification(Long certificationId);
}