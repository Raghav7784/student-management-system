package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import com.example.demo.dto.AllocationDTO;
import com.example.demo.entity.Allocation;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Project;
import com.example.demo.exception.AllocationException;
import com.example.demo.repository.AllocationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.AuditLogService;
import com.example.demo.service.impl.AllocationServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AllocationServiceImplTest {

    @Mock
    private AuditLogService auditLogService;

    @Mock
    private AllocationRepository allocationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private AllocationServiceImpl allocationService;

    private Employee employee;
    private Project project;
    private Allocation allocation;
    private AllocationDTO dto;

    @BeforeEach
    void setUp() {

        Department department = new Department();
        department.setDepartmentId(1L);
        department.setDepartmentName("IT");

        employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstname("Varun");
        employee.setLastname("MR");
        employee.setDepartment(department);
        employee.setAvailability(true);

        project = new Project();
        project.setProjectId(1L);
        project.setProjectName("ERP System");

        allocation = new Allocation();
        allocation.setAllocationId(1L);
        allocation.setEmployee(employee);
        allocation.setProject(project);
        allocation.setAllocationPercentage(50);
        allocation.setAllocationDate(LocalDate.now());

        dto = new AllocationDTO();
        dto.setEmployeeId(1L);
        dto.setProjectId(1L);
        dto.setAllocationPercentage(50);
        dto.setAllocationDate(LocalDate.now());
    }

    @Test
    void testCreateAllocationSuccess() {

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(allocationRepository.findByEmployeeEmployeeId(1L))
                .thenReturn(Collections.emptyList());

        when(allocationRepository.save(any(Allocation.class)))
                .thenReturn(allocation);

        Allocation result =
                allocationService.createAllocation(dto);

        assertNotNull(result);

        assertEquals(
                50,
                result.getAllocationPercentage());

        verify(auditLogService)
                .saveLog(
                        "Employee allocated to project",
                        "SYSTEM");
    }

    @Test
    void testCreateAllocationExceeds100Percent() {

        Allocation existing = new Allocation();
        existing.setAllocationPercentage(70);

        dto.setAllocationPercentage(40);

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(allocationRepository.findByEmployeeEmployeeId(1L))
                .thenReturn(Arrays.asList(existing));

        assertThrows(
                AllocationException.class,
                () -> allocationService.createAllocation(dto));
    }

    @Test
    void testGetAllAllocations() {

        when(allocationRepository.findAll())
                .thenReturn(Arrays.asList(allocation));

        assertEquals(
                1,
                allocationService.getAllAllocations().size());
    }

    @Test
    void testGetAllocationByIdSuccess() {

        when(allocationRepository.findById(1L))
                .thenReturn(Optional.of(allocation));

        Allocation result =
                allocationService.getAllocationById(1L);

        assertEquals(
                1L,
                result.getAllocationId());
    }

    @Test
    void testReleaseEmployeeSuccess() {

        when(allocationRepository.findById(1L))
                .thenReturn(Optional.of(allocation));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        when(allocationRepository.save(any(Allocation.class)))
                .thenReturn(allocation);

        Allocation result =
                allocationService.releaseEmployee(1L);

        assertNotNull(result);

        verify(auditLogService)
                .saveLog(
                        "Employee released from project",
                        "SYSTEM");
    }

    @Test
    void testReallocateEmployeeSuccess() {

        Project newProject = new Project();
        newProject.setProjectId(2L);
        newProject.setProjectName("CRM System");

        when(allocationRepository.findById(1L))
                .thenReturn(Optional.of(allocation));

        when(projectRepository.findById(2L))
                .thenReturn(Optional.of(newProject));

        when(allocationRepository.findByEmployeeEmployeeId(1L))
                .thenReturn(Collections.singletonList(allocation));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        when(allocationRepository.save(any(Allocation.class)))
                .thenReturn(allocation);

        Allocation result =
                allocationService.reallocateEmployee(
                        1L,
                        2L,
                        60);

        assertNotNull(result);

        verify(auditLogService)
                .saveLog(
                        "Employee reallocated to another project",
                        "SYSTEM");
    }

    @Test
    void testDeleteAllocationSuccess() {

        when(allocationRepository.findById(1L))
                .thenReturn(Optional.of(allocation));

        allocationService.deleteAllocation(1L);

        verify(allocationRepository)
                .delete(allocation);

        verify(auditLogService)
                .saveLog(
                        "Allocation deleted",
                        "SYSTEM");
    }
}