package com.cis.api.entity;



import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "srs",schema = "pts", catalog = "pts")
public class Srs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "srs_Id")
    private Integer srsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id", nullable = true)
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id", nullable = true)
    private ModuleSetup module;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "crs_Id", nullable = false)
    private Crs crs;

    @Column(name = "srs_No", length = 45)
    private String srsNo;

    @Column(name = "srs_Details", length = 500)
    private String srsDetails;

    @Column(name = "entry_Type", length = 1)
    private String entryType;

    @Column(name = "crs_Version_Id")
    private Integer crsVersionId;

    @Column(name = "first_Entry_Status", length = 1)
    private String firstEntryStatus;

    @Column(name = "pass_Fail_Status", length = 1)
    private String passFailStatus;

    @Column(name = "severity", length = 1)
    private String severityStatus;

    @Column(name = "bug_Id", length = 45)
    private String bugId;

    @Column(name = "remarks", length = 45)
    private String srsRemarks;

    @Column(name = "srs_Type", length = 1)
    private String srsType;

    @Column(name = "input_Procedure", length = 500)
    private String inputProcedure;

    @Column(name = "delete_Status", length = 1)
    private String deleteStatus;

    @Column(name = "srs_Ref_Id")
    private Integer srsRefId;

    @Column(name = "srsstatus_Fortask", length = 50)
    private String srsStatusForTask;

    @Column(name = "user_Id", length = 10)
    private String userId;

    @Column(name = "Releasescheduled_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseScheduledDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Releasescheduled_User_Id")
    private UserSetup releaseScheduledUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submoduleId")
    private SubModuleSetup submodule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id")
    private ProjectGroup group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_Id")
    private TaskAssignments task;

    @Column(name = "completed_Date")
    @Temporal(TemporalType.DATE)
    private Date completedDate;

    @Column(name = "planned_Start_Date")
    @Temporal(TemporalType.DATE)
    private Date plannedStartDate;

    @Column(name = "planned_End_Date")
    @Temporal(TemporalType.DATE)
    private Date plannedEndDate;

    @Column(name = "planned_Effort")
    private Double plannedEffort;

    @Column(name = "sdd_Entry_Id")
    private Integer sddEntryId;

    @Column(name = "added_On")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedOn;

    @Column(name = "complexity")
    private String complexity;

    @Column(name = "estimation_Screen_Complexity")
    private String estimationScreenComplexity;

    @Column(name = "estimation_Logic_Complexity")
    private String estimationLogicComplexity;

    @Column(name = "estimation_Integration_Complexity")
    private String estimationIntegrationComplexity;

    @Column(name = "estimation_Total_Effort_In_Hours")
    private Integer estimationTotalEffortInHours;

    @Column(name = "estimate_Status")
    private String estimateStatus;

    // Getters and Setters

    public Integer getSrsId() {
        return srsId;
    }

    public void setSrsId(Integer srsId) {
        this.srsId = srsId;
    }

    public ProjectDetails getProject() {
        return project;
    }

    public void setProject(ProjectDetails project) {
        this.project = project;
    }

    public ModuleSetup getModule() {
        return module;
    }

    public void setModule(ModuleSetup module) {
        this.module = module;
    }

    public Crs getCrs() {
        return crs;
    }

    public void setCrs(Crs crs) {
        this.crs = crs;
    }

    public String getSrsNo() {
        return srsNo;
    }

    public void setSrsNo(String srsNo) {
        this.srsNo = srsNo;
    }

    public String getSrsDetails() {
        return srsDetails;
    }

    public void setSrsDetails(String srsDetails) {
        this.srsDetails = srsDetails;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public Integer getCrsVersionId() {
        return crsVersionId;
    }

    public void setCrsVersionId(Integer crsVersionId) {
        this.crsVersionId = crsVersionId;
    }

    public String getFirstEntryStatus() {
        return firstEntryStatus;
    }

    public void setFirstEntryStatus(String firstEntryStatus) {
        this.firstEntryStatus = firstEntryStatus;
    }

    public String getPassFailStatus() {
        return passFailStatus;
    }

    public void setPassFailStatus(String passFailStatus) {
        this.passFailStatus = passFailStatus;
    }

    public String getSeverityStatus() {
        return severityStatus;
    }

    public void setSeverityStatus(String severityStatus) {
        this.severityStatus = severityStatus;
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getSrsRemarks() {
        return srsRemarks;
    }

    public void setSrsRemarks(String srsRemarks) {
        this.srsRemarks = srsRemarks;
    }

    public String getSrsType() {
        return srsType;
    }

    public void setSrsType(String srsType) {
        this.srsType = srsType;
    }

    public String getInputProcedure() {
        return inputProcedure;
    }

    public void setInputProcedure(String inputProcedure) {
        this.inputProcedure = inputProcedure;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getSrsRefId() {
        return srsRefId;
    }

    public void setSrsRefId(Integer srsRefId) {
        this.srsRefId = srsRefId;
    }

    public String getSrsStatusForTask() {
        return srsStatusForTask;
    }

    public void setSrsStatusForTask(String srsStatusForTask) {
        this.srsStatusForTask = srsStatusForTask;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getReleaseScheduledDate() {
        return releaseScheduledDate;
    }

    public void setReleaseScheduledDate(Date releaseScheduledDate) {
        this.releaseScheduledDate = releaseScheduledDate;
    }

//    public UserSetup getReleaseScheduledUser() {
//        return releaseScheduledUser;
//    }
//
//    public void setReleaseScheduledUser(User_setup releaseScheduledUser) {
//        this.releaseScheduledUser = releaseScheduledUser;
//    }
//
//    public submodulesetup getSubmodule() {
//        return submodule;
//    }
//
//    public void setSubmodule(submodulesetup submodule) {
//        this.submodule = submodule;
//    }
//
    public ProjectGroup getGroup() {
        return group;
    }

    public void setGroup(ProjectGroup group) {
        this.group = group;
    }
//
    public TaskAssignments getTask() {
        return task;
    }

    public void setTask(TaskAssignments task) {
        this.task = task;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Date getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(Date plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Date getPlannedEndDate() {
        return plannedEndDate;
    }

    public void setPlannedEndDate(Date plannedEndDate) {
        this.plannedEndDate = plannedEndDate;
    }

    public Double getPlannedEffort() {
        return plannedEffort;
    }

    public void setPlannedEffort(Double plannedEffort) {
        this.plannedEffort = plannedEffort;
    }

    public Integer getSddEntryId() {
        return sddEntryId;
    }

    public void setSddEntryId(Integer sddEntryId) {
        this.sddEntryId = sddEntryId;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getEstimationScreenComplexity() {
        return estimationScreenComplexity;
    }

    public void setEstimationScreenComplexity(String estimationScreenComplexity) {
        this.estimationScreenComplexity = estimationScreenComplexity;
    }

    public String getEstimationLogicComplexity() {
        return estimationLogicComplexity;
    }

    public void setEstimationLogicComplexity(String estimationLogicComplexity) {
        this.estimationLogicComplexity = estimationLogicComplexity;
    }

    public String getEstimationIntegrationComplexity() {
        return estimationIntegrationComplexity;
    }

    public void setEstimationIntegrationComplexity(String estimationIntegrationComplexity) {
        this.estimationIntegrationComplexity = estimationIntegrationComplexity;
    }

    public Integer getEstimationTotalEffortInHours() {
        return estimationTotalEffortInHours;
    }

    public void setEstimationTotalEffortInHours(Integer estimationTotalEffortInHours) {
        this.estimationTotalEffortInHours = estimationTotalEffortInHours;
    }

    public String getEstimateStatus() {
        return estimateStatus;
    }

    public void setEstimateStatus(String estimateStatus) {
        this.estimateStatus = estimateStatus;
    }
    
    @OneToMany(mappedBy = "srs", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("srs")
    private List<TestCaseDetails> testcaselist;
}

