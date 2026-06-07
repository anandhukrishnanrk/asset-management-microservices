package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmp_project_escalations", schema = "pts", catalog = "pts")
public class PmpProjectEscalations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmp_Id", nullable = false)
    private Integer pmpId;

    @Column(name = "escalation_For", nullable = false)
    private String escalationFor;

    @Column(name = "threshold_Period", nullable = false)
    private String thresholdPeriod;

    @Column(name = "from_Person", nullable = false)
    private String fromPerson;

    @Column(name = "to_Person", nullable = false)
    private String toPerson;

    @Column(name = "remarks", nullable = false)
    private String remarks;
}