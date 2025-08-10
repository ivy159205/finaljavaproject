package com.example.Healthcare.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "[User]")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "role")
    private String role;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<DailyLog> dailyLogs;

    // <<< PHẦN ĐƯỢC THÊM VÀO ĐỂ SỬA LỖI >>>
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Target> targets;
    // <<< KẾT THÚC PHẦN THÊM VÀO >>>

    // --- CONSTRUCTORS ---

    public User() {}

    public User(String username, String password, String email, String phoneNumber, String gender, String role, LocalDate dob, Double weight, Double height) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

    // --- GETTERS & SETTERS ---

    // ... các getter và setter hiện có giữ nguyên ...
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }
    public List<DailyLog> getDailyLogs() { return dailyLogs; }
    public void setDailyLogs(List<DailyLog> dailyLogs) { this.dailyLogs = dailyLogs; }

    // <<< GETTER VÀ SETTER MỚI CHO TARGETS >>>
    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }
    // <<< KẾT THÚC PHẦN GETTER/SETTER MỚI >>>
}