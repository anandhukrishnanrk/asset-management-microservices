package com.cis.api.vo;

import com.cis.api.entity.TestReportResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestReportResultDto {

    private Integer id;
    private Integer testReportId;
    private String result;
    private String updatedOn;

    private Integer moduleId;
    private String moduleName;

    private Integer subModuleId;
    private String subModuleName;

    private Integer uploadId;

    private String bugId;
    private String bugDesc;
    private String stepsToReproduce;

    private String severity;
    private String priority;
    private String types;
    private String taskStatus;
    private Integer svnVersion;
    private String status;
    private Integer projectId;

    private String rootCause;
    private String rootCauseDescription;
    private String actionTaken;

    private Integer bugTypeId;
    private String bugType;

    private String remark;

    private Integer groupId;
    private String groupName;

    // extra
    private String testCaseNo;

    // Constructor to convert Entity → DTO
    public TestReportResultDto(TestReportResult tr, String testCaseNo) {

        this.id = tr.getId();
        this.testReportId = tr.getTestReportId();
        this.result = tr.getResult();
        this.updatedOn = tr.getUpdatedOn() != null ? tr.getUpdatedOn().toString() : null;

        this.moduleId = tr.getModule() != null ? tr.getModule().getModuleId() : null;
        this.moduleName = tr.getModule() != null ? tr.getModule().getModuleName() : null;

        this.subModuleId = tr.getSubModule() != null ? tr.getSubModule().getSubMId() : null;
        this.subModuleName = tr.getSubModule() != null ? tr.getSubModule().getSubmodName() : null;

        this.uploadId = tr.getTestReportUpload() != null ? tr.getTestReportUpload().getUploadId() : null;

        this.bugId = tr.getBugId();
        this.bugDesc = tr.getBugDesc();
        this.stepsToReproduce = tr.getStepsToReproduce();

        this.severity = tr.getSeverity();
        this.priority = tr.getPriority();
        this.types = tr.getTypes();
        this.taskStatus = tr.getTaskStatus();
        this.svnVersion = tr.getSvnVersion();
        this.status = tr.getStatus();
        this.projectId = tr.getProjectId();

        this.rootCause = tr.getRootCause();
        this.rootCauseDescription = tr.getRootCauseDescription();
        this.actionTaken = tr.getActionTaken();

        this.bugTypeId = (tr.getBugType() != null) ? tr.getBugType().getBugTypeId() : null;
        this.bugType = (tr.getBugType() != null) ? tr.getBugType().getBugType() : null;

        this.remark = tr.getRemark();

        this.groupId = tr.getGroup() != null ? tr.getGroup().getId() : null;
        this.groupName = tr.getGroup() != null ? tr.getGroup().getGroupName() : null;

        this.testCaseNo = testCaseNo;
    }
}
