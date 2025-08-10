package com.example.Healthcare.service;

import com.example.Healthcare.model.User;
import com.example.Healthcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User existingUser = userRepo.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPhoneNumber(userDetails.getPhoneNumber());
            existingUser.setGender(userDetails.getGender());
            existingUser.setDob(userDetails.getDob());
            existingUser.setRole(userDetails.getRole());
            existingUser.setHeight(userDetails.getHeight());
            existingUser.setWeight(userDetails.getWeight());
            return userRepo.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public long countUsers() {
        return userRepo.count();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User updateCurrentUser(String email, User updatedInfo) {
        User user = userRepo.findByEmail(email);
        if (user != null) {
            // Kiểm tra hợp lệ:
            if (updatedInfo.getWeight() != null && (updatedInfo.getWeight() <= 0 || updatedInfo.getWeight() > 500)) {
                throw new IllegalArgumentException("Weight must be greater than 0 and reasonable (e.g., <= 500kg).");
            }
            if (updatedInfo.getHeight() != null && (updatedInfo.getHeight() <= 0 || updatedInfo.getHeight() > 300)) {
                throw new IllegalArgumentException("Height must be greater than 0 and reasonable (e.g., <= 300cm).");
            }

            user.setUsername(updatedInfo.getUsername());
            user.setDob(updatedInfo.getDob());
            user.setGender(updatedInfo.getGender());
            user.setHeight(updatedInfo.getHeight());
            user.setWeight(updatedInfo.getWeight());
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }
}