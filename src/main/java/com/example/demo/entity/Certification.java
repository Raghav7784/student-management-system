package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="certifications")
public class Certification {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long certificationId;
	
	@Column(nullable=false)
	private String certificationName;
	
	@Column(nullable = false)
	private String issuedBy;
	
	private LocalDate issueDate;
	
	private LocalDate expiryDate;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public Certification() {
		
	}

	public Certification(Long certificationId, String certificationName, String issuedBy, LocalDate issueDate,
			LocalDate expiryDate, Employee employee) {
		super();
		this.certificationId = certificationId;
		this.certificationName = certificationName;
		this.issuedBy = issuedBy;
		this.issueDate = issueDate;
		this.expiryDate = expiryDate;
		this.employee = employee;
	}

	public Long getCertificationId() {
		return certificationId;
	}

	public void setCertificationId(Long certificationId) {
		this.certificationId = certificationId;
	}

	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	

}

