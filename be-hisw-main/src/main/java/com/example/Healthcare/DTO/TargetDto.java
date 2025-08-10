package com.example.Healthcare.DTO;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
@Data
public class TargetDto {
    private Long targetId;
    private String title;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long userId;
    private List<TargetDetailDto> details;
}

