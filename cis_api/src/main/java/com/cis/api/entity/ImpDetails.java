package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "imp_details", catalog = "pts", schema = "pts")
@Data
@NoArgsConstructor
public class ImpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imp_Id")
    private Integer impId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id")
    private ModuleSetup module;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_Id", nullable = false)
    private HierarchySetup seat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private UserSetup user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_By")
    private UserSetup approvedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submission_Date", updatable = false)
    private Date submissionDate;

    @Column(name = "imp_Version")
    private Double impVersion;

    @Column(name = "imp_Status", length = 20)
    private String impStatus;

    @Lob
    @Column(name = "remarks")
    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_Date", updatable = false)
    private Date insertDate;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "group_Id")
//    private ProjectGroup group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "release_By")
    private UserSetup releaseBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "release_Date")
    private Date releaseDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approved_Date")
    private Date approvedDate;

    @Column(name = "release_Status", length = 20)
    private String releaseStatus;

    @Column(name = "delete_status", length = 20)
    private String deleteStatus;
}