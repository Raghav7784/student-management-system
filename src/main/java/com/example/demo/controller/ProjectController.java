package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProjectRequestDTO;
import com.example.demo.dto.ProjectResponseDTO;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> addProject(
            @RequestBody ProjectRequestDTO projectRequestDTO) {

        ProjectResponseDTO responseDTO = projectService.addProject(projectRequestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(
            @PathVariable Long id) {

        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {

        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectRequestDTO projectRequestDTO) {

        return ResponseEntity.ok(
                projectService.updateProject(id, projectRequestDTO));
    }

    // Delete Project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(
            @PathVariable Long id) {

        projectService.deleteProject(id);

        return ResponseEntity.ok("Project deleted successfully");
    }

}