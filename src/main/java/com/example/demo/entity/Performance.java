package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "performances")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private String reviewPeriod;

    @Column(nullable = false)
    private Double rating;

    private String feedback;

    private String goals;

    public Performance() {
    }

    public Performance(Long performanceId, Employee employee,
                       String reviewPeriod,
                       Double rating,
                       String feedback,
                       String goals) {
        this.performanceId = performanceId;
        this.employee = employee;
        this.reviewPeriod = reviewPeriod;
        this.rating = rating;
        this.feedback = feedback;
        this.goals = goals;
    }

    public Long getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Long performanceId) {
        this.performanceId = performanceId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getReviewPeriod() {
        return reviewPeriod;
    }

    public void setReviewPeriod(String reviewPeriod) {
        this.reviewPeriod = reviewPeriod;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}