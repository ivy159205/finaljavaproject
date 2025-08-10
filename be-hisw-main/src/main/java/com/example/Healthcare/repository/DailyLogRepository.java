package com.example.Healthcare.repository;
import java.util.Optional;

import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.model.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyLogRepository extends JpaRepository<DailyLog, Long> { // << THAY ĐỔI: String -> Long
    List<DailyLog> findByUser(User user);
    List<DailyLog> findByUserAndLogDateBetween(User user, LocalDate start, LocalDate end);
    Optional<DailyLog> findByUserAndLogDate(User user, LocalDate logDate);
    Optional<DailyLog> findByUser_UserIdAndLogDate(Long userId, LocalDate logDate);
}