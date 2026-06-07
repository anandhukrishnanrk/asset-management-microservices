package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resource_project_cost", schema = "pts", catalog = "pts")
@Data
public class ResourceProjectCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // ===============================
    // Relationships
    // ===============================

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "blended_cost_setup_Id", nullable = false)
//    private BlendedCostSetup blendedCostSetup;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails projectDetails;

    // ===============================
    // Column Mappings
    // ===============================

    @Column(name = "monthly_Cost")
    private Double monthlyCost;

    @Column(name = "day_Cost")
    private Double dayCost;

    @Column(name = "hour_Cost")
    private Double hourCost;

    @Column(name = "active_Status")
    private String activeStatus;

    // ===============================
    // Not Present in Hibernate XML
    // ===============================

    @Transient
    private String remarks;

   
}
