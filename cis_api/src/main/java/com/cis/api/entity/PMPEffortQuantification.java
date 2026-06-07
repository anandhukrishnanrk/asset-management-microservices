package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmpeffortquantification" , schema = "pts", catalog = "pts")
public class PMPEffortQuantification {

    @Id
    @Column(name = "pmp_effort_Id")
    private Integer pmpEffortId; // matches generator "assigned" in Hibernate XML

    @Column(name = "activity", length = 100)
    private String activity;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "milestone", length = 2, nullable = false)
    private String milestone = "N";

//    @Column(name = "doctype")
//    private String doctype;

//    @Column(name = "checkstatus")
//    private String checkstatus = "N";

    @Transient
    private Long hourss;

    // -------------------- Relationships --------------------

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id", nullable = true)
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pmp_Id", nullable = true)
    private PmpGdReleased pmp;

    // -------------------- Constructors --------------------

    public PMPEffortQuantification(String activity, long hourss) {
        this.activity = activity;
        this.hours = (int) hourss;
    }

    public PMPEffortQuantification(String activity, Integer hours) {
        this.activity = activity;
        this.hours = hours;
    }

    public PMPEffortQuantification() {
    }
}
