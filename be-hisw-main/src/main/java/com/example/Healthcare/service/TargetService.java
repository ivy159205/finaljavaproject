package com.example.Healthcare.service;
import com.example.Healthcare.DTO.TargetDto;
import com.example.Healthcare.model.Target;

import java.util.List;

public interface TargetService {
    List<Target> getAllTargets();
    Target getTargetById(Long id);
    Target createTarget(Target target);
    Target updateTarget(Long id, Target updatedTarget);
    void deleteTarget(Long id);
    TargetDto addTarget(TargetDto targetDto);
      List<TargetDto> getTargetDTOsByUserId(Long userId);// Thay String bằng Long
       TargetDto updateTargetWed(Long targetId, TargetDto targetDto, Long userId);
    // --- THÊM PHƯƠNG THỨC NÀY ---
    long countActiveTargets();
}