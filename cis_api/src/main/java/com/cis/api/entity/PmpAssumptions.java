package com.cis.api.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "pmp_assumptions", schema = "pts", catalog = "pts")
public class PmpAssumptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmp_Id", nullable = false)
    private Integer pmpId;

    @Column(name = "assumption", nullable = false)
    private String assumption;

    @Column(name = "project_Impact", nullable = false)
    private String projectImpact;

    @Column(name = "acd_Type", nullable = false)
    private String acdType;

    public PmpAssumptions() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPmpId() {
        return pmpId;
    }

    public void setPmpId(Integer pmpId) {
        this.pmpId = pmpId;
    }

    public String getAssumption() {
        return assumption;
    }

    public void setAssumption(String assumption) {
        this.assumption = assumption;
    }

    public String getProjectImpact() {
        return projectImpact;
    }

    public void setProjectImpact(String projectImpact) {
        this.projectImpact = projectImpact;
    }

    public String getAcdType() {
        return acdType;
    }

    public void setAcdType(String acdType) {
        this.acdType = acdType;
    }
}