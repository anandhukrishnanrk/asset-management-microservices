package com.cis.api.entity;

 

import jakarta.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "srs_ver_history",schema = "pts", catalog="pts")
public class SrsVersionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srs_Ver_His_Id")
    private Integer srsVerHisId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "srs_Id", nullable = false)
    private Srs srsDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "srs_version_Id", nullable = false)
    private SrsVersion srsVersion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prototype_Id")
    private PrototypePoints prototype;

    @Column(name = "status")
    private String status;

    @Column(name = "srs_Point")
    private String srsPoint;

    @Column(name = "srs_Ref_No")
    private String srsRefNo;

    @Column(name = "request_Status")
    private String requestStatus;

    @Column(name = "deleted_On")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedOn;

    @Column(name = "delete_Remarks")
    private String deleteRemarks;

    @Column(name = "added_On")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedOn;


    // ------------------------
    // Getters & Setters
    // ------------------------
    public Integer getSrsVerHisId() {
        return srsVerHisId;
    }

    public void setSrsVerHisId(Integer srsVerHisId) {
        this.srsVerHisId = srsVerHisId;
    }

    public Srs getSrsDetails() {
        return srsDetails;
    }

    public void setSrsDetails(Srs srsDetails) {
        this.srsDetails = srsDetails;
    }

    public SrsVersion getSrsVersion() {
        return srsVersion;
    }

    public void setSrsVersion(SrsVersion srsVersion) {
        this.srsVersion = srsVersion;
    }

    public PrototypePoints getPrototype() {
        return prototype;
    }

    public void setPrototype(PrototypePoints prototype) {
        this.prototype = prototype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSrsPoint() {
        return srsPoint;
    }

    public void setSrsPoint(String srsPoint) {
        this.srsPoint = srsPoint;
    }

    public String getSrsRefNo() {
        return srsRefNo;
    }

    public void setSrsRefNo(String srsRefNo) {
        this.srsRefNo = srsRefNo;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

    public String getDeleteRemarks() {
        return deleteRemarks;
    }

    public void setDeleteRemarks(String deleteRemarks) {
        this.deleteRemarks = deleteRemarks;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }
}

