package com.cis.api.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "srs_version", schema = "pts", catalog = "pts")
@Data
public class SrsVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srs_version_Id")
    private Integer srsVersionId;

    @Column(name = "srs_Version_No", nullable = false)
    private Double srsVersionNo;

    @Column(name = "srs_Revise_Status", length = 1)
    private String srsReviseStatus;

    @Column(name = "srs_Active_Status", length = 1)
    private String srsActiveStatus;

    @Column(name = "project_Id")
    private Integer projectId;

    @Column(name = "group_Id")
    private Integer groupId;

    @Column(name = "approval_status", length = 1)
    private String approvalStatus;

    @Column(name = "crs_Version_Id")
    private Integer crsVersionId;

    @Column(name = "copy_From_Ver")
    private Integer copyFromVer;

    @Column(name = "version_Submit_Status", length = 1)
    private String versionSubmitStatus;

    @Column(name = "created_By", length = 45)
    private String createdBy;

    // Stored as VARCHAR in DB (not ideal)
    @Column(name = "created_On", length = 45)
    private String createdOn;

    @Column(name = "approved_User")
    private Integer approvedUser;

    @Column(name = "submitted_User")
    private Integer submittedUser;

    @Column(name = "released_User")
    private Integer releasedUser;

    @Column(name = "submitted_Date")
    private LocalDate submittedDate;

    @Column(name = "approved_Date")
    private LocalDate approvedDate;

    @Column(name = "released_Date")
    private LocalDate releasedDate;

    @Column(name = "created_User")
    private Integer createdUser;

    @Column(name = "released_Status", length = 2)
    private String releasedStatus;

    @Column(name = "review_Status", length = 2)
    private String reviewStatus;

    @Column(name = "review_Date")
    private LocalDate reviewDate;

    @Column(name = "reviewed_By", length = 45)
    private String reviewedBy;
}
