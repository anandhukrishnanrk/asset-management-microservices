package com.cis.api.entity;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "resource_weekly_effort", schema = "pts", catalog = "pts")
public class ResourceWeeklyEffort {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "effort_Id")
    private Integer effort_Id;

    @Column(name = "week_Id")
    private Integer weekId;

    // ===============================
    // Relationships
    // ===============================

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id")
    private ProjectDetails projectDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "allot_Id")
    private  SeatUserAlloted seatUserAlloted;

    // ===============================
    // Column Mappings
    // ===============================

    @Column(name = "weekly_Percentage")
    private Integer weeklyPercentage;

    @Temporal(TemporalType.DATE)
    @Column(name = "week_Start_Date")
    private Date weekStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "week_End_Date")
    private Date weekEndDate;

    @Column(name = "remarks")
    private String weeklyRemarks;

    // ===============================
    // Not Present in Hibernate XML
    // ===============================

    @Transient
    private ResourceRequest resourceRequest;

    // ===============================
    // Getters and Setters
    // ===============================

    public Integer getEffort_Id() {
        return effort_Id;
    }

    public void setEffort_Id(Integer effort_Id) {
        this.effort_Id = effort_Id;
    }

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public SeatUserAlloted getSeatUserAlloted() {
        return seatUserAlloted;
    }

    public void setSeatUserAlloted(SeatUserAlloted seatUserAlloted) {
        this.seatUserAlloted = seatUserAlloted;
    }

    public Integer getWeeklyPercentage() {
        return weeklyPercentage;
    }

    public void setWeeklyPercentage(Integer weeklyPercentage) {
        this.weeklyPercentage = weeklyPercentage;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public String getWeeklyRemarks() {
        return weeklyRemarks;
    }

    public void setWeeklyRemarks(String weeklyRemarks) {
        this.weeklyRemarks = weeklyRemarks;
    }
}
