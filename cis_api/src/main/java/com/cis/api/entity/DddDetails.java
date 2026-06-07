package com.cis.api.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "ddd_details", schema = "pts", catalog = "pts")
@Data
public class DddDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ddd_Id")
    private Long dddId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id")
    private ModuleSetup module;

    @Column(name = "submission_Date", nullable = false)
    private LocalDate submissionDate;

    @Column(name = "ddd_Version", nullable = false)
    private Double dddVersion;

    @Column(name = "release_Status", length = 1)
    private String releaseStatus;

    @Column(name = "ddd_Status", nullable = false, length = 1)
    private String dddStatus;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "user_Id")
    private Long userId;

    @Column(name = "insert_Date")
    private LocalDateTime insertDate;

    @Column(name = "group_Id")
    private Long groupId;

    @Column(name = "project_Id", nullable = false)
    private Long projectId;

    @Column(name = "approved_By")
    private Long approvedBy;

    @Column(name = "release_By")
    private Long releaseBy;

    @Column(name = "release_Date")
    private LocalDateTime releaseDate;

    @Column(name = "approved_Date")
    private LocalDateTime approvedDate;
    
    @Transient
    private String fileName;

}
