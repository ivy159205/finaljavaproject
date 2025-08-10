package com.example.Healthcare.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "DailyLog")
public class DailyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // << THÊM DÒNG NÀY
    @Column(name = "log_id")
    private Long logId; // << THAY ĐỔI: String -> Long

    @Column(name = "log_date")
    private LocalDate logDate;

    @Column(name = "note")
    private String note;

    // Giữ LAZY để tối ưu, không cần annotation JSON ở đây
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id")
    private User user;

    // Quan hệ này có thể giữ lại hoặc bỏ annotation JSON tùy vào bạn có cần trả về health records không
    @OneToMany(mappedBy = "dailyLog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthRecord> records;

    // Getters and Setters
    public Long getLogId() { // << THAY ĐỔI: String -> Long
        return logId;
    }

    public void setLogId(Long logId) { // << THAY ĐỔI: String -> Long
        this.logId = logId;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<HealthRecord> getRecords() {
        return records;
    }

    public void setRecords(List<HealthRecord> records) {
        this.records = records;
    }
}