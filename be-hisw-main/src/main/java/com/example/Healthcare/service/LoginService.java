package com.example.Healthcare.service;
import com.example.Healthcare.model.User;
import com.example.Healthcare.DTO.RegisterRequest;;

public interface LoginService {
    User register(RegisterRequest request);
    User login(String email, String password);
    void resetPassword(String email, String newPassword);
    void generateAndSendOtp(String email);
    void resetPassword(String email, String otp, String newPassword);
}
