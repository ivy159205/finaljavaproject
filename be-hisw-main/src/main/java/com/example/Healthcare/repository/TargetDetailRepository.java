    package com.example.Healthcare.repository;
    import com.example.Healthcare.model.TargetDetail;
    import java.util.List;
    import org.springframework.data.jpa.repository.JpaRepository;
    public interface TargetDetailRepository extends JpaRepository<TargetDetail, Long> {
        List<TargetDetail> findByTargetTargetId(Long targetId);
    }
