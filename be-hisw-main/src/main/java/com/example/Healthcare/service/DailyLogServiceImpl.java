package com.example.Healthcare.service;

import com.example.Healthcare.DTO.DailyLogDTO; // Import DTO
import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.model.User;
import com.example.Healthcare.repository.DailyLogRepository;
import com.example.Healthcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import org.springframework.transaction.annotation.Transactional; // Thêm import này
import java.util.stream.Collectors;

@Service
public class DailyLogServiceImpl implements DailyLogService {

    @Autowired
    private DailyLogRepository dailyLogRepo;

    @Autowired
    private UserRepository userRepo;

    // HÀM HELPER ĐỂ CHUYỂN ĐỔI
    private DailyLogDTO convertToDto(DailyLog log) {
        Long userId = (log.getUser() != null) ? log.getUser().getUserId() : null;
        String username = (log.getUser() != null) ? log.getUser().getUsername() : "Không xác định";

        return new DailyLogDTO(
            log.getLogId(),
            log.getLogDate().toString(),
            log.getNote(),
            userId,
            username
        );
    }
        @Override
    public DailyLog save(DailyLog log) {
        return dailyLogRepo.save(log);
    }

    @Override
    @Transactional(readOnly = true) // Cần Transactional để LAZY loading hoạt động
    public List<DailyLogDTO> getAllLogs() {
        return dailyLogRepo.findAll()
                .stream()
                .map(this::convertToDto) // Chuyển mỗi DailyLog thành DailyLogDTO
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public DailyLog getOrCreateLogByUserAndDate(Long userId, LocalDate date) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) return null;

        // Kiểm tra log đã tồn tại chưa
        return dailyLogRepo.findByUserAndLogDate(user, date)
            .orElseGet(() -> {
                DailyLog newLog = new DailyLog();
                newLog.setUser(user);
                newLog.setLogDate(date);
                newLog.setNote("Tự động tạo khi nhập bản ghi sức khỏe");

                return dailyLogRepo.save(newLog);
            });
    }

    @Override
    @Transactional(readOnly = true)
    public DailyLogDTO getLogById(Long id) {
        return dailyLogRepo.findById(id)
                .map(this::convertToDto) // Chuyển đổi nếu tìm thấy
                .orElse(null);
    }

    @Override
    public List<DailyLog> getLogsByUserId(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return dailyLogRepo.findByUser(user);
        }
        return List.of();
    }

    @Override
    public List<DailyLog> getLogsByUserIdAndDateRange(Long userId, LocalDate start, LocalDate end) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return dailyLogRepo.findByUserAndLogDateBetween(user, start, end);
        }
        return List.of();
    }

    @Override
    @Transactional // Thêm annotation này
    public DailyLogDTO createLog(DailyLog log) {
        // ... logic của bạn để thiết lập quan hệ
        DailyLog savedLog = dailyLogRepo.save(log); // Lưu vào DB
        return convertToDto(savedLog); // Trả về DTO
    }

    @Override
    @Transactional // Thêm annotation này
    public DailyLogDTO updateLog(Long id, DailyLog log) {
        if (dailyLogRepo.existsById(id)) {
            log.setLogId(id);
            
            // ... logic của bạn
            
            DailyLog updatedLog = dailyLogRepo.save(log); // Lưu vào DB
            return convertToDto(updatedLog); // Trả về DTO
        }
        return null; 
    }

    @Override
    public void deleteLog(Long id) { // << THAY ĐỔI: String -> Long
        dailyLogRepo.deleteById(id);
    }
}