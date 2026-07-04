package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AllocationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.exception.AllocationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.AllocationService;
import com.example.demo.service.AuditLogService;

@Service
public class AllocationServiceImpl implements AllocationService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    AllocationServiceImpl.class);

    private final AuditLogService auditLogService;
    private final AllocationRepository allocationRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public AllocationServiceImpl(
            AuditLogService auditLogService,
            AllocationRepository allocationRepository,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {

        this.auditLogService = auditLogService;
        this.allocationRepository = allocationRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Allocation createAllocation(AllocationDTO dto) {

        Employee employee = employeeRepository
                .findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"));

        Project project = projectRepository
                .findById(dto.getProjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"));

        List<Allocation> existingAllocations =
                allocationRepository
                        .findByEmployeeEmployeeId(
                                dto.getEmployeeId());

        int totalAllocation = existingAllocations
                .stream()
                .mapToInt(Allocation::getAllocationPercentage)
                .sum();

        if (totalAllocation
                + dto.getAllocationPercentage() > 100) {

            logger.warn(
                    "Allocation exceeded for employee {}",
                    employee.getEmployeeId());

            throw new AllocationException(
                    "Employee allocation cannot exceed 100%");
        }

        Allocation allocation = new Allocation();

        allocation.setEmployee(employee);
        allocation.setProject(project);
        allocation.setAllocationPercentage(
                dto.getAllocationPercentage());

        allocation.setAllocationDate(
                dto.getAllocationDate());

        allocation.setReleaseDate(
                dto.getReleaseDate());

        employee.setAvailability(false);
        employeeRepository.save(employee);

        Allocation savedAllocation =
                allocationRepository.save(allocation);

        logger.info(
                "Employee {} allocated {}% to project {}",
                employee.getEmployeeId(),
                dto.getAllocationPercentage(),
                project.getProjectId());

        auditLogService.saveLog(
                "Employee allocated to project",
                "SYSTEM");

        return savedAllocation;
    }

    @Override
    public List<Allocation> getAllAllocations() {

        return allocationRepository.findAll();
    }

    @Override
    public Allocation getAllocationById(Long id) {

        return allocationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Allocation not found"));
    }

    @Override
    public Allocation releaseEmployee(Long allocationId) {

        Allocation allocation = allocationRepository
                .findById(allocationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Allocation not found"));

        allocation.setReleaseDate(LocalDate.now());

        Employee employee = allocation.getEmployee();

        employee.setAvailability(true);

        employeeRepository.save(employee);

        Allocation updatedAllocation =
                allocationRepository.save(allocation);

        logger.info(
                "Employee {} released from project {}",
                employee.getEmployeeId(),
                allocation.getProject().getProjectId());

        auditLogService.saveLog(
                "Employee released from project",
                "SYSTEM");

        return updatedAllocation;
    }

    @Override
    public Allocation reallocateEmployee(
            Long allocationId,
            Long newProjectId,
            Integer allocationPercentage) {

        Allocation allocation = allocationRepository
                .findById(allocationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Allocation not found"));

        Employee employee = allocation.getEmployee();

        Project newProject = projectRepository
                .findById(newProjectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project not found"));

        List<Allocation> allocations =
                allocationRepository.findByEmployeeEmployeeId(
                        employee.getEmployeeId());

        int currentTotal = allocations.stream()
                .filter(a ->
                        !a.getAllocationId()
                                .equals(allocationId))
                .mapToInt(
                        Allocation::getAllocationPercentage)
                .sum();

        if (currentTotal + allocationPercentage > 100) {

            throw new AllocationException(
                    "Employee allocation cannot exceed 100%");
        }

        allocation.setProject(newProject);

        allocation.setAllocationPercentage(
                allocationPercentage);

        allocation.setAllocationDate(
                LocalDate.now());

        allocation.setReleaseDate(null);

        employee.setAvailability(false);

        employeeRepository.save(employee);

        Allocation updated =
                allocationRepository.save(allocation);

        logger.info(
                "Employee {} reallocated to project {}",
                employee.getEmployeeId(),
                newProject.getProjectId());

        auditLogService.saveLog(
                "Employee reallocated to another project",
                "SYSTEM");

        return updated;
    }

    @Override
    public void deleteAllocation(Long id) {

        Allocation allocation = allocationRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Allocation not found"));

        allocationRepository.delete(allocation);

        logger.info(
                "Allocation deleted: {}",
                id);

        auditLogService.saveLog(
                "Allocation deleted",
                "SYSTEM");
    }
}