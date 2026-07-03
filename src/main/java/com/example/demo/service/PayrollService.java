package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PayrollRequestDTO;
import com.example.demo.dto.PayrollResponseDTO;

public interface PayrollService {

    PayrollResponseDTO addPayroll(PayrollRequestDTO payrollRequestDTO);

    PayrollResponseDTO getPayrollById(Long payrollId);

    List<PayrollResponseDTO> getAllPayrolls();

    PayrollResponseDTO updatePayroll(Long payrollId,
                                     PayrollRequestDTO payrollRequestDTO);

    void deletePayroll(Long payrollId);
}