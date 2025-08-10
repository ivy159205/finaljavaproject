package com.example.Healthcare.repository;
import com.example.Healthcare.model.DailyLog;
import com.example.Healthcare.model.HealthRecord;
import com.example.Healthcare.model.MetricType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByDailyLogLogId(Long logId);
    List<HealthRecord> findByDailyLog_User_UserId(Long userId);
    List<HealthRecord> findByDailyLog_User_UserIdAndDailyLog_LogDate(Long userId, LocalDate date);
    Optional<HealthRecord> findByDailyLog_User_UserIdAndDailyLog_LogDateAndMetricType_MetricId(
    Long userId, LocalDate logDate, Long metricId);
    Optional<HealthRecord> findByDailyLogAndMetricType(DailyLog dailyLog, MetricType metricType);
}