package com.cis.api.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "pmp_dependencies" , schema = "pts", catalog = "pts")
public class PmpDependencies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmp_Id", nullable = false)
    private Integer pmpId;

    @Column(name = "dependencies", nullable = false)
    private String dependencies;

    @Column(name = "type_And_Responsibility", nullable = false)
    private String typeAndResponsibility;

    // O: Open, C: Closed
    @Column(name = "status", nullable = false, length = 1)
    private String status;

    public PmpDependencies() {
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

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public String getTypeAndResponsibility() {
        return typeAndResponsibility;
    }

    public void setTypeAndResponsibility(String typeAndResponsibility) {
        this.typeAndResponsibility = typeAndResponsibility;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}