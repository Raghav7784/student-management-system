package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee_skills")
public class EmployeeSkill {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long employeeskillId;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="skill_id")
	private Skill skill;
	
	@Column(nullable=false)
	private String skilllevel;
	
	@Column(nullable=false)
	private Integer yearsOfExperience;
	
	public EmployeeSkill() {
		
	}

	public EmployeeSkill(Long employeeskillId, Employee employee, Skill skill, String skilllevel,
			Integer yearsOfExperience) {
		super();
		this.employeeskillId = employeeskillId;
		this.employee = employee;
		this.skill = skill;
		this.skilllevel = skilllevel;
		this.yearsOfExperience = yearsOfExperience;
	}

	public Long getEmployeeskillId() {
		return employeeskillId;
	}

	public void setEmployeeskillId(Long employeeskillId) {
		this.employeeskillId = employeeskillId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getSkilllevel() {
		return skilllevel;
	}

	public void setSkilllevel(String skilllevel) {
		this.skilllevel = skilllevel;
	}

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	

}

