package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CertificationRequestDTO;
import com.example.demo.dto.CertificationResponseDTO;
import com.example.demo.entity.Certification;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CertificationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.CertificationService;

@Service
public class CertificationServiceImpl implements CertificationService {

    private static final Logger logger =
            LoggerFactory.getLogger(CertificationServiceImpl.class);

    private final CertificationRepository certificationRepository;
    private final EmployeeRepository employeeRepository;

    public CertificationServiceImpl(
            CertificationRepository certificationRepository,
            EmployeeRepository employeeRepository) {

        this.certificationRepository = certificationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public CertificationResponseDTO addCertification(
            CertificationRequestDTO dto) {

        logger.info(
                "Adding certification for Employee ID: {}",
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

        Certification certification = new Certification();

        certification.setEmployee(employee);
        certification.setCertificationName(
                dto.getCertificationName());
        certification.setIssuedBy(dto.getIssuedBy());
        certification.setIssueDate(dto.getIssueDate());
        certification.setExpiryDate(dto.getExpiryDate());

        Certification saved =
                certificationRepository.save(certification);

        logger.info(
                "Certification added successfully: {}",
                saved.getCertificationName());

        return mapToDTO(saved);
    }

    @Override
    public CertificationResponseDTO getCertificationById(
            Long certificationId) {

        logger.info(
                "Fetching certification with ID: {}",
                certificationId);

        Certification certification =
                certificationRepository.findById(certificationId)
                .orElseThrow(() -> {

                    logger.error(
                            "Certification not found with ID: {}",
                            certificationId);

                    return new ResourceNotFoundException(
                            "Certification not found");
                });

        return mapToDTO(certification);
    }

    @Override
    public List<CertificationResponseDTO>
            getAllCertifications() {

        logger.info("Fetching all certifications");

        return certificationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CertificationResponseDTO updateCertification(
            Long certificationId,
            CertificationRequestDTO dto) {

        logger.info(
                "Updating certification with ID: {}",
                certificationId);

        Certification certification =
                certificationRepository.findById(certificationId)
                .orElseThrow(() -> {

                    logger.error(
                            "Certification not found with ID: {}",
                            certificationId);

                    return new ResourceNotFoundException(
                            "Certification not found");
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

        certification.setEmployee(employee);
        certification.setCertificationName(
                dto.getCertificationName());
        certification.setIssuedBy(dto.getIssuedBy());
        certification.setIssueDate(dto.getIssueDate());
        certification.setExpiryDate(dto.getExpiryDate());

        Certification updated =
                certificationRepository.save(certification);

        logger.info(
                "Certification updated successfully: {}",
                updated.getCertificationName());

        return mapToDTO(updated);
    }

    @Override
    public void deleteCertification(Long certificationId) {

        logger.info(
                "Deleting certification with ID: {}",
                certificationId);

        Certification certification =
                certificationRepository.findById(certificationId)
                .orElseThrow(() -> {

                    logger.error(
                            "Certification not found with ID: {}",
                            certificationId);

                    return new ResourceNotFoundException(
                            "Certification not found");
                });

        certificationRepository.delete(certification);

        logger.info(
                "Certification deleted successfully with ID: {}",
                certificationId);
    }

    private CertificationResponseDTO
            mapToDTO(Certification certification) {

        CertificationResponseDTO dto =
                new CertificationResponseDTO();

        dto.setCertificationId(
                certification.getCertificationId());

        dto.setEmployeeName(
                certification.getEmployee().getFirstname()
                + " "
                + certification.getEmployee().getLastname());

        dto.setCertificationName(
                certification.getCertificationName());

        dto.setIssuedBy(
                certification.getIssuedBy());

        dto.setIssueDate(
                certification.getIssueDate());

        dto.setExpiryDate(
                certification.getExpiryDate());

        return dto;
    }
}