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
import lombok.Data;

@Entity
@Table(name = "release_details", schema = "pts", catalog = "pts")
@Data
public class ReleaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "release_Id")
    private Long releaseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id")
    private ModuleSetup module;

    @Column(name = "submission_Date")
    private LocalDate submissionDate;

    @Column(name = "release_Version")
    private String releaseVersion;

    @Column(name = "release_Status")
    private String releaseStatus;

    @Column(name = "release_Date")
    private LocalDateTime releaseDate;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "seat_Id")
    private SeatUserAlloted seat;

    @Column(name = "insert_Date")
    private LocalDateTime insertDate;

    @Column(name = "group_Id")
    private Integer groupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private UserSetup user;

    @Column(name = "approved_By")
    private Integer approvedBy;

    @Column(name = "approved_Date")
    private LocalDateTime approvedDate;

    @Column(name = "release_By")
    private Integer releaseBy;



    @Column(name = "project_Size")
    private Integer projectSize;
}
