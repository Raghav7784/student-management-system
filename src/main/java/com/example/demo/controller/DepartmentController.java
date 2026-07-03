package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @RequestBody DepartmentRequestDTO dto) {

        return ResponseEntity.ok(service.createDepartment(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable Long id) {

        return ResponseEntity.ok(service.getDepartmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {

        return ResponseEntity.ok(service.getAllDepartments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequestDTO dto) {

        return ResponseEntity.ok(service.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {

        service.deleteDepartment(id);

        return ResponseEntity.ok("Department deleted successfully");
    }
}