package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "testreport_history", schema = "pts" , catalog = "pts")
@Data
public class TestReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_His_Id")
    private Integer tHisId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id", nullable = true)
    private ProjectDetails projectId;

    // @ManyToOne(fetch = FetchType.EAGER, optional = false)
    // @JoinColumn(name = "testreport_Version_Id", nullable = false)
    // private Testreports_version testreportversion;

     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "testcase_Id", nullable = false)
     private TestCaseDetails testcasedetails;

    @Column(name = "test_Case_Details", length = 2)
    private String testCaseDetails;

    @Column(name = "inputProcedure", length = 10)
    private String inputProcedure;

    @Column(name = "passFailStatus", length = 1)
    private String passFailStatus;

    @Column(name = "severityStatus", length = 45)
    private String severityStatus;

    @Column(name = "priority_Status", length = 45)
    private String priorityStatus;

    @Column(name = "remarks", length = 200)
    private String remarks;

}
