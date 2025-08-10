package com.example.Healthcare.DTO;

import java.time.LocalDate;
// DTO để đăng ký
public class RegisterRequest {
    public Long userId;
    public String username;
    public String password;
    public String email;
    public String phoneNumber;
    public String gender;
    public String role;
    public LocalDate dob;
}

