package com.cis.api.entity;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmp_measurement_plan", schema = "pts", catalog ="pts")
public class PmpMeasurementPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmp_Id", nullable = false)
    private Integer pmpId;

//    @Column(name = "metric_name")
//    private String metricName;

    @Column(name = "project_Objective", nullable = false)
    private String projectObjective;

    @Column(name = "remarks", nullable = false)
    private String remarks;

//    @Column(name = "lcl")
//    private Integer lcl;
//
//    @Column(name = "ucl")
//    private Integer ucl;

//    @Column(name = "actual_value")
//    private Double actualValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "metricsSetup_Id", nullable = true)
    private MetricsSetup metricsSetup;
}