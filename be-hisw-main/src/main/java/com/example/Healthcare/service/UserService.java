package com.example.Healthcare.service;

import com.example.Healthcare.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    long countUsers();

    // ✅ Thêm mới:
    User getUserByEmail(String email);
    User updateCurrentUser(String email, User updatedInfo);
    User saveUser(User user); // dùng để lưu lại sau cập nhật
}