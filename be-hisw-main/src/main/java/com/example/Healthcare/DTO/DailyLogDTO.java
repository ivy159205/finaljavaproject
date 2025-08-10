package com.example.Healthcare.DTO;

public class DailyLogDTO {
    private Long logId;
    private String logDate;
    private String note;
    private Long userId;
    private String username;

    // Constructors
    public DailyLogDTO() {
    }

    public DailyLogDTO(Long logId, String logDate, String note, Long userId, String username) {
        this.logId = logId;
        this.logDate = logDate;
        this.note = note;
        this.userId = userId;
        this.username = username;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
