package com.cis.api.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TestCaseVersionsVo {
    private Long versionId;
    private Long projectId;
    private Long groupId;
    private Double tstCaseVersion;
    private String tstCaseApproveStatus;
    private Long copyFromVer;
    private SrsVersionVo srsVersion;
    private String versionSubmitStatus;
    private String reviseStatus;
    private LocalDate createdOn;
    private String createdBy;
    private Long approvedUser;
    private Long submittedUser;
    private Long releasedUser;
    private LocalDate submittedDate;
    private LocalDate approvedDate;
    private LocalDate releasedDate;
    private Long createdUser;
    private String releasedStatus;
    private Long prototypeVerId;
}
