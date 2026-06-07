package com.cis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "test_case_versions", schema = "pts", catalog = "pts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCaseDetailsVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_Id")
    private Integer versionId;

    @Column(name = "tst_Case_Version")
    private Double testCaseVersion;

    // -----------------------------
    // ASSOCIATIONS (SAFE FOR JSON)
    // -----------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_Id", nullable = false)
    @JsonIgnoreProperties({"groups", "modules", "users", "srsList"})
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_Id")
    @JsonIgnoreProperties({"projects", "members"})
    private ProjectGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "srs_version_Id")
    @JsonIgnoreProperties({"project", "srs", "srsDetails"})
    private SrsVersion srsVersion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prototype_Ver_Id")
    @JsonIgnoreProperties({"prototype", "points"})
    private PrototypeVersion prototypeVersion;

    // -----------------------------
    // STATUS FIELDS
    // -----------------------------

    @Column(name = "tst_Case_Approve_Status", length = 2)
    private String testCaseApprove = "N";

    @Column(name = "copy_From_Ver")
    private Integer copyFromVer;

    @Column(name = "version_Submit_Status", length = 1)
    private String versionSubmitStatus = "N";

    @Column(name = "created_By", length = 45)
    private String createdBy;

    @Column(name = "created_On")
    private LocalDateTime createdOn;

    @Column(name = "released_Status", length = 2, nullable = false)
    private String releasedStatus = "N";

    @Column(name = "submitted_Date")
    private LocalDateTime submittedDate;

    @Column(name = "approved_Date")
    private LocalDateTime approvedDate;

    @Column(name = "released_Date")
    private LocalDateTime releasedDate;

    // -----------------------------
    // USER MAPPINGS (SAFE FOR JSON)
    // -----------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_User")
    @JsonIgnoreProperties({"roles", "permissions", "projects"})
    private UserSetup createdUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_User")
    @JsonIgnoreProperties({"roles", "permissions", "projects"})
    private UserSetup approvedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitted_User")
    @JsonIgnoreProperties({"roles", "permissions", "projects"})
    private UserSetup submittedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "released_User")
    @JsonIgnoreProperties({"roles", "permissions", "projects"})
    private UserSetup releasedUser;

    // -----------------------------
    // TRANSIENT COUNTERS (NOT IN DB)
    // -----------------------------

    @Transient private int totCnt;
    @Transient private int passCnt;
    @Transient private int failedCnt;
    @Transient private int resCnt;
    @Transient private int bugsCnt;
    @Transient private int openCnt;
    @Transient private int closedCnt;

    // This is already in DB as prototype_Ver_Id — so do NOT keep transient
    // unless you WANT to override it manually.
    @Transient 
    private Integer prototypeVerId;
}