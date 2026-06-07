package com.cis.api.vo;

import lombok.Data;

@Data
public class TestReportHistoryVo {
    private Integer tHisId;
    private ProjectDetailsVo projectId;
    private String testCaseDetails;
    private String inputProcedure;
    private String passFailStatus;
    private String severityStatus;
    private String priorityStatus;
    private String remarks;
}
