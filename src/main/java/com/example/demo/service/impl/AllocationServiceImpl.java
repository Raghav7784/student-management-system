package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dto.AllocationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.exception.AllocationException;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.AllocationService;
import com.example.demo.service.AuditLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationServiceImpl implements AllocationService {
	
	private static final Logger logger =
	        LoggerFactory.getLogger(
	                AllocationServiceImpl.class);
	@Autowired
	private AuditLogService auditLogService;

    @Autowired
    private AllocationRepository allocationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Allocation createAllocation(AllocationDTO dto) {
    	
    	AllocationDTO project = null;
		AllocationDTO employee = null;
		logger.info(
    	        "Employee {} allocated {}% to project {}",
    	        employee.getEmployeeId(),
    	        dto.getAllocationPercentage(),
    	        project.getProjectId());

        Employee employee1 = employeeRepository
                .findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        Project project1 = projectRepository
                .findById(dto.getProjectId())
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        List<Allocation> existingAllocations =
                allocationRepository
                        .findByEmployeeEmployeeId(
                                dto.getEmployeeId());

        int totalAllocation = existingAllocations
                .stream()
                .mapToInt(Allocation::getAllocationPercentage)
                .sum();

        if (totalAllocation +
                dto.getAllocationPercentage() > 100) {

        	logger.warn(
        	        "Allocation exceeded for employee {}",
        	        employee1.getEmployeeId());

        	throw new AllocationException(
        	        "Employee allocation cannot exceed 100%");
        }

        Allocation allocation = new Allocation();

        allocation.setEmployee(employee1);
        allocation.setProject(project1);
        allocation.setAllocationPercentage(
                dto.getAllocationPercentage());

        allocation.setAllocationDate(
                dto.getAllocationDate());

        allocation.setReleaseDate(
                dto.getReleaseDate());

        return allocationRepository.save(allocation);
    }

    @Override
    public List<Allocation> getAllAllocations() {

        return allocationRepository.findAll();
    }

    @Override
    public Allocation getAllocationById(Long id) {

        return allocationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Allocation not found"));
    }

    @Override
    public void deleteAllocation(Long id) {

        allocationRepository.deleteById(id);
    }
    
    
}