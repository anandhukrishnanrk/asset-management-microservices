package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "metrics_setup", schema = "pts", catalog = "pts")
public class MetricsSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer metricsId;

    @Column(name = "metrics_Name")
    private String metricsName;

    @Column(name = "display_Order")
    private Integer displayOrder;

    @Column(name = "process")
    private String process;

    @Column(name = "delete_Status")
    private String deleteStatus = "N";

    @Column(name = "lsl")
    private Double lsl;

    @Column(name = "usl")
    private Double usl;

    @Column(name = "organization_Objective")
    private Double organizationObjective;

    @Column(name = "sd")
    private Double sd;

    @Column(name = "mean")
    private Double mean;

    @Column(name = "lcl")
    private Double lcl;

    @Column(name = "ucl")
    private Double ucl;
}
