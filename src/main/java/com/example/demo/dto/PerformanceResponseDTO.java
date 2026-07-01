package com.example.demo.dto;

public class PerformanceResponseDTO {

    private Long performanceId;
    private String employeeName;
    private String reviewPeriod;
    private Double rating;
    private String feedback;
    private String goals;

    public PerformanceResponseDTO() {
    }

    public PerformanceResponseDTO(Long performanceId, String employeeName,
            String reviewPeriod, Double rating,
            String feedback, String goals) {
        this.performanceId = performanceId;
        this.employeeName = employeeName;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(long l) {
        this.employeeName=employeeName;
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