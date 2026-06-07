package com.cis.api.vo;


import lombok.Data;
import java.util.Date;

@Data
public class TaskAssignmentsDto {

    private Integer tAId;

    // ---- Many-to-One (Flattened to avoid Lazy Proxy issues) ----
    private Integer projectId;
    private String projectName;

    private Integer moduleId;
    private String moduleName;

    private Integer subModuleId;
    private String subModuleName;

    private Integer groupId;
    private String groupName;

    private Integer userId;
    private String userName;

    private Integer srsId;
    private String srsName;

    private Integer prototypeId;
    private String prototypeName;

    private Integer reassignedOutByUserId;
    private String reassignedOutByUserName;

    private Integer reassignedInByUserId;
    private String reassignedInByUserName;

    private Integer reassignedFromTaskId;

    // ---- Simple Fields ----
    private String taskType;
    private String stageType;
    private String activityType;
    private String description;
    private Date schDate;
    private String reviewType;
    private String categoryType;
    private String status;
    private String remarkss;
    private String allotedBy;
    private String deleteStatus;
    private Date completedDate;
    private String effortTaken;
    private Date fromDate;
    private String fromTime;
    private String toTime;
    private Date schdateforR;
    private Date assignedOn;
    private String estimatedTime;
    private String priority;
    private String percentageCompleted;

    private Integer cmmiProjectComponentId;
    private Integer nonConformityId;
    private Integer reAllotmentOf;
    private String defectStatus;
    private Integer effortHours;
    private Integer effortMins;
    private Integer plannedEffortHours;
    private Integer plannedEffortMins;
    private Long plannedEffortInSeconds;

    private Date startDate;
    private Date endDate;

    private String participantStatus;
    private Integer mainTaskId;

    private String reassignedOut;
    private String reassignedIn;
    private String reassignRemarks;
    private Date reassignedOutDate;
    private Date reassignedInDate;
}

