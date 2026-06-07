package com.cis.api.vo;

import lombok.Data;

@Data
public class TestPlanUploadsVo {
    private Integer uploadId;
    private ProjectDetailsVo projectDetails;
    private String attachmentDate;
    private String remarks;
    private String attachement;
    private String attachedBy;
    private String testPlanNo;
    private String releaseDate;
    private String releaseBy;
    private Double testPlanVersion;
    private String releaseStatus;
    private String clientViewStatus;
    private String deleteStatus;
}
