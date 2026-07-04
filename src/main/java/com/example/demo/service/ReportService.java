package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProjectAllocationDTO;
import com.example.demo.dto.SkillResponseDTO;
import com.example.demo.dto.UtilizationDTO;

public interface ReportService {

    List<SkillResponseDTO> getAllSkillsReport();

    SkillResponseDTO getSkillReportById(
            Long skillId);

    List<UtilizationDTO> getUtilizationReport();

    List<ProjectAllocationDTO>
    getProjectAllocationReport(
            Long projectId);
}