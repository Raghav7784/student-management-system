package com.example.demo.dto;

public class EmployeeRequestDTO {

	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String department;
	
	private Integer experience;
	
	private Long userId;
	
	public EmployeeRequestDTO() {
		
	}

	public EmployeeRequestDTO(String firstName, String lastName, String phone, String department, Integer experience,
			Long userId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.department = department;
		this.experience = experience;
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	
}
