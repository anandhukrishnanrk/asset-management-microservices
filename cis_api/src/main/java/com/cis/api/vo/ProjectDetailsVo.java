package com.cis.api.vo;

import lombok.Data;

@Data
public class ProjectDetailsVo {

    private Long projectId;

    private Long clientRegisterId;

    // private ClientVisitGeneralDetails clientVisit;

    // private ContactPersonDetails clientontactPerson;

    private String projectOrderDate;

    private String orderProjectName;

    private Integer seatId;

    private Integer typeSeatId;

    private Integer statusSeatId;

    private String projectStatus;

    private String projectOrderNo;

    private String projectCode;

    private String productionURL;

    private String projectCategory;

    private String projectBudget;

    private String estimatedTimeInitiation;

    private String proposalVersion;

    private String intiateStatus;

    private String projectDescription;

    private String projectType;

    private String projectCurrentStatus;

    private String groupAddStatus;

    private String projectStartDate;

    private String projectEndDate;

    private String activeStatus;

    private String slaStatus;

}
