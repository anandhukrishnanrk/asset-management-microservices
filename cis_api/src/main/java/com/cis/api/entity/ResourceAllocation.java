package com.cis.api.entity;


import jakarta.persistence.*;
import java.util.Date;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

//@Entity
//@Table(name = "resource_allocation", schema = "pts", catalog = "pts")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class ResourceAllocation {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "allocation_Id")
//    private Integer id;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = true)
//    @JoinColumn(name = "request_Id")
//    public ResourceRequest resourceRequest;
//
//    @Column(name = "start_Date")
//    public Date startDate;
//
//    @Column(name = "release_Date")
//    public Date releaseDate;
//
//    @Column(name = "extention_Status")
//    public String extentionStatus;
//
//    @Column(name = "status")
//    public String status;
//}


@Data
@Entity
@Table(name = "resource_allocation", schema = "pts", catalog = "pts")
public class ResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocation_Id")
    private Integer allocationId;

    // ===============================
    // Relationships
    // ===============================

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "request_Id", nullable = false)
    private ResourceRequest resourceRequest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "allot_Id")
    private SeatUserAlloted seatUserAlloted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Cost_Id")
    private ResourceProjectCost resourceProjectCost;

    // ===============================
    // Mapped Columns
    // ===============================

    @Column(name = "start_Date")
    private String startDate;

    @Column(name = "release_Date")
    private String releaseDate;

    @Column(name = "status")
    private String allocationStatus;

    @Column(name = "extention_Status")
    private String extentionStatus;

    // ===============================
    // Not Present in Hibernate XML
    // Marked as Transient
    // ===============================

    @Transient
    private String trainedBy;

    @Transient
    private String endDate;

    @Transient
    private Integer percentage;

    @Transient
    private String remarks;

    @Transient
    private Double plannedEffort;

    @Transient
    private Double actualEffort;

    @Transient
    private long additionClosed;

    @Transient
    private long additionTotal;

    @Transient
    private long bugClosed;

    @Transient
    private long bugTotal;

    @Transient
    private long datacorrectionClosed;

    @Transient
    private long datacorrectionTotal;

    @Transient
    private long doubtclearanceClosed;

    @Transient
    private long doubtclearanceTotal;

    @Transient
    private long complaintsClosed;

    @Transient
    private long complaintsTotal;

    @Transient
    private long adminClosed;

    @Transient
    private long adminTotal;

    @Transient
    private long allocationPercentage;

    @Transient
    private Double additionPlannedEffort;

    @Transient
    private Double bugPlannedEffort;

    @Transient
    private Double datacorrectionPlannedEffort;

    @Transient
    private Double doubtclearancePlannedEffort;

    @Transient
    private Double complaintsPlannedEffort;

    @Transient
    private Double adminPlannedEffort;

    @Transient
    private Double additionActualEffort;

    @Transient
    private Double bugActualEffort;

    @Transient
    private Double datacorrectionActualEffort;

    @Transient
    private Double doubtclearanceActualEffort;

    @Transient
    private Double complaintsActualEffort;

    @Transient
    private Double adminActualEffort;

    @Transient
    private Double plannedEffortWBS;

    @Transient
    private Date endDateDis;

    @Transient
    private ResourceWeeklyEffort resourceWeeklyEffort;


    }
