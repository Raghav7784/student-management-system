package com.example.demo.dto;

public class ProjectResponseDTO {
	
	private Long projectId;
	private String projectName;
	private String clientName;
	private String description;
	private String startDate;
	private String endDate;
	private String status;
	private String technologyStack;
	
	public ProjectResponseDTO() {
		
	}

	public ProjectResponseDTO(Long projectId, String projectName, String clientName, String description,
			String startDate, String endDate, String status, String technologyStack) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.clientName = clientName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.technologyStack = technologyStack;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTechnologyStack() {
		return technologyStack;
	}

	public void setTechnologyStack(String technologyStack) {
		this.technologyStack = technologyStack;
	}

	public void setBudget(Double budget) {
		// TODO Auto-generated method stub
		
	}
	

}
