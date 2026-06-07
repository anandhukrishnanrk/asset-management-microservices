package com.cis.api.entity;

import java.time.LocalDate;

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
@Table(name = "test_case_versions", schema = "pts", catalog = "pts")
@Data
public class TestCaseVersions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_Id")
    private Long versionId;

    @Column(name = "project_Id")
    private Long projectId;

    @Column(name = "group_Id")
    private Long groupId;

    @Column(name = "tst_Case_Version", nullable = false)
    private Double tstCaseVersion;

    @Column(name = "tst_Case_Approve_Status", length = 1)
    private String tstCaseApproveStatus;

    @Column(name = "copy_From_Ver")
    private Long copyFromVer;

    // Foreign Key Mapping
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "srs_version_Id")
    private SrsVersion srsVersion;

    @Column(name = "version_Submit_Status", length = 1)
    private String versionSubmitStatus;

    @Column(name = "revise_Status", length = 1)
    private String reviseStatus;

    @Column(name = "created_On")
    private LocalDate createdOn;

    @Column(name = "created_By", length = 45)
    private String createdBy;

    @Column(name = "approved_User")
    private Long approvedUser;

    @Column(name = "submitted_User")
    private Long submittedUser;

    @Column(name = "released_User")
    private Long releasedUser;

    @Column(name = "submitted_Date")
    private LocalDate submittedDate;

    @Column(name = "approved_Date")
    private LocalDate approvedDate;

    @Column(name = "released_Date")
    private LocalDate releasedDate;

    @Column(name = "created_User")
    private Long createdUser;

    @Column(name = "released_Status", length = 2)
    private String releasedStatus;

    @Column(name = "prototype_Ver_Id")
    private Long prototypeVerId;
}
