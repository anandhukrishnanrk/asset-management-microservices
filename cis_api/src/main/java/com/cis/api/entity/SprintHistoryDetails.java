package com.cis.api.entity;


import lombok.Data;

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

@Data
@Entity
@Table(name = "sprint_history", schema = "pts", catalog = "pts")
public class SprintHistoryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // better than increment
    @Column(name = "id")
    private Integer sprintHistoryId;

    @Column(name = "sprint_detail_id")
    private Integer sprintDetailId;

    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "srs_no")
    private String srsNo;

    @Column(name = "owner")
    private String owner;

    @Column(name = "Reassign_Status")
    private String reassignStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "entered_on")
    private Date enteredOn;

    // Typo kept to match DB column
    @Column(name = "entrered_by")
    private Integer entreredBy;

    @Column(name = "srs_id")
    private Integer srsId;

    @Temporal(TemporalType.DATE)
    @Column(name = "completed_date")
    private Date completedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "srs_id", insertable = false, updatable = false)
    private Srs srs;
}
