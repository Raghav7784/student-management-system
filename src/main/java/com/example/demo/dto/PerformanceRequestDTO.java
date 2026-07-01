package com.example.demo.dto;

public class PerformanceRequestDTO {

    private Long employeeId;
    private String reviewPeriod;
    private Double rating;
    private String feedback;
    private String goals;

    public PerformanceRequestDTO() {
    }

    public PerformanceRequestDTO(Long employeeId, String reviewPeriod,
            Double rating, String feedback, String goals) {
        this.employeeId = employeeId;
        this.reviewPeriod = reviewPeriod;
        this.rating = rating;
        this.feedback = feedback;
        this.goals = goals;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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