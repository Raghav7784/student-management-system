package com.example.demo.dto;

public class EmployeeSkillRequestDTO {

    private Long employeeId;
    private Long skillId;
    private String skillLevel;
    private Integer yearsOfExperience;

    public EmployeeSkillRequestDTO() {
    }

    public EmployeeSkillRequestDTO(Long employeeId, Long skillId,
                                   String skillLevel, Integer yearsOfExperience) {
        this.employeeId = employeeId;
        this.skillId = skillId;
        this.skillLevel = skillLevel;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
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