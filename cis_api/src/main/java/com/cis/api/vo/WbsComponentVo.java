package com.cis.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class WbsComponentVo {

    private Integer componentId;
    private String itemName;
    private Integer groupId;
    private String groupName;
    private Integer auditId;           // null for non-audit components
    private Date plannedStartDate;
    private Date plannedEndDate;
    private Double plannedEffort;
    private Date actualStartDate;
    private Date actualEndDate;
    private Double actualEffort;
    private Integer moduleId;

    public WbsComponentVo() {}

    public Integer getComponentId() { return componentId; }
    public void setComponentId(Integer componentId) { this.componentId = componentId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Integer getGroupId() { return groupId; }
    public void setGroupId(Integer groupId) { this.groupId = groupId; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public Integer getAuditId() { return auditId; }
    public void setAuditId(Integer auditId) { this.auditId = auditId; }

    public Date getPlannedStartDate() { return plannedStartDate; }
    public void setPlannedStartDate(Date plannedStartDate) { this.plannedStartDate = plannedStartDate; }

    public Date getPlannedEndDate() { return plannedEndDate; }
    public void setPlannedEndDate(Date plannedEndDate) { this.plannedEndDate = plannedEndDate; }

    public Double getPlannedEffort() { return plannedEffort; }
    public void setPlannedEffort(Double plannedEffort) { this.plannedEffort = plannedEffort; }

    public Date getActualStartDate() { return actualStartDate; }
    public void setActualStartDate(Date actualStartDate) { this.actualStartDate = actualStartDate; }

    public Date getActualEndDate() { return actualEndDate; }
    public void setActualEndDate(Date actualEndDate) { this.actualEndDate = actualEndDate; }

    public Double getActualEffort() { return actualEffort; }
    public void setActualEffort(Double actualEffort) { this.actualEffort = actualEffort; }

    public Integer getModuleId() { return moduleId; }
    public void setModuleId(Integer moduleId) { this.moduleId = moduleId; }
}