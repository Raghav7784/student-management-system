package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProjectRequestDTO;
import com.example.demo.dto.ProjectResponseDTO;

public interface ProjectService {
	
	ProjectResponseDTO addProject(ProjectRequestDTO projectRequestDTO);
	
	ProjectResponseDTO getProjectById(Long projectId );
	
	List<ProjectResponseDTO> getAllProjects();
	
	ProjectResponseDTO updateProject(Long projectId, ProjectRequestDTO projectRequestDTO);
	
	void deleteProject(Long projectId);

}
