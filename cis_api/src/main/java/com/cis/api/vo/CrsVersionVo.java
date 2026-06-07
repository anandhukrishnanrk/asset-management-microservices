package com.cis.api.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CrsVersionVo {
    private Integer crsVersionId;
    private Integer projectId;
    private Integer groupId;
    private Double crsVersion;
    private String crsApprovalStatus;
    private Integer copyFromVerId;
    private Integer torVerId;
    private String crsVerSubmitStatus;
    private String csrReviseStatus;
    private String crsCreatedOn;
    private String crsCreatedBy;
    private Integer approvedUser;
    private Integer submittedUser;
    private Integer releasedUser;
    private LocalDate submittedDate;
    private LocalDate approvedDate;
    private LocalDate releasedDate;
    private Integer createdUser;
    private String releasedStatus;
    private Integer pmpid;
}
