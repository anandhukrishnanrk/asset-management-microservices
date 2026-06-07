package com.cis.api.vo;



import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrsVo {

    private Integer crsId;

    private Integer projectId;
    private Integer moduleId;
    private Integer crsRefId;
    private Integer submoduleId;
    private Integer groupId;

    private String crsNo;
    private String crsDetails;
    private String torRefNo;
    private String crsSubmitStatus;
    private String crsEntryType;

    private String crsRemark;
    private Integer torVersionId;
    private Integer copyOfCrsEntryId;
    private String firstCrsEntryStatus;

    private String crsType;
    private String userId;

    private String percentageCompleted;
    private String crsStatusForTask;
    private String taskId;

    private String completedDate;
    private String effortTaken;
    private String fromDate;
    private String fromTime;
    private String toTime;

    private String attachment;

    private String dependentRequirement;
    private String deleteStatus;
    private String approveStatus;
    private String types;
}
