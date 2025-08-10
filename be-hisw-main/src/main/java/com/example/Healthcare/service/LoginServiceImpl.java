package com.example.Healthcare.service;

import com.example.Healthcare.model.User;
import com.example.Healthcare.DTO.RegisterRequest;
import com.example.Healthcare.repository.LoginRepository;
import com.example.Healthcare.security.JwtUtil;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailService emailService;

    // Lưu trữ OTP: key=email, value=otp
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();
    // Lưu trữ thời gian hết hạn OTP: key=email, value=expirationTime
    private final Map<String, LocalDateTime> otpExpiryStore = new ConcurrentHashMap<>();

    @Override
    public User register(RegisterRequest req) {
        if (userRepo.findByEmail(req.email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(req.username);
        user.setPassword(passwordEncoder.encode(req.password)); // Mã hoá mật khẩu
        user.setEmail(req.email);
        user.setPhoneNumber(req.phoneNumber);
        user.setGender(req.gender);
        user.setRole(req.role == null ? "user" : req.role); // Gán mặc định nếu thiếu
        user.setDob(req.dob);

        return userRepo.save(user);
    }

    @Override
    public User login(String email, String password) {
        return userRepo.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }

    public String generateTokenForUser(User user) {
        return jwtUtil.generateToken(user.getUserId() ,user.getEmail(), user.getRole());
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    @Override
    public void generateAndSendOtp(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with this email"));

        Random random = new Random();
        String otp = String.format("%06d", random.nextInt(999999));

        // Lưu OTP và thời gian hết hạn (ví dụ 5 phút)
        otpStore.put(email, otp);
        otpExpiryStore.put(email, LocalDateTime.now().plusMinutes(5));

        emailService.sendOtpEmail(email, otp);
    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {
        // 1. Xác thực OTP
        String storedOtp = otpStore.get(email);
        LocalDateTime expiryTime = otpExpiryStore.get(email);

        if (storedOtp == null || !storedOtp.equals(otp)) {
            throw new IllegalArgumentException("Invalid OTP.");
        }

        if (expiryTime == null || LocalDateTime.now().isAfter(expiryTime)) {
            otpStore.remove(email); // Xoá OTP hết hạn
            otpExpiryStore.remove(email);
            throw new IllegalArgumentException("OTP has expired.");
        }

        // 2. Tìm người dùng và cập nhật mật khẩu
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with this email"));

        user.setPassword(passwordEncoder.encode(newPassword)); // Mã hoá mật khẩu mới
        userRepo.save(user);

        // 3. Xoá OTP sau khi sử dụng thành công
        otpStore.remove(email);
        otpExpiryStore.remove(email);
    }
}
