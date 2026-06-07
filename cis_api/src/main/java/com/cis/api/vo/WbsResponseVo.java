package com.cis.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class WbsResponseVo {

    private Integer cmmiProjectId;
    private Integer projectId;
    private String projectName;
    private Integer moduleId;

    // Effort summary fields (from JSP - hoursWorked attributes)
    private String hoursWorkedOnCoding;
    private String hoursWorkedOnBug;
    private String hoursWorkedOnDefect;

    // Date summary fields
    private Date codingStartDate;
    private Date codingEndDate;
    private Date bugStartDate;
    private Date bugEndDate;
    private Date defectStartDate;
    private Date defectEndDate;

    // Main WBS data grouped by phase/group
    private List<WbsGroupVo> wbsGroups;

    // Flat list of all components (for detail view)
    private List<WbsComponentVo> components;

    // Total row (matching JSP totalPlannedEffort etc.)
    private Double totalPlannedEffort;
    private Double totalActualEffort;

    public WbsResponseVo() {}

    public Integer getCmmiProjectId() { return cmmiProjectId; }
    public void setCmmiProjectId(Integer cmmiProjectId) { this.cmmiProjectId = cmmiProjectId; }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public Integer getModuleId() { return moduleId; }
    public void setModuleId(Integer moduleId) { this.moduleId = moduleId; }

    public String getHoursWorkedOnCoding() { return hoursWorkedOnCoding; }
    public void setHoursWorkedOnCoding(String hoursWorkedOnCoding) { this.hoursWorkedOnCoding = hoursWorkedOnCoding; }

    public String getHoursWorkedOnBug() { return hoursWorkedOnBug; }
    public void setHoursWorkedOnBug(String hoursWorkedOnBug) { this.hoursWorkedOnBug = hoursWorkedOnBug; }

    public String getHoursWorkedOnDefect() { return hoursWorkedOnDefect; }
    public void setHoursWorkedOnDefect(String hoursWorkedOnDefect) { this.hoursWorkedOnDefect = hoursWorkedOnDefect; }

    public Date getCodingStartDate() { return codingStartDate; }
    public void setCodingStartDate(Date codingStartDate) { this.codingStartDate = codingStartDate; }

    public Date getCodingEndDate() { return codingEndDate; }
    public void setCodingEndDate(Date codingEndDate) { this.codingEndDate = codingEndDate; }

    public Date getBugStartDate() { return bugStartDate; }
    public void setBugStartDate(Date bugStartDate) { this.bugStartDate = bugStartDate; }

    public Date getBugEndDate() { return bugEndDate; }
    public void setBugEndDate(Date bugEndDate) { this.bugEndDate = bugEndDate; }

    public Date getDefectStartDate() { return defectStartDate; }
    public void setDefectStartDate(Date defectStartDate) { this.defectStartDate = defectStartDate; }

    public Date getDefectEndDate() { return defectEndDate; }
    public void setDefectEndDate(Date defectEndDate) { this.defectEndDate = defectEndDate; }

    public List<WbsGroupVo> getWbsGroups() { return wbsGroups; }
    public void setWbsGroups(List<WbsGroupVo> wbsGroups) { this.wbsGroups = wbsGroups; }

    public List<WbsComponentVo> getComponents() { return components; }
    public void setComponents(List<WbsComponentVo> components) { this.components = components; }

    public Double getTotalPlannedEffort() { return totalPlannedEffort; }
    public void setTotalPlannedEffort(Double totalPlannedEffort) { this.totalPlannedEffort = totalPlannedEffort; }

    public Double getTotalActualEffort() { return totalActualEffort; }
    public void setTotalActualEffort(Double totalActualEffort) { this.totalActualEffort = totalActualEffort; }
}
