package com.cis.api.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(schema ="pts", name = "cmmi_project_components", catalog = "pts")
@Data
public class CmmiProjectComponents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "component_Id")
    private CmmiSubgroup component;

    @Column(name = "inclusion_Status")
    private String inclusionStatus;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "planned_Start_Date")
    private Date plannedStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "planned_End_Date")
    private Date plannedEndDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "actual_Start_Date")
    private Date actualStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "actual_End_Date")
    private Date actualEndDate;

    @Column(name = "planned_Effort")
    private Double plannedEffort;

    @Column(name = "actual_Effort")
    private Double actualEffort;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsible_Person", nullable = true)
    private UserSetup responsiblePerson;

    /**
     * Raw FK column preserved exactly as in HBM
     */
    @Column(name = "cmmi_Project_Id", nullable = false)
    private Integer cmmiProjectId;

    /**
     * Logical relationship to parent CmmiProject.
     * Uses same column, but read-only to avoid duplicate writes.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cmmi_Project_Id", insertable = false, updatable = false)
    private CmmiProject cmmiProject;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_By", nullable = true)
    private SeatUserAlloted actionBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "action_Datetime")
    private Date actionDatetime;

    @OneToMany(mappedBy = "component", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<CmmiComponentReschedule> reschedules;

    @Column(name = "module_Id")
    private Integer moduleId;
}
