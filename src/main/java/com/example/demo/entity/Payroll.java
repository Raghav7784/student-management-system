package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "payrolls")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payrollId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private Double basicSalary;

    @Column(nullable = false)
    private Double bonus;

    @Column(nullable = false)
    private Double deductions;

    @Column(nullable = false)
    private Double netSalary;

    @Column(nullable = false)
    private LocalDate paymentDate;

    public Payroll() {
    }

    public Payroll(Long payrollId, Employee employee, Double basicSalary,
                   Double bonus, Double deductions,
                   Double netSalary, LocalDate paymentDate) {
        this.payrollId = payrollId;
        this.employee = employee;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.paymentDate = paymentDate;
    }

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getDeductions() {
        return deductions;
    }

    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}