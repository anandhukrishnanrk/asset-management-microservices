package com.cis.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class TicketVo {

    private Integer requestId;
    private String ticketNo;
    private String requestType;
    private String severity;
    private String prority;
    private String reqDescription;
    private String subject;
    private String status;
    private String assignStatus;
    private String closeStatus;
    private String requestFrom;
    private String requestMode;
    private String phoneNo;
    private String emailId;
    private String tag;
    private String category;
    private String rootCause;
    private String actionTaken;
    private String progress;
    private String uatStatus;
    private String slaStatus;
    private String timeTaken;

    private Date requestDate;
    private Date enteredDate;
    private Date closeDate;
    private Date scheduledDate;
    private Date exceptedReleaseDate;
    private Date resolutionEndTime;

    // Entered user info (flat - no nested objects)
    private Integer enteredUserId;
    private String enteredUserName;

    // Assigned user info
    private String assignedTo;
    private Date assignedOn;

    // Module info
    private Integer moduleId;
    private String moduleName;

    // Project info
    private Integer projectId;
    private String projectName;

    // Task assigned user
    private String taskAssignedUserName;

    // Root cause
    private String rootCauseName;

    // Effort
    private Double plannedEffort;
    private Integer plannedEffortHours;
    private Integer plannedEffortMins;

    public Integer getRequestId() { return requestId; }
    public void setRequestId(Integer requestId) { this.requestId = requestId; }

    public String getTicketNo() { return ticketNo; }
    public void setTicketNo(String ticketNo) { this.ticketNo = ticketNo; }

    public String getRequestType() { return requestType; }
    public void setRequestType(String requestType) { this.requestType = requestType; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getPrority() { return prority; }
    public void setPrority(String prority) { this.prority = prority; }

    public String getReqDescription() { return reqDescription; }
    public void setReqDescription(String reqDescription) { this.reqDescription = reqDescription; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignStatus() { return assignStatus; }
    public void setAssignStatus(String assignStatus) { this.assignStatus = assignStatus; }

    public String getCloseStatus() { return closeStatus; }
    public void setCloseStatus(String closeStatus) { this.closeStatus = closeStatus; }

    public String getRequestFrom() { return requestFrom; }
    public void setRequestFrom(String requestFrom) { this.requestFrom = requestFrom; }

    public String getRequestMode() { return requestMode; }
    public void setRequestMode(String requestMode) { this.requestMode = requestMode; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }

    public String getActionTaken() { return actionTaken; }
    public void setActionTaken(String actionTaken) { this.actionTaken = actionTaken; }

    public String getProgress() { return progress; }
    public void setProgress(String progress) { this.progress = progress; }

    public String getUatStatus() { return uatStatus; }
    public void setUatStatus(String uatStatus) { this.uatStatus = uatStatus; }

    public String getSlaStatus() { return slaStatus; }
    public void setSlaStatus(String slaStatus) { this.slaStatus = slaStatus; }

    public String getTimeTaken() { return timeTaken; }
    public void setTimeTaken(String timeTaken) { this.timeTaken = timeTaken; }

    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date requestDate) { this.requestDate = requestDate; }

    public Date getEnteredDate() { return enteredDate; }
    public void setEnteredDate(Date enteredDate) { this.enteredDate = enteredDate; }

    public Date getCloseDate() { return closeDate; }
    public void setCloseDate(Date closeDate) { this.closeDate = closeDate; }

    public Date getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(Date scheduledDate) { this.scheduledDate = scheduledDate; }

    public Date getExceptedReleaseDate() { return exceptedReleaseDate; }
    public void setExceptedReleaseDate(Date exceptedReleaseDate) { this.exceptedReleaseDate = exceptedReleaseDate; }

    public Date getResolutionEndTime() { return resolutionEndTime; }
    public void setResolutionEndTime(Date resolutionEndTime) { this.resolutionEndTime = resolutionEndTime; }

    public Integer getEnteredUserId() { return enteredUserId; }
    public void setEnteredUserId(Integer enteredUserId) { this.enteredUserId = enteredUserId; }

    public String getEnteredUserName() { return enteredUserName; }
    public void setEnteredUserName(String enteredUserName) { this.enteredUserName = enteredUserName; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }

    public Date getAssignedOn() { return assignedOn; }
    public void setAssignedOn(Date assignedOn) { this.assignedOn = assignedOn; }

    public Integer getModuleId() { return moduleId; }
    public void setModuleId(Integer moduleId) { this.moduleId = moduleId; }

    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getTaskAssignedUserName() { return taskAssignedUserName; }
    public void setTaskAssignedUserName(String taskAssignedUserName) { this.taskAssignedUserName = taskAssignedUserName; }

    public String getRootCauseName() { return rootCauseName; }
    public void setRootCauseName(String rootCauseName) { this.rootCauseName = rootCauseName; }

    public Double getPlannedEffort() { return plannedEffort; }
    public void setPlannedEffort(Double plannedEffort) { this.plannedEffort = plannedEffort; }

    public Integer getPlannedEffortHours() { return plannedEffortHours; }
    public void setPlannedEffortHours(Integer plannedEffortHours) { this.plannedEffortHours = plannedEffortHours; }

    public Integer getPlannedEffortMins() { return plannedEffortMins; }
    public void setPlannedEffortMins(Integer plannedEffortMins) { this.plannedEffortMins = plannedEffortMins; }
}
