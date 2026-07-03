package com.example.demo.dto;

public class DashboardResponseDTO {

    private long totalEmployees;
    private long totalDepartments;
    private long totalSkills;
    private long totalAttendance;
    private long totalPayroll;

    public DashboardResponseDTO() {
    }

    public DashboardResponseDTO(long totalEmployees,
                                long totalDepartments,
                                long totalSkills,
                                long totalAttendance,
                                long totalPayroll) {

        this.totalEmployees = totalEmployees;
        this.totalDepartments = totalDepartments;
        this.totalSkills = totalSkills;
        this.totalAttendance = totalAttendance;
        this.totalPayroll = totalPayroll;
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

    public long getTotalSkills() {
        return totalSkills;
    }

    public void setTotalSkills(long totalSkills) {
        this.totalSkills = totalSkills;
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
}