package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto) {

        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());

        Department saved = repository.save(department);

        return mapToDTO(saved);
    }

    @Override
    public DepartmentResponseDTO getDepartmentById(Long id) {

        Department department = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return mapToDTO(department);
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO dto) {

        Department department = repository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        department.setDepartmentName(dto.getDepartmentName());
        department.setDescription(dto.getDescription());

        return mapToDTO(repository.save(department));
    }

    @Override
    public void deleteDepartment(Long id) {

        repository.deleteById(id);
    }

    private DepartmentResponseDTO mapToDTO(Department department) {

        DepartmentResponseDTO dto = new DepartmentResponseDTO();

        dto.setDepartmentId(department.getDepartmentId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDescription(department.getDescription());

        return dto;
    }
}