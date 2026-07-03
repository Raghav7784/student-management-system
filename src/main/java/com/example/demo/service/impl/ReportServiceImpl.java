package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeSkill;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AllocationRepository allocationRepository;

    @Override
    public Map<String, List<String>> getSkillReport() {

        Map<String, List<String>> report = new HashMap<>();

        List<EmployeeSkill> skills =
                employeeSkillRepository.findAll();

        for (EmployeeSkill es : skills) {

            String skillName =
                    es.getSkill().getSkillName();

            String employeeName =
                    es.getEmployee().getLastname()
                    + " "
                    + es.getEmployee().getLastname();

            report.computeIfAbsent(
                    skillName,
                    k -> new ArrayList<>())
                    .add(employeeName);
        }

        return report;
    }

    @Override
    public Map<String, Integer> getUtilizationReport() {

        Map<String, Integer> report = new HashMap<>();

        List<Employee> employees = employeeRepository.findAll();

        List<Allocation> allocations = allocationRepository.findAll();

        for (Employee employee : employees) {

            int totalAllocation = allocations.stream()
                    .filter(a ->
                            a.getEmployee() != null &&
                            a.getEmployee().getEmployeeId() == employee.getEmployeeId())
                    .mapToInt(Allocation::getAllocationPercentage)
                    .sum();

            String employeeName =
                    employee.getLastname() + " " + employee.getFirstname();

            report.put(employeeName, totalAllocation);
        }

        return report;
    }

    @Override
    public Map<String, List<String>>
    getProjectAllocationReport() {

        Map<String, List<String>> report =
                new HashMap<>();

        List<Allocation> allocations =
                allocationRepository.findAll();

        for (Allocation allocation : allocations) {

            String projectName =
                    allocation.getProject()
                            .getProjectName();

            String employeeName =
                    allocation.getEmployee()
                            .getLastname()
                    + " "
                    + allocation.getEmployee()
                            .getLastname();

            report.computeIfAbsent(
                    projectName,
                    k -> new ArrayList<>())
                    .add(employeeName);
        }

        return report;
    }
}