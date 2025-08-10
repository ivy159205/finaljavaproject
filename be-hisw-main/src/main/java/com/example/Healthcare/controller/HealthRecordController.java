package com.example.Healthcare.controller;

import com.example.Healthcare.DTO.HealthRecordCreateDTO;
import com.example.Healthcare.DTO.HealthRecordDTO;
import com.example.Healthcare.DTO.DailyLogResponseDTO; // ✅ Bổ sung DTO response mới
import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.model.HealthRecord;
import com.example.Healthcare.repository.DailyLogRepository;
import com.example.Healthcare.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/healthrecords")
@CrossOrigin(origins = "*")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    @Autowired
    private DailyLogRepository dailyLogRepository; // ✅ cần để lấy notes

    // Lấy tất cả health records (dạng DTO)
    @GetMapping
    public List<HealthRecordDTO> getAllHealthRecords() {
        return healthRecordService.getAllHealthRecordsAsDTO();
    }

    // Lấy health record theo ID
    @GetMapping("/{id}")
    public ResponseEntity<HealthRecordDTO> getHealthRecordById(@PathVariable Long id) {
        HealthRecordDTO dto = healthRecordService.getHealthRecordByIdAsDTO(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // Lấy health records theo user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HealthRecordDTO>> getHealthRecordsByUserId(@PathVariable Long userId) {
        List<HealthRecordDTO> records = healthRecordService.getHealthRecordsByUserId(userId);
        return ResponseEntity.ok(records);
    }

    // Tạo health record mới
    @PostMapping
    public ResponseEntity<?> createHealthRecord(@RequestBody HealthRecordCreateDTO dto) {
        HealthRecordDTO created = healthRecordService.createHealthRecordFromDTO(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Cập nhật 1 health record
    @PutMapping("/{id}")
    public ResponseEntity<HealthRecordDTO> updateHealthRecord(@PathVariable Long id,
                                                               @RequestBody HealthRecord healthRecordDetails) {
        HealthRecordDTO updatedDto = healthRecordService.updateHealthRecordAsDTO(id, healthRecordDetails);
        return (updatedDto != null) ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    // Xoá health record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthRecord(@PathVariable Long id) {
        healthRecordService.deleteHealthRecord(id);
        return ResponseEntity.noContent().build();
    }

    // Đếm tổng health record
    @GetMapping("/count")
    public long countHealthRecords() {
        return healthRecordService.countHealthRecords();
    }

    // ✅ Lấy health record của user theo ngày, kèm theo notes
    @GetMapping("/user/{userId}/log/{date}")
    public ResponseEntity<?> getHealthRecordsByUserAndDate(
            @PathVariable Long userId,
            @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);

        // Lấy daily log
        Optional<DailyLog> dailyLogOptional = dailyLogRepository.findByUser_UserIdAndLogDate(userId, localDate);
        if (!dailyLogOptional.isPresent()) {
            return ResponseEntity.ok(null);
        }

        String notes = dailyLogOptional.get().getNote();

        List<HealthRecordDTO> records = healthRecordService.getHealthRecordsByUserAndDate(userId, localDate);

        DailyLogResponseDTO response = new DailyLogResponseDTO(notes, records);
        return ResponseEntity.ok(response);
    }

    // ✅ Cập nhật theo user + ngày
    @PutMapping("/by-user-date")
    public ResponseEntity<?> updateHealthRecordsByUserAndDate(@RequestBody HealthRecordCreateDTO dto) {
        try {
            List<HealthRecordDTO> updated = healthRecordService.updateHealthRecordsFromDTO(dto);
            if (updated.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No health records found to update.");
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
