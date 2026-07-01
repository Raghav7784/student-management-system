package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectRequestDTO;
import com.example.demo.dto.ProjectResponseDTO;
import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponseDTO addProject(ProjectRequestDTO projectRequestDTO) {

        if (projectRepository.findByProjectName(projectRequestDTO.getProjectName()).isPresent()) {
            throw new RuntimeException("Project already exists");
        }

        Project project = new Project();

        project.setProjectName(projectRequestDTO.getProjectName());
        project.setClientName(projectRequestDTO.getClientName());
        project.setDescription(projectRequestDTO.getDescription());
        project.setStartDate(projectRequestDTO.getStartDate());
        project.setEndDate(projectRequestDTO.getEndDate());
        project.setStatus(projectRequestDTO.getStatus());
        project.setTechnologyStack(projectRequestDTO.getTechnologyStack());

        Project savedProject = projectRepository.save(project);

        return mapToDTO(savedProject);
    }

    @Override
    public ProjectResponseDTO getProjectById(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        return mapToDTO(project);
    }

    @Override
    public List<ProjectResponseDTO> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDTO updateProject(Long projectId,
                                            ProjectRequestDTO projectRequestDTO) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setProjectName(projectRequestDTO.getProjectName());
        project.setClientName(projectRequestDTO.getClientName());
        project.setDescription(projectRequestDTO.getDescription());
        project.setStartDate(projectRequestDTO.getStartDate());
        project.setEndDate(projectRequestDTO.getEndDate());
        project.setStatus(projectRequestDTO.getStatus());
        project.setTechnologyStack(projectRequestDTO.getTechnologyStack());

        Project updatedProject = projectRepository.save(project);

        return mapToDTO(updatedProject);
    }

    @Override
    public void deleteProject(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        projectRepository.delete(project);
    }

    // Helper Method
    private ProjectResponseDTO mapToDTO(Project project) {

        ProjectResponseDTO dto = new ProjectResponseDTO();

        dto.setProjectId(project.getProjectId());
        dto.setProjectName(project.getProjectName());
        dto.setClientName(project.getClientName());
        dto.setDescription(project.getDescription());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setStatus(project.getStatus());
        dto.setTechnologyStack(project.getTechnologyStack());

        return dto;
    }
}