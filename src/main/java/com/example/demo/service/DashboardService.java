package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UtilizationDTO;

public interface DashboardService {

    List<UtilizationDTO> getUtilizationReport();

}