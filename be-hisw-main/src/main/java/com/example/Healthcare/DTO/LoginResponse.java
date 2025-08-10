package com.example.Healthcare.DTO;

public class LoginResponse {
    public String message;
    public String token;

    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
