package com.cis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "crs_version", schema = "pts", catalog = "pts")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CrsVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crs_version_Id")
    private Integer crsVersionId;

    @Column(name = "project_Id")
    private Integer projectId;

    @Column(name = "group_Id")
    private Integer groupId;

    @Column(name = "crs_Version")
    private Double crsVersion;

    @Column(name = "crs_approval_Status", length = 5)
    private String crsApprovalStatus;

    @Column(name = "copy_from_Ver_Id")
    private Integer copyFromVerId;

    @Column(name = "tor_ver_Id")
    private Integer torVerId;

    @Column(name = "crs_ver_submit_Status", length = 5)
    private String crsVerSubmitStatus;

    @Column(name = "csr_revise_Status", length = 5)
    private String csrReviseStatus;

    @Column(name = "crs_created_On", length = 45)
    private String crsCreatedOn;

    @Column(name = "crs_created_By", length = 45)
    private String crsCreatedBy;

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

    @Column(name = "pmpid")
    private Integer pmpid;
}
