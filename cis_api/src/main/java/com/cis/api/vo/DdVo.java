package com.cis.api.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class DdVo {
    private Integer ddId;
    private ProjectDetailsVo projectId;
    private Integer groupId;
    private ModuleSetupVo moduleId;
    private String ddVenue;
    private String personAttended;
    private Double ddVersion;
    private LocalTime ddTime;
    private String ddPurpose;
    private String ddCreatedBy;
    private String ddLastModified;
    private String ddRemark;
    private String submitStatus;
    private String ddApproveStatus;
    private String torNumber;
    private Integer frmTorVerId;
    private Integer frmTorId;
    private String ddDateDiscussion;
    private String ddPresentStatus;
    private String ddNumber;
    private String personAttendedKran;
    private Integer approvedUser;
    private LocalDate approvedDate;
    private Integer submittedUser;
    private LocalDate submittedDate;
    private Integer createdUser;
    private LocalDate createdDate;
    private String attachmentPath;
    private String attachmentName;
}
