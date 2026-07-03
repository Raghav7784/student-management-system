package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaveType;

    private String reason;

    private String status;

    public Leave() {
    }

    public Leave(Long leaveId, Employee employee, LocalDate startDate,
                 LocalDate endDate, String leaveType,
                 String reason, String status) {

        this.leaveId = leaveId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.reason = reason;
        this.status = status;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}