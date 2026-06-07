package com.cis.api.vo;

import java.util.Date;
import java.util.Set;
import lombok.Data;

@Data
public class CmmiProjectComponentsVo {
    private Integer id;
    private CmmiSubgroupVo component;
    private String inclusionStatus;
    private String status;
    private Date plannedStartDate;
    private Date plannedEndDate;
    private Date actualStartDate;
    private Date actualEndDate;
    private Double plannedEffort;
    private Double actualEffort;
    private UserSetupVo responsiblePerson;
    private Integer cmmiProjectId;
    private CmmiProjectVo cmmiProject;
    private String remarks;
    private SeatUserAllottedVo actionBy;
    private Date actionDatetime;
    private Set<CmmiComponentRescheduleVo> reschedules;
    private Integer moduleId;
}
