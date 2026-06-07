package com.cis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "test_case_details", schema = "pts", catalog = "pts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCaseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_Case_Id")
    private Integer testCaseId;

    @Column(name = "test_Case_No")
    private String testCaseNo;

    @Column(name = "test_Case_Details", length = 500)
    private String testCaseDetails;

    @Column(name = "srs_Version_Id")
    private Integer srsVersionId;

    @Column(name = "pass_Fail_Status")
    private String passFailStatus;

    @Column(name = "severity", length = 50)
    private String severityStatus;

    @Column(name = "remarks")
    private String testCaseRemarks;

    // Prevent recursion: testCase → versionHistory → testCase…
    @OneToMany(mappedBy = "testCase", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("testCase")
    private List<TestCaseDetailsVersionHistory> versionHistory;

    // Associations that must NOT go to JSON
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "srs_Id")
    @JsonIgnoreProperties({"testCases", "project"})
    private Srs srs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "updated_By")
    @JsonIgnoreProperties({"tasks", "projects"})
    private UserSetup user;

    @Column(name = "submission_Date")
    private LocalDate submissionDate;

    @Column(name = "completed_Date")
    private LocalDateTime completedDate;
    
    @Column(name = "description")
    private String description;  // Missing field

    @Column(name = "input_Procedure", length = 500)
    private String inputProcedure;  // Missing field

    @Column(name = "test_Case_Type", length = 1)
    private String testCaseType;  // Missing field

    // Add other fields if needed…
}