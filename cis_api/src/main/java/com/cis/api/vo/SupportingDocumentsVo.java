package com.cis.api.vo;

import lombok.Data;

@Data
public class SupportingDocumentsVo {

    private Long supportingDocId;
    private CmmiProjectVo cmmiProject;
    private String submissionDate;
    private String attachmentName;
    private String remarks;
    private UserSetupVo submittedBy;
    private String tempDate;
    private String docType;
    private String deleteStatus;
}
