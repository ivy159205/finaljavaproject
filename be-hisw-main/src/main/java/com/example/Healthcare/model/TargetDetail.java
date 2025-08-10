package com.example.Healthcare.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TargetDetail")
public class TargetDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id") // THÊM DÒNG NÀY ĐỂ ÁNH XẠ ĐÚNG TÊN CỘT TRONG DB
    private Long detailId; // ĐÃ SỬA: Thay đổi kiểu dữ liệu từ String sang Long

    @Transient // Báo cho JPA không lưu trường này vào DB
    private Long metricId;

    @Column(name = "comparison_type")
    private String comparisonType;

    @Column(name = "target_value")
    private Double targetValue;

    @Column(name = "aggregation_type")
    private String aggregationType;

    @ManyToOne(fetch = FetchType.LAZY) // Thêm fetch type cho mối quan hệ
    @JoinColumn(name = "target_id", referencedColumnName = "target_id") // Đảm bảo đúng tên cột và tên tham chiếu
    @JsonBackReference
    private Target target;

    @ManyToOne(fetch = FetchType.LAZY) // Thêm fetch type cho mối quan hệ
    @JoinColumn(name = "metric_id", referencedColumnName = "metric_id") // Đảm bảo đúng tên cột và tên tham chiếu
    private MetricType metricType;

    // Constructors (nên có một constructor không tham số)
    public TargetDetail() {
    }

    // Constructors with fields (có thể thêm nếu cần)
    public TargetDetail(String comparisonType, Double targetValue, String aggregationType, Target target,
            MetricType metricType) {
        this.comparisonType = comparisonType;
        this.targetValue = targetValue;
        this.aggregationType = aggregationType;
        this.target = target;
        this.metricType = metricType;
    }

    // --- Getters & Setters ---

    public Long getDetailId() { // ĐÃ SỬA: Kiểu trả về là Long
        return detailId;
    }

    public void setDetailId(Long detailId) { // ĐÃ SỬA: Kiểu tham số là Long
        this.detailId = detailId;
    }

    public String getComparisonType() {
        return comparisonType;
    }

    public void setComparisonType(String comparisonType) {
        this.comparisonType = comparisonType;
    }

    public Double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }

    public String getAggregationType() {
        return aggregationType;
    }

    public void setAggregationType(String aggregationType) {
        this.aggregationType = aggregationType;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public Long getMetricId() {
        return metricId;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }
}