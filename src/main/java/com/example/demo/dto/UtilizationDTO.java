package com.example.demo.dto;

public class UtilizationDTO {

    private String employeeName;

    private Integer billablePercentage;

    private Integer benchPercentage;

    public UtilizationDTO() {
    }

    public UtilizationDTO(String employeeName,
                          Integer billablePercentage,
                          Integer benchPercentage) {

        this.employeeName = employeeName;
        this.billablePercentage = billablePercentage;
        this.benchPercentage = benchPercentage;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getBillablePercentage() {
        return billablePercentage;
    }

    public void setBillablePercentage(Integer billablePercentage) {
        this.billablePercentage = billablePercentage;
    }

    public Integer getBenchPercentage() {
        return benchPercentage;
    }

    public void setBenchPercentage(Integer benchPercentage) {
        this.benchPercentage = benchPercentage;
    }
}