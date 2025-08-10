package com.example.Healthcare.repository;

import com.example.Healthcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // ✅ Thêm phương thức để tìm user bằng email
    User findByEmail(String email);
}
