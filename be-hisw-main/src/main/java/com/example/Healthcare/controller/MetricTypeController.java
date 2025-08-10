package com.example.Healthcare.controller;


import com.example.Healthcare.model.MetricType;
import com.example.Healthcare.service.MetricTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@CrossOrigin(origins = "*") // Hoặc "http://127.0.0.1:5500" nếu bạn muốn cụ thể
public class MetricTypeController {

    @Autowired
    private MetricTypeService metricService;

    @GetMapping
    public List<MetricType> getAllMetrics() {
        return metricService.getAll();
    }

    @GetMapping("/{id}")
    public MetricType getMetricById(@PathVariable Long id) {
        return metricService.getById(id);
    }

    @PostMapping
    public MetricType createMetric(@RequestBody MetricType metricType) {
        return metricService.create(metricType);
    }

    @PutMapping("/{id}")
    public MetricType updateMetric(@PathVariable Long id, @RequestBody MetricType metricType) {
        return metricService.update(id, metricType);
    }

    @DeleteMapping("/{id}")
    public void deleteMetric(@PathVariable Long id) {
        metricService.delete(id);
    }
}