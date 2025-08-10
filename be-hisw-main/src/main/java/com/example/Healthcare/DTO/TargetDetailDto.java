package com.example.Healthcare.DTO;
import lombok.Data;
@Data
public class TargetDetailDto {
    private Long tdetailId; 
    private Long metricId;
    private String comparisonType;
    private Double targetValue;
    private String aggregationType;
}
