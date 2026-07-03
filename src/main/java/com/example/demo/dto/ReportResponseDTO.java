package com.example.demo.dto;

public class ReportResponseDTO {

    private long totalEmployees;
    private long totalDepartments;
    private long totalAttendance;
    private long totalPayroll;
    private long totalSkills;

    public ReportResponseDTO() {
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public long getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(long totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public long getTotalPayroll() {
        return totalPayroll;
    }

    public void setTotalPayroll(long totalPayroll) {
        this.totalPayroll = totalPayroll;
    }

    public long getTotalSkills() {
        return totalSkills;
    }

    public void setTotalSkills(long totalSkills) {
        this.totalSkills = totalSkills;
    }
}