package com.cis.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;



@Data
public class TestCaseDetailsDTO {

    private Integer testCaseId;           // Test Case Id
    private String testCaseNo;            // Test Case No
    private String requirement;           // Requirement → srs.srsNo
    private String testCasePoint;         // Test Case Point → description + inputProcedure
    private String expectedResult;        // Expected Result → testCaseDetails
    private String type;                  // Type → testCaseType (F → Functional, etc.)
    private String module;                // Module → srs.crs.module.moduleName
    private String updatedBy;             // Updated By → user.userName
    private LocalDate updatedOn;          // Updated On → submissionDate

    private String passFailStatus;        // optional
    private String severityStatus;        // optional
    private String testCaseRemarks;       // optional
    private LocalDateTime completedDate;  // optional
}