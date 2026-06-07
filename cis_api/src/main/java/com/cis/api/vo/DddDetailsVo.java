package com.cis.api.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DddDetailsVo {
    private Long dddId;
    private ModuleSetupVo module;
    private LocalDate submissionDate;
    private Double dddVersion;
    private String releaseStatus;
    private String dddStatus;
    private String remarks;
    private Long userId;
    private LocalDateTime insertDate;
    private Long groupId;
    private Long projectId;
    private Long approvedBy;
    private Long releaseBy;
    private LocalDateTime releaseDate;
    private LocalDateTime approvedDate;
    private String fileName;
}
