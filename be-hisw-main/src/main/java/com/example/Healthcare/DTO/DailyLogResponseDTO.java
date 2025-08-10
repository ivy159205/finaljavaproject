package com.example.Healthcare.DTO;

import java.util.List;

public class DailyLogResponseDTO {
    private String notes;
    private List<HealthRecordDTO> metrics;

    // Constructors
    public DailyLogResponseDTO(String notes, List<HealthRecordDTO> metrics) {
        this.notes = notes;
        this.metrics = metrics;
    }

    // Getters và Setters
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<HealthRecordDTO> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<HealthRecordDTO> metrics) {
        this.metrics = metrics;
    }
}
