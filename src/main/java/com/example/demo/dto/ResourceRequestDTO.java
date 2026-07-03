package com.example.demo.dto;

public class ResourceRequestDTO {

    private Long projectId;
    private String requiredSkill;
    private Integer requiredCount;
    private String status;

    public ResourceRequestDTO() {
    }

    public ResourceRequestDTO(Long projectId, String requiredSkill,
                              Integer requiredCount, String status) {
        this.projectId = projectId;
        this.requiredSkill = requiredSkill;
        this.requiredCount = requiredCount;
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public Integer getRequiredCount() {
        return requiredCount;
    }

    public void setRequiredCount(Integer requiredCount) {
        this.requiredCount = requiredCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}