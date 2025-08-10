package com.example.Healthcare.DTO;

import java.util.List;

public class HealthRecordCreateDTO {
    private Long userId;
    private String date;
    private String notes;
    private List<MetricDTO> metrics;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<MetricDTO> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricDTO> metrics) {
        this.metrics = metrics;
    }
}
