package com.example.Healthcare.service;

import com.example.Healthcare.model.MetricType;

import java.util.List;

public interface MetricTypeService {
    List<MetricType> getAll();
    MetricType getById(Long id);
    MetricType create(MetricType metricType);
    MetricType update(Long id, MetricType metricType);
    void delete(Long id);
}