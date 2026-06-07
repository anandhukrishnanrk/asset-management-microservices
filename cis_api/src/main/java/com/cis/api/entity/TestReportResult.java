package com.cis.api.entity;



import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
@Entity
@Table(name = "test_report_result", schema = "pts" , catalog = "pts")
public class TestReportResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // native → AUTO
    @Column(name = "id")
    private Integer id;

    @Column(name = "test_Report_Id")
    private Integer testReportId;

    @Column(name = "result")
    private String result;

    @Column(name = "updated_On")
    private LocalDate updatedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id")
    private ModuleSetup module;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_Module_Id")
    private SubModuleSetup subModule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "upload_Id")
    private TestReportUpload testReportUpload;

    @Column(name = "bug_Id")
    private String bugId;

    @Column(name = "bug_Desc")
    private String bugDesc;

    @Column(name = "steps_To_Reproduce")
    private String stepsToReproduce;

    @Column(name = "severity_Status")
    private String severity;

    @Column(name = "priority_Status")
    private String priority;

    @Column(name = "types")
    private String types;

    @Column(name = "taskStatus")
    private String taskStatus;

    @Column(name = "svn_Version")
    private Integer svnVersion;

    @Column(name = "status")
    private String status;

    @Column(name = "project_Id")
    private Integer projectId;

    @Column(name = "root_Cause")
    private String rootCause;

    @Column(name = "root_cause_description")
    private String rootCauseDescription;

    @Column(name = "action_taken")
    private String actionTaken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bug_Type_Id")
    private BugType bugType;

    @Column(name = "remark", columnDefinition = "TEXT")
    private String remark;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "version_Id")
//    private TestReportVersion versionId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id")
    private ProjectGroup group;

    // Default Constructor
    public TestReportResult() {
    }

    // Getters and Setters
    // (Generate using IDE to save space)
}

