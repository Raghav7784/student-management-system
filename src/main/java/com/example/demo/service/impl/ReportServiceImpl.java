package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectAllocationDTO;
import com.example.demo.dto.SkillResponseDTO;
import com.example.demo.dto.UtilizationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Skill;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.ReportService;

@Service
public class ReportServiceImpl
        implements ReportService {

    private final SkillRepository skillRepository;
    private final EmployeeRepository employeeRepository;
    private final AllocationRepository allocationRepository;

    public ReportServiceImpl(
            SkillRepository skillRepository,
            EmployeeRepository employeeRepository,
            AllocationRepository allocationRepository) {

        this.skillRepository = skillRepository;
        this.employeeRepository = employeeRepository;
        this.allocationRepository = allocationRepository;
    }

    @Override
    public List<SkillResponseDTO>
    getAllSkillsReport() {

        return skillRepository.findAll()
                .stream()
                .map(this::mapSkillToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SkillResponseDTO
    getSkillReportById(Long skillId) {

        Skill skill =
                skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Skill not found"));

        return mapSkillToDTO(skill);
    }

    @Override
    public List<UtilizationDTO>
    getUtilizationReport() {

        List<UtilizationDTO> result =
                new ArrayList<>();

        List<Employee> employees =
                employeeRepository.findAll();

        List<Allocation> allocations =
                allocationRepository.findAll();

        for (Employee employee : employees) {

            int billable =
                    allocations.stream()
                    .filter(a ->
                            a.getEmployee()
                            .getEmployeeId()
                            .equals(
                                    employee
                                    .getEmployeeId()))
                    .mapToInt(
                            Allocation::
                            getAllocationPercentage)
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

        return result;
    }

    @Override
    public List<ProjectAllocationDTO>
    getProjectAllocationReport(
            Long projectId) {

        List<Allocation> allocations =
                allocationRepository
                .findByProjectProjectId(
                        projectId);

        return allocations.stream()
                .map(allocation -> {

                    String employeeName =
                            allocation
                            .getEmployee()
                            .getFirstname()
                            + " "
                            + allocation
                            .getEmployee()
                            .getLastname();

                    String projectName =
                            allocation
                            .getProject()
                            .getProjectName();

                    return new ProjectAllocationDTO(
                            employeeName,
                            projectName,
                            allocation
                            .getAllocationPercentage()
                    );

                }).collect(Collectors.toList());
    }

    private SkillResponseDTO
    mapSkillToDTO(Skill skill) {

        SkillResponseDTO dto =
                new SkillResponseDTO();

        dto.setSkillId(
                skill.getSkillId());

        dto.setSkillName(
                skill.getSkillName());

        dto.setCategory(
                skill.getCategory());

        dto.setDescription(
                skill.getDescription());

        return dto;
    }
}