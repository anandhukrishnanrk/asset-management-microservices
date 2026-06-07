package com.cis.api.vo;

import java.util.Date;
import lombok.Data;

@Data
public class CmmiComponentRescheduleVo {
    private Integer id;
    private Integer componentId;
    private CmmiProjectComponentsVo component;
    private Date plannedStartDate;
    private Date plannedEndDate;
    private Double plannedEffort;
    private String reason;
    private SeatUserAllottedVo actionBy;
    private Date actionOn;
}
