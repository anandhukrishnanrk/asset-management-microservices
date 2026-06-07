package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name = "task_assigmments", schema = "pts", catalog = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_A_Id")
    private Integer tAId;


    // ----------------- Many-to-One Mappings -----------------

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id")
    @JsonIgnore
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_Id")
    @JsonIgnore
    private ModuleSetup module;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id")
    @JsonIgnore
    private ProjectGroup group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_Module_Id")
    @JsonIgnore
    private SubModuleSetup subModule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    @JsonIgnore
    private UserSetup user;

    // ----------------- Simple Properties -----------------

    @Column(name = "task_Type")
    private String taskType;

    @Column(name = "stage_Type")
    private String stageType;

    @Column(name = "activity_Type")
    private String activityType;

    @Column(name = "description")
    private String description;

    @Column(name = "sch_date")
    @Temporal(TemporalType.DATE)
    private Date schDate;

    @Column(name = "review_Type")
    private String reviewType;

    @Column(name = "category_Type")
    private String categoryType;

    @Column(name = "status")
    private String status;

    @Column(name = "remarkss")
    private String remarkss;

    @Column(name = "alloted_By")
    private String allotedBy;

    @Column(name = "delete_Status")
    private String deleteStatus;

    @Column(name = "completedDate")
    @Temporal(TemporalType.DATE)
    private Date completedDate;

    @Column(name = "efforttaken", length = 50)
    private String effortTaken;

    @Column(name = "fromDate")
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Column(name = "fromtime")
    private String fromTime;

    @Column(name = "totime", length = 30)
    private String toTime;

    @Column(name = "schdateforR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date schdateforR;

    @Column(name = "assignedon")
    @Temporal(TemporalType.DATE)
    private Date assignedOn;

    @Column(name = "estimatedtime", length = 50)
    private String estimatedTime;

    @Column(name = "priority", length = 50)
    private String priority;

    @Column(name = "percentagecompleted")
    private String percentageCompleted;


    // ----------------- More LAZY Many-to-One -----------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "srs_Id")
    @JsonIgnore
    private Srs srs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prototype_Id")
    @JsonIgnore
    private PrototypePoints prototype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reassigned_out_by_user")
    @JsonIgnore
    private UserSetup reassignedOutByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reassigned_in_by_user")
    @JsonIgnore
    private UserSetup reassignedInByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reassigned_from_task")
    @JsonIgnore
    private TaskAssignments reassignedFromTask;


    // ----------------- Remaining Fields -----------------

    @Column(name = "cmmi_Project_Component_Id")
    private Integer cmmiProjectComponentId;

    @Column(name = "non_Conformity_Id")
    private Integer nonConformityId;

    @Column(name = "re_Allotment_Of")
    private Integer reAllotmentOf;

    @Column(name = "defect_Status")
    private String defectStatus;

    @Column(name = "effort_Hours", nullable = false)
    private Integer effortHours;

    @Column(name = "effort_Mins")
    private Integer effortMins;

    @Column(name = "planned_Effort_Hours", nullable = false)
    private Integer plannedEffortHours;

    @Column(name = "planned_Effort_Minutes")
    private Integer plannedEffortMins;

    @Column(name = "planned_StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "planned_EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "participant_Status")
    private String participantStatus;

    @Column(name = "main_Task_Id")
    private Integer mainTaskId;

    @Column(name = "planned_Effort_In_Seconds")
    private Long plannedEffortInSeconds;

    @Column(name = "reassigned_out")
    private String reassignedOut;

    @Column(name = "reassigned_in")
    private String reassignedIn;

    @Column(name = "reassign_remarks")
    private String reassignRemarks;

    @Column(name = "reassigned_out_date")
    @Temporal(TemporalType.DATE)
    private Date reassignedOutDate;

    @Column(name = "reassigned_in_date")
    @Temporal(TemporalType.DATE)
    private Date reassignedInDate;
}
