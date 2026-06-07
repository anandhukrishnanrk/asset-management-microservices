package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmp_development_infrastructure", schema = "pts", catalog = "pts")
public class PmpDevelopmentInfrastructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmp_Id", nullable = false)
    private Integer pmpId;

    @Column(name = "infrastructure_Type", nullable = false)
    private String infrastructureType;

    @Column(name = "infrastructure", nullable = false)
    private String infrastructure;

    @Column(name = "required_Quantity", nullable = false)
    private Integer requiredQuantity;
}
