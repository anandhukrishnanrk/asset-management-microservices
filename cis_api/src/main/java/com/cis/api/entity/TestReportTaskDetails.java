package com.cis.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "test_report_task_details", schema = "pts", catalog = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestReportTaskDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "test_Report_Id")
    private Integer testReportId;

    @Column(name = "schedule_Date")
    @Temporal(TemporalType.DATE)
    private Date scheduleDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_Id", nullable = false)
    private TaskAssignments task;

    @Column(name = "severity_Status")
    private String severity;

    @Column(name = "priority_Status")
    private String priority;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "svn_Version")
    private Integer svnVersion;

    @Column(name = "test_Result_Id")
    private Integer testResultId;

    @Column(name = "status")
    private String status;
}

