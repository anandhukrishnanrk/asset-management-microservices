package com.cis.api.vo;

import lombok.Data;

@Data
public class TestPlanDetailsVo {
    private Integer testPlanId;
    private ProjectDetailsVo projectDetails;
    private ProjectGroupVo group;
    private String testplanNo;
    private String objTypeEnh;
    private String introduction;
    private String objType;
    private String objDescr;
    private String testScope;
    private String exclusion;
}
