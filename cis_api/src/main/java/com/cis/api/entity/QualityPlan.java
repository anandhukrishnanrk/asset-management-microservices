package com.cis.api.entity;

import java.time.LocalDate;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cmmi_quality_plan", catalog = "pts", schema = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QualityPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cmmi_Project_Id")
    private Integer cmmiProjectId;

    @Temporal(TemporalType.DATE)
    @Column(name = "qp_Date")
    private LocalDate qpDate;

    @Column(name = "attachment_Name")
    private String attachmentName;

    @Column(name = "version_Number")
    private String versionNumber;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "added_By")
    private SeatUserAlloted addedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_By")
    private SeatUserAlloted approvedBy;

    @Column(name = "delete_status")
    private String deleteStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "approved_On")
    private Date approvedOn;
}
