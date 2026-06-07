package com.cis.api.entity;



import jakarta.persistence.*;
import java.util.Date;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

//@Entity
//@Table(name = "resource_allocation_details", schema = "pts", catalog = "pts")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class ResourceAllocationDetails {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = true)
//    @JoinColumn(name = "allocation_Id")
//    public ResourceAllocation resourceAllocation;
//
//    @Column(name = "allocation_Start_Date")
//    public Date startDate;
//
//    @Column(name = "allocation_End_Date")
//    public Date endDate;
//
//    @Column(name = "status")
//    public String status;
//
//    @Column(name = "allocation_Status")
//    public String allocationStatus;
//
//    @Column(name = "allocation_Percentage")
//    public Integer percentage;
//
//    @Column(name = "remarks")
//    public String remarks;
//
//    @Column(name = "updated_On")
//    public Date updatedOn;
//
//    @Column(name = "extention_Type")
//    public String extentionType;
//}
//
//

@Entity
@Table(name = "resource_allocation_details", schema = "pts", catalog = "pts")
public class ResourceAllocationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // many-to-one mapping
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "allocation_Id", nullable = false)
    private ResourceAllocation resourceAllocation;

    @Column(name = "allocation_Status")
    private String allocationStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "allocation_End_Date")
    private Date endDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "allocation_Start_Date")
    private Date startDate;

    @Column(name = "extention_Type")
    private String extentionType = "N"; // N-Not Extension

    @Column(name = "allocation_Percentage")
    private Integer percentage;

    @Column(name = "status")
    private String status;

    // ===============================
    // Not present in Hibernate XML
    // Marking as Transient
    // ===============================

    @Transient
    private String remarks;

    @Transient
    private Date updatedOn;

    @Transient
    private Double costTillNow;

    @Transient
    private Double plannedEffort;

    @Transient
    private Double forecastCost;

    @Transient
    private Double actualCost;

    @Transient
    private Double actualEffort;

    @Transient
    private String startDateStr;

    @Transient
    private String endDateStr;

    @Transient
    private Double leaveDays;

    @Transient
    private Integer userId;

    @Transient
    private String userSkillSet;

    @Transient
    private Integer skillValue;

    // ===============================
    // Getters and Setters
    // ===============================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ResourceAllocation getResourceAllocation() {
        return resourceAllocation;
    }

    public void setResourceAllocation(ResourceAllocation resourceAllocation) {
        this.resourceAllocation = resourceAllocation;
    }

    public String getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(String allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getExtentionType() {
        return extentionType;
    }

    public void setExtentionType(String extentionType) {
        this.extentionType = extentionType;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Transient getters/setters omitted for brevity
}
