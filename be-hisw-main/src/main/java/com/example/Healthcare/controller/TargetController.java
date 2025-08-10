package com.example.Healthcare.controller;

import com.example.Healthcare.DTO.TargetDto;
import com.example.Healthcare.model.Target;
import com.example.Healthcare.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

import java.util.List;

@RestController
@RequestMapping("/api/targets")
@CrossOrigin(origins = "*")
public class TargetController {

    @Autowired
    private TargetService targetService;

    @GetMapping
    public List<Target> getAllTargets() {
        return targetService.getAllTargets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Target> getTargetById(@PathVariable Long id) {
        Target target = targetService.getTargetById(id);
        return target != null ? ResponseEntity.ok(target) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Target> createTarget(@RequestBody Target target) {
        Target saved = targetService.createTarget(target);
        return ResponseEntity.ok(saved);
    }
    @PostMapping("/add")
        public ResponseEntity<TargetDto> addTarget(@RequestBody TargetDto targetDto) {
            TargetDto result = targetService.addTarget(targetDto);
            return ResponseEntity.ok(result);
        }
   @PutMapping("/{id}")
public ResponseEntity<?> updateTarget(@PathVariable Long id, @RequestBody Target target, Principal principal) {
    // 🧠 Lấy username (hoặc userId) từ token
    String username = principal.getName(); // nếu bạn dùng sub = username trong JWT

    // Tìm target hiện có
    Target existingTarget = targetService.getTargetById(id);
    if (existingTarget == null) {
        return ResponseEntity.notFound().build();
    }

    // Kiểm tra quyền: chỉ cho phép chủ sở hữu sửa
    String ownerUsername = existingTarget.getUser().getUsername(); // hoặc .getEmail(), tuỳ cách bạn map
    if (!username.equals(ownerUsername)) {
        return ResponseEntity.status(403).body("Bạn không có quyền sửa mục tiêu này.");
    }

    // Thực hiện cập nhật
    Target updated = targetService.updateTarget(id, target);
    return ResponseEntity.ok(updated);
}
@PutMapping("/targets/{id}")
public ResponseEntity<?> updateTargetWed(
        @PathVariable("id") Long targetId,
        @RequestBody TargetDto targetDto) {
    try {
        Long userId = targetDto.getUserId(); // FE đã giải mã token và truyền userId

        TargetDto updated = targetService.updateTargetWed(targetId, targetDto, userId);
        return ResponseEntity.ok(updated);

    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi.");
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarget(@PathVariable Long id) {
        targetService.deleteTarget(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint mới hoặc sửa đổi để lấy target theo user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TargetDto>> getTargetsByUserId(@PathVariable Long userId) {
        List<TargetDto> targets = targetService.getTargetDTOsByUserId(userId);
        return ResponseEntity.ok(targets);
    }

    // --- THÊM ENDPOINT NÀY ---
    @GetMapping("/active/count")
    public long countActiveTargets() {
        return targetService.countActiveTargets();
    }
}
