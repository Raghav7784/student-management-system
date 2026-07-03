package com.example.demo.dto;

public class DepartmentRequestDTO {

    private String departmentName;
    private String description;

    public DepartmentRequestDTO() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}