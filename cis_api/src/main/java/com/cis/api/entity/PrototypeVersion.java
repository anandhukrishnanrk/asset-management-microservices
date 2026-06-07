package com.cis.api.entity;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pts.prototype_version")
public class PrototypeVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prototype_Version_Id")
    private Integer prototypeVersionId;

    @Column(name = "prototype_Version_No")
    private Double prototypeVersionNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails projectDetails;

    @Column(name = "prototype_Created_On")
    private Date prototypeCreatedOn;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prototype_Created_By")
    private SeatUserAlloted prototypeCreatedBy;

    // ----------- Getters and Setters -----------

    public Integer getPrototypeVersionId() {
        return prototypeVersionId;
    }

    public void setPrototypeVersionId(Integer prototypeVersionId) {
        this.prototypeVersionId = prototypeVersionId;
    }

    public Double getPrototypeVersionNo() {
        return prototypeVersionNo;
    }

    public void setPrototypeVersionNo(Double prototypeVersionNo) {
        this.prototypeVersionNo = prototypeVersionNo;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public Date getPrototypeCreatedOn() {
        return prototypeCreatedOn;
    }

    public void setPrototypeCreatedOn(Date prototypeCreatedOn) {
        this.prototypeCreatedOn = prototypeCreatedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  SeatUserAlloted getPrototypeCreatedBy() {
        return prototypeCreatedBy;
    }

    public void setPrototypeCreatedBy(SeatUserAlloted prototypeCreatedBy) {
        this.prototypeCreatedBy = prototypeCreatedBy;
    }
}