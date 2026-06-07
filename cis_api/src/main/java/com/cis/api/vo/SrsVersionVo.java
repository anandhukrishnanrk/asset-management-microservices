package com.cis.api.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SrsVersionVo {
    private Integer srsVersionId;
    private Double srsVersionNo;
    private String srsReviseStatus;
    private String srsActiveStatus;
    private Integer projectId;
    private Integer groupId;
    private String approvalStatus;
    private Integer crsVersionId;
    private Integer copyFromVer;
    private String versionSubmitStatus;
    private String createdBy;
    private String createdOn;
    private Integer approvedUser;
    private Integer submittedUser;
    private Integer releasedUser;
    private LocalDate submittedDate;
    private LocalDate approvedDate;
    private LocalDate releasedDate;
    private Integer createdUser;
    private String releasedStatus;
    private String reviewStatus;
    private LocalDate reviewDate;
    private String reviewedBy;
}
