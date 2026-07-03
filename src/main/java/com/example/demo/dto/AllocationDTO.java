package com.example.demo.dto;

import java.time.LocalDate;

public class AllocationDTO {

    private Long employeeId;
    private Long projectId;
    private Integer allocationPercentage;
    private LocalDate allocationDate;
    private LocalDate releaseDate;

    public AllocationDTO() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getAllocationPercentage() {
        return allocationPercentage;
    }

    public void setAllocationPercentage(Integer allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(LocalDate allocationDate) {
        this.allocationDate = allocationDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}