package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmp_general_details")
public class PmpGeneralDetails {

    @Id
    @Column(name = "pmp_Gd_Id")
    private Integer pmpGdId;   // assigned (no auto generation)

    /* ===================== RELATIONSHIPS ===================== */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id")
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pmp_Id")
    private PmpGdReleased pmp;

    // Self reference (revision from)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gd_revision_From_Id")
    private PmpGeneralDetails pmpGd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "released_User_Id")
    private UserSetup approvedUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submitted_User_Id")
    private UserSetup submittedUser;

    /* ===================== BASIC FIELDS ===================== */

    @Column(name = "project_Start_Date")
    private String projectStartDate;

    @Column(name = "project_End_Date")
    private String projectEndDate;

    @Column(name = "project_Development_Strategy", length = 1500)
    private String projectDevelopmentStrategy;

    @Column(name = "issue_Management_Policy", length = 1500)
    private String issueManagementPolicy;

    @Column(name = "project_Schedule", length = 1500)
    private String projectSchedule;

    @Column(name = "required_Recourses", length = 1500)
    private String requiredRecourses;

    @Column(name = "pmp_Gd_Remark", length = 1500)
    private String pmpGdRemark;

    @Column(name = "gd_Ver")
    private Double gdVer;

    @Column(name = "gd_Approval_Status", length = 1)
    private String gdApprovalStatus;

    @Column(name = "gd_Submit_Status", length = 1)
    private String gdSubmitStatus;

    @Column(name = "gd_revision_Status", length = 1)
    private String gdRevisionStatus;

    @Column(name = "pending_Status", length = 1)
    private String pendingStatus;

    @Column(name = "released_Date")
    private String releasedDate;

    @Column(name = "submitted_Date")
    private String submittedDate;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "projectdescrption")
    private String projectdescrption;

    @Column(name = "dar_Details")
    private String darDetails;

    @Column(name = "car_Details")
    private String carDetails;

    @Column(name = "risk_Details")
    private String riskDetails;

    @Column(name = "process_Tailoring")
    private String processTailoring;

    @Column(name = "acceptance_Exit_Criteria")
    private String acceptanceExitCriteria;

    @Column(name = "measurement_Details")
    private String measurementDetails;

    @Column(name = "templates_Other_Tailoring")
    private String templatesAndOtherTailoring;
}
