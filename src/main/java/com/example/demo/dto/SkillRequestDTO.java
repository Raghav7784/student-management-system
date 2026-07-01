package com.example.demo.dto;

public class SkillRequestDTO {
	
	private String skillName;
	private String category;
	private String description;
	
	public SkillRequestDTO() {
		
	}

	public SkillRequestDTO(String skillName, String category, String description) {
		super();
		this.skillName = skillName;
		this.category = category;
		this.description = description;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
