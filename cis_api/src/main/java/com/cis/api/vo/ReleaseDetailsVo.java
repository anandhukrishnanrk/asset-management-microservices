package com.cis.api.vo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReleaseDetailsVo {
    private Long releaseId;
    private ModuleSetupVo module;
    private LocalDate submissionDate;
    private String releaseVersion;
    private String releaseStatus;
    private LocalDateTime releaseDate;
    private String remarks;
    private SeatUserAllottedVo seat;
    private LocalDateTime insertDate;
    private Integer groupId;
    private ProjectDetailsVo project;
    private UserSetupVo user;
    private Integer approvedBy;
    private LocalDateTime approvedDate;
    private Integer releaseBy;
    private String releaseStatusFlag;
    private Integer projectSize;
}
