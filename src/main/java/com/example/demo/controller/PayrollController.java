package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PayrollRequestDTO;
import com.example.demo.dto.PayrollResponseDTO;
import com.example.demo.service.PayrollService;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PayrollResponseDTO> addPayroll(
            @RequestBody PayrollRequestDTO dto) {

        return new ResponseEntity<>(
                payrollService.addPayroll(dto),
                HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<PayrollResponseDTO> getPayroll(
            @PathVariable Long id) {

        return ResponseEntity.ok(payrollService.getPayrollById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<PayrollResponseDTO>> getAllPayrolls() {

        return ResponseEntity.ok(payrollService.getAllPayrolls());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PayrollResponseDTO> updatePayroll(
            @PathVariable Long id,
            @RequestBody PayrollRequestDTO dto) {

        return ResponseEntity.ok(
                payrollService.updatePayroll(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayroll(
            @PathVariable Long id) {

        payrollService.deletePayroll(id);

        return ResponseEntity.ok("Payroll deleted successfully");
    }
}