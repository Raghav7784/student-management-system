package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="allocations")
public class Allocation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long allocationId;

    private Integer allocationPercentage;

    private LocalDate allocationDate;

    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;

    public Allocation() {
    }

	public Allocation(Long allocationId, Integer allocationPercentage, LocalDate allocationDate, LocalDate releaseDate,
			Employee employee, Project project) {
		super();
		this.allocationId = allocationId;
		this.allocationPercentage = allocationPercentage;
		this.allocationDate = allocationDate;
		this.releaseDate = releaseDate;
		this.employee = employee;
		this.project = project;
	}

	public Long getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(Long allocationId) {
		this.allocationId = allocationId;
	}

	public Integer getAllocationPercentage() {
		return allocationPercentage;
	}

	public void setAllocationPercentage(Integer allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}

	public LocalDate getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(LocalDate allocationDate) {
		this.allocationDate = allocationDate;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
    
    
}