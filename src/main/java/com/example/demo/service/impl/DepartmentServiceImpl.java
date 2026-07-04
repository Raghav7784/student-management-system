package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger =
            LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {

        logger.info("Creating department: {}",
                dto.getDepartmentName());

        Department department = new Department();

        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());

        Department saved = repository.save(department);

        logger.info("Department created successfully with ID: {}",
                saved.getDepartmentId());

        return mapToDTO(saved);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {

        logger.info("Fetching department with ID: {}", id);

        Department department = repository.findById(id)
                .orElseThrow(() -> {

                    logger.error("Department not found with ID: {}", id);

                    return new ResourceNotFoundException(
                            "Department not found");
                });

        return mapToDTO(department);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {

        logger.info("Fetching all departments");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO updateDepartment(
            Long id,
            DepartmentRequestDTO dto) {

        logger.info("Updating department with ID: {}", id);

        Department department = repository.findById(id)
                .orElseThrow(() -> {

                    logger.error("Department not found with ID: {}", id);

                    return new ResourceNotFoundException(
                            "Department not found");
                });

        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());

        Department updatedDepartment =
                repository.save(department);

        logger.info("Department updated successfully: {}",
                updatedDepartment.getDepartmentName());

        return mapToDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        logger.info("Deleting department with ID: {}", id);

        Department department = repository.findById(id)
                .orElseThrow(() -> {

                    logger.error("Department not found with ID: {}", id);

                    return new ResourceNotFoundException(
                            "Department not found");
                });

        repository.delete(department);

        logger.info("Department deleted successfully with ID: {}",
                id);
    }

    private DepartmentResponseDTO mapToDTO(Department department) {

        DepartmentResponseDTO dto = new DepartmentResponseDTO();

        dto.setDepartmentId(department.getDepartmentId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDescription(department.getDescription());

        return dto;
    }
}