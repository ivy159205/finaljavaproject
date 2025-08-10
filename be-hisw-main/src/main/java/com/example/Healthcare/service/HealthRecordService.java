// HealthRecordService.java
package com.example.Healthcare.service;

import com.example.Healthcare.DTO.HealthRecordDTO;
import com.example.Healthcare.DTO.HealthRecordCreateDTO;

import java.time.LocalDate;
import java.util.List;

public interface HealthRecordService {
    List<HealthRecordDTO> getAllHealthRecordsAsDTO();
    HealthRecordDTO getHealthRecordByIdAsDTO(Long id);
    HealthRecordDTO createHealthRecordAsDTO(com.example.Healthcare.model.HealthRecord healthRecord);
    HealthRecordDTO createHealthRecordFromDTO(HealthRecordCreateDTO dto);
    HealthRecordDTO updateHealthRecordAsDTO(Long id, com.example.Healthcare.model.HealthRecord healthRecordDetails);
    List<HealthRecordDTO> getHealthRecordsByUserId(Long userId);
    List<HealthRecordDTO> getHealthRecordsByUserAndDate(Long userId, LocalDate date);
    void deleteHealthRecord(Long id);
    long countHealthRecords();
    List<HealthRecordDTO> updateHealthRecordsFromDTO(HealthRecordCreateDTO dto);
}