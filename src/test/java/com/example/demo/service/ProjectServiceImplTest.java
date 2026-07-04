package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import com.example.demo.dto.ProjectRequestDTO;
import com.example.demo.dto.ProjectResponseDTO;
import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.impl.ProjectServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;
    private ProjectRequestDTO requestDTO;

    @BeforeEach
    void setUp() {

        project = new Project();

        project.setProjectId(1L);
        project.setProjectName("Employee Management System");
        project.setClientName("ABC Technologies");
        project.setDescription("Resource Management Project");
        project.setStartDate("2026-01-01");
        project.setEndDate("2026-12-31");
        project.setStatus("ACTIVE");
        project.setTechnologyStack("Java, Spring Boot, MySQL");
        project.setBudget(500000.0);

        requestDTO = new ProjectRequestDTO();

        requestDTO.setProjectName(
                "Employee Management System");

        requestDTO.setClientName(
                "ABC Technologies");

        requestDTO.setDescription(
                "Resource Management Project");

        requestDTO.setStartDate(
                "2026-01-01");

        requestDTO.setEndDate(
                "2026-12-31");

        requestDTO.setStatus(
                "ACTIVE");

        requestDTO.setTechnologyStack(
                "Java, Spring Boot, MySQL");

        requestDTO.setBudget(500000.0);
    }

    @Test
    void testAddProjectSuccess() {

        when(projectRepository.findByProjectName(
                requestDTO.getProjectName()))
                .thenReturn(Optional.empty());

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectResponseDTO response =
                projectService.addProject(requestDTO);

        assertNotNull(response);

        assertEquals(
                "Employee Management System",
                response.getProjectName());

        verify(projectRepository)
                .save(any(Project.class));
    }

    @Test
    void testAddProjectAlreadyExists() {

        when(projectRepository.findByProjectName(
                requestDTO.getProjectName()))
                .thenReturn(Optional.of(project));

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> projectService.addProject(
                                requestDTO));

        assertEquals(
                "Project already exists",
                exception.getMessage());
    }

    @Test
    void testGetProjectByIdSuccess() {

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        ProjectResponseDTO response =
                projectService.getProjectById(1L);

        assertNotNull(response);

        assertEquals(
                "Employee Management System",
                response.getProjectName());
    }

    @Test
    void testGetProjectByIdNotFound() {

        when(projectRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> projectService.getProjectById(1L));

        assertEquals(
                "Project not found",
                exception.getMessage());
    }

    @Test
    void testGetAllProjects() {

        when(projectRepository.findAll())
                .thenReturn(Arrays.asList(project));

        assertEquals(
                1,
                projectService.getAllProjects().size());
    }

    @Test
    void testUpdateProjectSuccess() {

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectResponseDTO response =
                projectService.updateProject(
                        1L,
                        requestDTO);

        assertNotNull(response);

        assertEquals(
                "Employee Management System",
                response.getProjectName());

        verify(projectRepository)
                .save(any(Project.class));
    }

    @Test
    void testDeleteProjectSuccess() {

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        projectService.deleteProject(1L);

        verify(projectRepository)
                .delete(project);
    }
}