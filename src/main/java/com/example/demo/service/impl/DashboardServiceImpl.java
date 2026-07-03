package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UtilizationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AllocationRepository allocationRepository;

    @Override
    public List<UtilizationDTO> getUtilizationReport() {

        List<UtilizationDTO> result = new ArrayList<>();

        List<Employee> employees = employeeRepository.findAll();
        List<Allocation> allocations = allocationRepository.findAll();

        for (Employee employee : employees) {

            int billable = allocations.stream()
                    .filter(a -> a.getEmployee().getEmployeeId()
                            == employee.getEmployeeId())
                    .mapToInt(Allocation::getAllocationPercentage)
                    .sum();

            int bench = 100 - billable;

            if (bench < 0) {
                bench = 0;
            }

            String employeeName =
                    employee.getFirstname() + " "
                    + employee.getLastname();

            result.add(
                    new UtilizationDTO(
                            employeeName,
                            billable,
                            bench
                    )
            );
        }

        return result;
    }
}