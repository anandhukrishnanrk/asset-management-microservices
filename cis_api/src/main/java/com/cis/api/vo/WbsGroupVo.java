package com.cis.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class WbsGroupVo {

    private Integer id;
    private String groupName;
    private Date plannedStartDate;
    private Date plannedEndDate;
    private Double plannedEffort;
    private Date actualStartDate;
    private Date actualEndDate;
    private Double actualEffort;
    private List<WbsComponentVo> components;

    public WbsGroupVo() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

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

    public List<WbsComponentVo> getComponents() { return components; }
    public void setComponents(List<WbsComponentVo> components) { this.components = components; }
}
