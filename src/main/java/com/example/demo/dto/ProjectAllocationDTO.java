package com.example.demo.dto;

public class ProjectAllocationDTO {

    private String employeeName;
    private String projectName;
    private Integer allocationPercentage;

    public ProjectAllocationDTO() {
    }

    public ProjectAllocationDTO(
            String employeeName,
            String projectName,
            Integer allocationPercentage) {

        this.employeeName = employeeName;
        this.projectName = projectName;
        this.allocationPercentage = allocationPercentage;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getAllocationPercentage() {
        return allocationPercentage;
    }

    public void setAllocationPercentage(
            Integer allocationPercentage) {

        this.allocationPercentage =
                allocationPercentage;
    }
}