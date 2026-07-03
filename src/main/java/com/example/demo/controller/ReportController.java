package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.ReportService;

@PreAuthorize(
	    "hasRole('ADMIN') or hasRole('AUDITOR')"
	)
@RestController
@RequestMapping("/api/reports")
@CrossOrigin("*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/skills")
    public Map<String, List<String>>
    getSkillReport() {

        return reportService
                .getSkillReport();
    }

    @GetMapping("/utilization")
    public Map<String, Integer>
    getUtilizationReport() {

        return reportService
                .getUtilizationReport();
    }

    @GetMapping("/projects")
    public Map<String, List<String>>
    getProjectAllocationReport() {

        return reportService
                .getProjectAllocationReport();
    }
}