package com.example.Healthcare.DTO;
import java.util.List;
import lombok.Data;
@Data
public class HealthLogInputDto {
    private Long userId;
    private String date; // yyyy-MM-dd
    private String notes;
    private List<MetricInputDto> metrics;
    public Long getUserId() {
        return userId;
    }
    // getters, setters
}
