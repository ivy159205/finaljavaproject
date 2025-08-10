package com.example.Healthcare.DTO;
import lombok.Data;
@Data
public class MetricInputDto {
    private Long metricId;
    private String value; // dùng String để nhận cả "120/80"

    // getters, setters
}