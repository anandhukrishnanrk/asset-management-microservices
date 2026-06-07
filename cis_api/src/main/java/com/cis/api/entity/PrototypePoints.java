package com.cis.api.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "prototype_points", schema = "pts" ,catalog = "pts")
public class PrototypePoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prototype_Point_Id")
    private Integer prototypePointId;

    // Many-to-one relationship with PrototypeVersion
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "prototype_Version_Id", nullable = false)
//    private PrototypeVersion prototypeVersion;

    // Point added date
    @Column(name = "point_Added_On")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pointAddedOn;

    // Point description
    @Column(name = "point_Description")
    private String pointDescription;

    // Many-to-one relationship with Seat_user_alloted
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_Added_By")
    private SeatUserAlloted pointAddedBy;

    // Complexity fields
    @Column(name = "screen_Complexity")
    private String screenComplexity;

    @Column(name = "logic_Complexity")
    private String logicComplexity;

    @Column(name = "integration_Complexity")
    private String integrationComplexity;

    @Column(name = "total_Complexity")
    private String totalComplexity;

    @Column(name = "total_Effort_In_Hours")
    private Double totalEffortInHours;

    // Many-to-one relationship with Module_setup
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_Id")
    private ModuleSetup module;

    // Many-to-one relationship with Task_Assignments
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "task_Id")
//    private TaskAssignments task;

    // Planned dates
    @Column(name = "planned_Start_Date")
    @Temporal(TemporalType.DATE)
    private Date plannedStartDate;

    @Column(name = "planned_End_Date")
    @Temporal(TemporalType.DATE)
    private Date plannedEndDate;

    // SRS status
    @Column(name = "srs_Status")
    private String srsStatus;

}

