package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PayrollRequestDTO;
import com.example.demo.dto.PayrollResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Payroll;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PayrollRepository;
import com.example.demo.service.PayrollService;

@Service
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    public PayrollServiceImpl(PayrollRepository payrollRepository,
                              EmployeeRepository employeeRepository) {

        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public PayrollResponseDTO addPayroll(PayrollRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Payroll payroll = new Payroll();

        payroll.setEmployee(employee);
        payroll.setBasicSalary(dto.getBasicSalary());
        payroll.setBonus(dto.getBonus());
        payroll.setDeductions(dto.getDeductions());

        payroll.setNetSalary(
                dto.getBasicSalary()
                + dto.getBonus()
                - dto.getDeductions());

        payroll.setPaymentDate(dto.getPaymentDate());

        Payroll saved = payrollRepository.save(payroll);

        return mapToDTO(saved);
    }

    @Override
    public PayrollResponseDTO getPayrollById(Long payrollId) {

        Payroll payroll = payrollRepository.findById(payrollId)
        		.orElseThrow(() -> new ResourceNotFoundException("Payroll record not found"));

        return mapToDTO(payroll);
    }

    @Override
    public List<PayrollResponseDTO> getAllPayrolls() {

        return payrollRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PayrollResponseDTO updatePayroll(Long payrollId,
                                            PayrollRequestDTO dto) {

        Payroll payroll = payrollRepository.findById(payrollId)
        		.orElseThrow(() -> new ResourceNotFoundException("Payroll record not found"));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        payroll.setEmployee(employee);
        payroll.setBasicSalary(dto.getBasicSalary());
        payroll.setBonus(dto.getBonus());
        payroll.setDeductions(dto.getDeductions());

        payroll.setNetSalary(
                dto.getBasicSalary()
                + dto.getBonus()
                - dto.getDeductions());

        payroll.setPaymentDate(dto.getPaymentDate());

        Payroll updated = payrollRepository.save(payroll);

        return mapToDTO(updated);
    }

    @Override
    public void deletePayroll(Long payrollId) {

        Payroll payroll = payrollRepository.findById(payrollId)
        		.orElseThrow(() -> new ResourceNotFoundException("Payroll record not found"));

        payrollRepository.delete(payroll);
    }

    private PayrollResponseDTO mapToDTO(Payroll payroll) {

        PayrollResponseDTO dto = new PayrollResponseDTO();

        dto.setPayrollId(payroll.getPayrollId());
        dto.setEmployeeId(payroll.getEmployee().getEmployeeId());
        dto.setEmployeeName(payroll.getEmployee().getFirstname()
                + " "
                + payroll.getEmployee().getFirstname());

        dto.setBasicSalary(payroll.getBasicSalary());
        dto.setBonus(payroll.getBonus());
        dto.setDeductions(payroll.getDeductions());
        dto.setNetSalary(payroll.getNetSalary());
        dto.setPaymentDate(payroll.getPaymentDate());

        return dto;
    }
}