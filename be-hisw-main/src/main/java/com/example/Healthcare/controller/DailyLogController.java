package com.example.Healthcare.controller;

import com.example.Healthcare.DTO.DailyLogDTO; // <-- Import DTO
import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.service.DailyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dailylogs")
@CrossOrigin("*")
public class DailyLogController {

    @Autowired
    private DailyLogService dailyLogService;

    @GetMapping
    public List<DailyLogDTO> getAllLogs() { // <-- THAY ĐỔI KIỂU TRẢ VỀ
        return dailyLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public DailyLogDTO getLogById(@PathVariable Long id) { // <-- THAY ĐỔI KIỂU TRẢ VỀ
        return dailyLogService.getLogById(id);
    }

    @GetMapping("/by-user/{userId}")
    public List<DailyLog> getLogsByUserId(@PathVariable Long userId) {
        return dailyLogService.getLogsByUserId(userId);
    }

    @GetMapping("/by-user/{userId}/range")
    public List<DailyLog> getLogsByUserIdAndRange(
            @PathVariable Long userId,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        return dailyLogService.getLogsByUserIdAndDateRange(userId, LocalDate.parse(start), LocalDate.parse(end));
    }

    @PostMapping
    public DailyLogDTO createLog(@RequestBody DailyLog log) { // <-- SỬA KIỂU TRẢ VỀ
        return dailyLogService.createLog(log);
    }

    @PutMapping("/{id}")
    public DailyLogDTO updateLog(@PathVariable Long id, @RequestBody DailyLog log) { // <-- SỬA KIỂU TRẢ VỀ
        return dailyLogService.updateLog(id, log);
    }
    
    @DeleteMapping("/{id}")
    public void deleteLog(@PathVariable Long id) { // << THAY ĐỔI: String -> Long
        dailyLogService.deleteLog(id);
    }
}