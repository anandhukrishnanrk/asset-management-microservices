package com.cis.api.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema ="pts", name = "cmmi_component_reschedule", catalog = "pts")
@Data
public class CmmiComponentReschedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // native generator
    @Column(name = "id")
    private Integer id;

    /**
     * Raw FK column as defined in HBM
     */
    @Column(name = "component_Id", nullable = false)
    private Integer componentId;

    /**
     * Logical relationship used by CmmiProjectComponents.reschedules
     * Read-only to avoid duplicate column mapping.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "component_Id", insertable = false, updatable = false)
    private CmmiProjectComponents component;

    @Temporal(TemporalType.DATE)
    @Column(name = "planned_Start_Date")
    private Date plannedStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "planned_End_Date")
    private Date plannedEndDate;

    @Column(name = "planned_Effort")
    private Double plannedEffort;

    @Column(name = "reason")
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_By", nullable = true)
    private SeatUserAlloted actionBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "action_On")
    private Date actionOn;
}
