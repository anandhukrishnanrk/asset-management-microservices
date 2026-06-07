package com.cis.api.entity;


import lombok.Data;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Data
@Entity
@Table(name = "sprint_details", schema = "pts", catalog = "pts")
public class SprintDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better than "increment"
    @Column(name = "sprint_id")
    private int sprintId;

    @Column(name = "sprint_name")
    private String sprintName;

    @Temporal(TemporalType.DATE)
    @Column(name = "sprint_start_date")
    private Date sprintStartDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "sprint_end_date")
    private Date sprintEndDate;

    @Column(name = "sprint_history_status")
    private String sprintHistorStatus;

    @Column(name = "sprint_phase")
    private String sprintPhase;

    @Column(name = "sprint_project_id")
    private int sprintProjectId;

    @Temporal(TemporalType.DATE)
    @Column(name = "sprint_revised_date")
    private Date sprintReviseDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date_second")
    private Date startDateSecond;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date_second")
    private Date endDateSecond;

//    @Column(name = "start_week")
//    private String startWeek;
//
//    @Column(name = "end_week")
//    private String endWeek;
}
