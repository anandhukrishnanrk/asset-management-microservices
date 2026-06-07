package com.cis.api.vo;

import lombok.Data;

@Data
public class DeliverableSchedulesVo {
    private Integer dEffortId;
    private ProjectDetailsVo project;
    private Integer projectOrderId;
    private String deliverableItem;
    private String activity;
    private Integer deliverableTypeId;
    private Integer deliverableFrequency;
    private String deliveredRemarks;
    private String deliverableEnteredOn;
    private Integer deliverableEnteredBy;
    private String deleteStatus;
}
