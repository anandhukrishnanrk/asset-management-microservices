package com.cis.api.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "level_one_estimation_version", schema = "pts", catalog = "pts")
@Data
public class LevelOneEstimationVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_One_Estimation_Version_Id")
    private Integer levelOneEstimationVersionId;

    @Column(name = "estimation_Version_No")
    private Double estimationVersionNo;

    @Column(name = "project_Id")
    private Integer projectId;

    @Column(name = "estimation_Created_By")
    private Integer estimationCreatedBy;

    @Column(name = "estimation_Created_On")
    private LocalDate estimationCreatedOn;

    @Column(name = "estimation_Approved_By")
    private Integer estimationApprovedBy;

    @Column(name = "estimation_Approved_On")
    private LocalDate estimationApprovedOn;

    @Column(name = "estimation_Revised_On")
    private LocalDate estimationRevisedOn;

    @Column(name = "revise_Status", length = 1)
    private String reviseStatus;

    @Column(name = "approve_Status", length = 1)
    private String approveStatus;

    @Column(name = "estimation_Revised_By")
    private Integer estimationRevisedBy;

    @Column(name = "group_Id")
    private Integer groupId;
}
