package com.example.demo.dto;

public class EmployeeResponseDTO {
	
	private Long employeeId;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String department;
	
	private Integer experience;
	
	private boolean availability;
	
	public EmployeeResponseDTO() {
		
	}

	public EmployeeResponseDTO(Long employeeId, String firstName, String lastName, String phone, String department,
			Integer experience, boolean availability) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.department = department;
		this.experience = experience;
		this.availability = availability;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	
	
	

}
