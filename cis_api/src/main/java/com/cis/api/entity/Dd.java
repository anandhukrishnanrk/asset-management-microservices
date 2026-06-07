package com.cis.api.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "dd", schema = "pts", catalog = "pts")
@Data
public class Dd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dd_Id")
    private Integer ddId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id")
    private ProjectDetails projectId;

    @Column(name = "group_Id")
    private Integer groupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id")
    private ModuleSetup moduleId;

    @Column(name = "dd_Venue", length = 150)
    private String ddVenue;

    @Lob
    @Column(name = "person_Attended")
    private String personAttended;

    @Column(name = "dd_Version")
    private Double ddVersion;

    @Column(name = "dd_Time")
    private LocalTime ddTime;

    @Column(name = "dd_Purpose", length = 1050)
    private String ddPurpose;

    @Column(name = "dd_Created_By", length = 100)
    private String ddCreatedBy;

    @Column(name = "dd_Last_Modified", length = 100)
    private String ddLastModified;

    @Column(name = "dd_Remark", length = 2000)
    private String ddRemark;

    @Column(name = "submit_Status", length = 1)
    private String submitStatus;

    @Column(name = "dd_Approve_Status", length = 1)
    private String ddApproveStatus;

    @Column(name = "tor_Number", length = 45)
    private String torNumber;

    @Column(name = "frm_tor_ver_Id")
    private Integer frmTorVerId;

    @Column(name = "frm_tor_Id")
    private Integer frmTorId;

    // Stored as VARCHAR in DB
    @Column(name = "dd_Date_Discussion", length = 100)
    private String ddDateDiscussion;

    @Column(name = "dd_present_Status", length = 50)
    private String ddPresentStatus;

    @Column(name = "dd_Number", length = 500)
    private String ddNumber;

    @Lob
    @Column(name = "person_Attended_Kran")
    private String personAttendedKran;

    @Column(name = "approved_User")
    private Integer approvedUser;

    @Column(name = "approved_Date")
    private LocalDate approvedDate;

    @Column(name = "submitted_User")
    private Integer submittedUser;

    @Column(name = "submitted_Date")
    private LocalDate submittedDate;

    @Column(name = "created_User")
    private Integer createdUser;

    @Column(name = "created_Date")
    private LocalDate createdDate;

    @Lob
    @Column(name = "attachment_Path")
    private String attachmentPath;

    @Lob
    @Column(name = "attachment_Name")
    private String attachmentName;
}
