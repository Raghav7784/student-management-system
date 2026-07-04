package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ProjectAllocationDTO;
import com.example.demo.dto.SkillResponseDTO;
import com.example.demo.dto.UtilizationDTO;
import com.example.demo.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(
            ReportService reportService) {

        this.reportService =
                reportService;
    }

    @GetMapping("/skills")
    public List<SkillResponseDTO>
    getAllSkillsReport() {

        return reportService
                .getAllSkillsReport();
    }

    @GetMapping("/skills/{id}")
    public SkillResponseDTO
    getSkillReportById(
            @PathVariable Long id) {

        return reportService
                .getSkillReportById(id);
    }

    @GetMapping("/utilization")
    public List<UtilizationDTO>
    getUtilizationReport() {

        return reportService
                .getUtilizationReport();
    }

    @GetMapping("/project/{projectId}")
    public List<ProjectAllocationDTO>
    getProjectAllocationReport(
            @PathVariable Long projectId) {

        return reportService
                .getProjectAllocationReport(
                        projectId);
    }
}