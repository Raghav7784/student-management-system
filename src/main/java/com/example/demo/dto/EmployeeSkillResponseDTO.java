package com.example.demo.dto;

public class EmployeeSkillResponseDTO {

    private Long employeeSkillId;
    private String employeeName;
    private String skillName;
    private String skillLevel;
    private Integer yearsOfExperience;

    public EmployeeSkillResponseDTO() {
    }

    public EmployeeSkillResponseDTO(Long employeeSkillId,
                                    String employeeName,
                                    String skillName,
                                    String skillLevel,
                                    Integer yearsOfExperience) {
        this.employeeSkillId = employeeSkillId;
        this.employeeName = employeeName;
        this.skillName = skillName;
        this.skillLevel = skillLevel;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Long getEmployeeSkillId() {
        return employeeSkillId;
    }

    public void setEmployeeSkillId(Long employeeSkillId) {
        this.employeeSkillId = employeeSkillId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}