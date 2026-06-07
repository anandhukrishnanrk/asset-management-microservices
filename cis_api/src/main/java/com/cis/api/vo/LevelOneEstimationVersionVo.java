package com.cis.api.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LevelOneEstimationVersionVo {
    private Integer levelOneEstimationVersionId;
    private Double estimationVersionNo;
    private Integer projectId;
    private Integer estimationCreatedBy;
    private LocalDate estimationCreatedOn;
    private Integer estimationApprovedBy;
    private LocalDate estimationApprovedOn;
    private LocalDate estimationRevisedOn;
    private String reviseStatus;
    private String approveStatus;
    private Integer estimationRevisedBy;
    private Integer groupId;
}
