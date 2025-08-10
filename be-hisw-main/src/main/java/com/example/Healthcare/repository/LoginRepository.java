package com.example.Healthcare.repository;
import com.example.Healthcare.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}