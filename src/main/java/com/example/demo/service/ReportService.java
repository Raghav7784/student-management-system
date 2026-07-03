package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface ReportService {

    Map<String, List<String>> getSkillReport();

    Map<String, Integer> getUtilizationReport();

    Map<String, List<String>> getProjectAllocationReport();

}