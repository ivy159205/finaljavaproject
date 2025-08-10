package com.example.Healthcare.repository;
import com.example.Healthcare.model.Target;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
  List<Target> findByUserUserId(Long userId);
long countByStatus(String status);
Optional<Target> findByTargetIdAndUser_UserId(Long targetId, Long userId);
}