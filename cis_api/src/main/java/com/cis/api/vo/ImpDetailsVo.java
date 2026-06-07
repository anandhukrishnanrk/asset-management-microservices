package com.cis.api.vo;

import java.util.Date;
import lombok.Data;

@Data
public class ImpDetailsVo {
    private Integer impId;
    private ProjectDetailsVo project;
    private ModuleSetupVo module;
    private HierarchySetupVo seat;
    private UserSetupVo user;
    private UserSetupVo approvedBy;
    private Date submissionDate;
    private Double impVersion;
    private String impStatus;
    private String remarks;
    private Date insertDate;
    private UserSetupVo releaseBy;
    private Date releaseDate;
    private Date approvedDate;
    private String releaseStatus;
    private String deleteStatus;
}
