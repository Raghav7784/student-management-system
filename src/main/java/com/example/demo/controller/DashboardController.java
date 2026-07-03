package com.example.demo.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UtilizationDTO;
import com.example.demo.service.DashboardService;

@PreAuthorize(
	    "hasRole('ADMIN') or hasRole('MANAGER')"
	)
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/utilization")
    public List<UtilizationDTO>
    getUtilizationReport() {

        return dashboardService
                .getUtilizationReport();
    }
}