package com.cis.api.entity;



import jakarta.persistence.*;
import java.util.Date;


import java.util.Date;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(schema = "pts", name = "resource_request", catalog = "pts")
//public class ResourceRequest {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "request_Id")
//	private Integer requestId;
//	
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = ProjectDetails.class)
//	@JoinColumn(name = "project_Id")
//	public ProjectDetails projectDetails;
//	
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = SeatUserAlloted.class)
//	@JoinColumn(name = "requested_By")
//	public SeatUserAlloted requestedBy;
//	
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = ResourceProject.class)
//	@JoinColumn(name = "resource_Id")
//	public ResourceProject resourceProject;
//	
//	@Column(name = "requested_On")
//	public Date requestedOn;
//	
//	@Column(name = "start_Date")
//	public Date startDate;
//	
//	@Column(name = "end_Date")
//	public Date endDate;
//	
//	@Column(name = "allocation_Status")
//	public String allocationStatus;
//	
//	@Column(name = "percentage")
//	public Integer percentage;
//	
//	@Column(name = "request_Remarks")
//	public String requestRemarks;
//	
//	@Column(name = "reject_Remarks")
//	public String rejectRemarks;
//	
//	@Column(name = "required_Skills")
//	public String requiredSkills;
//	
//	@Column(name = "status")
//	public String status;
//	
//	@Column(name = "rmg_Status")
//	public String rmgStatus;
//	
//	@Column(name = "pmo_Status")
//	public String pmoStatus;
//	
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = ResourceRegionSetup.class)
//	@JoinColumn(name = "region_id")
//	public ResourceRegionSetup resourceRegionSetup;
//	
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = SeatUserAlloted.class)
//	@JoinColumn(name = "denied_By")
//	public SeatUserAlloted deniedBy;
//	
//	
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = SeatUserAlloted.class)
//	@JoinColumn(name = "pmo_Denied_By")
//	public SeatUserAlloted pmoDeniedBy;
//	
//	@Column(name = "denied_On")
//	public Date deniedOn;
//	
//	@Column(name = "pmo_Denied_On")
//	public Date pmoDeniedOn;
//	
//	
//	@Transient
//	public String startDateStr;
//	
//	@Transient
//	public String endDateStr;
//	
//	@Transient 
//	private String roleName;
//
//}











@Entity
@Table(name = "resource_request", schema = "pts", catalog = "pts")
@Data
public class ResourceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_Id")
    private Integer requestId;

    @Column(name = "project_Id", nullable = false)
    private Integer projectId;

    // ===============================
    // Relationships
    // ===============================

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "resource_Id", nullable = false)
    private ResourceProject resourceProject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "requested_By")
    private SeatUserAlloted seatUserAlloted;

    // ===============================
    // Column Mappings
    // ===============================

    @Column(name = "requested_On")
    private String requestedOn;

    @Column(name = "start_Date")
    private String startDate;

    @Column(name = "end_Date")
    private String endDate;

    @Column(name = "allocation_Status")
    private String allocationStatus;

    @Column(name = "percentage")
    private Integer percentage;

    @Column(name = "request_Remarks")
    private String requestRemarks;

    @Column(name = "reject_Remarks")
    private String rejectRemarks;

    @Column(name = "required_Skills")
    private String requiredSkills;

    @Column(name = "status")
    private String status;

    // ===============================
    // Not Present in Hibernate XML
    // ===============================

    @Transient
    private ResourceAllocation resourceAllocation;

    @Transient
    private String updatedBy;

    @Transient
    private Date updatedOn;

    // ===============================
    // Getters and Setters
    // ===============================

}