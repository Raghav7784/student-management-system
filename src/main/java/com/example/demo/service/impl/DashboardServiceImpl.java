package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UtilizationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    DashboardServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final AllocationRepository allocationRepository;

    public DashboardServiceImpl(
            EmployeeRepository employeeRepository,
            AllocationRepository allocationRepository) {

        this.employeeRepository = employeeRepository;
        this.allocationRepository = allocationRepository;
    }

    @Override
    public List<UtilizationDTO> getUtilizationReport() {

        logger.info("Generating employee utilization report");

        List<UtilizationDTO> result = new ArrayList<>();

        List<Employee> employees =
                employeeRepository.findAll();

        List<Allocation> allocations =
                allocationRepository.findAll();

        for (Employee employee : employees) {

            int billable = allocations.stream()
                    .filter(a ->
                            a.getEmployee()
                             .getEmployeeId()
                             .equals(employee.getEmployeeId()))
                    .mapToInt(
                            Allocation::getAllocationPercentage)
                    .sum();

            int bench = 100 - billable;

            if (bench < 0) {
                bench = 0;
            }

            String employeeName =
                    employee.getFirstname()
                    + " "
                    + employee.getLastname();

            result.add(
                    new UtilizationDTO(
                            employeeName,
                            billable,
                            bench
                    )
            );
        }

        logger.info(
                "Generated utilization report for {} employees",
                result.size());

        return result;
    }
}