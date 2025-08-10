package com.example.Healthcare.repository;
import com.example.Healthcare.model.MetricType;

import org.springframework.data.jpa.repository.JpaRepository;
public interface MetricTypeRepository extends JpaRepository<MetricType, Long> {}