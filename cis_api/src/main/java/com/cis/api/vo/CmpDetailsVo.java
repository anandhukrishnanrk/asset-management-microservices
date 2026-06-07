package com.cis.api.vo;

import java.util.Date;
import lombok.Data;

@Data
public class CmpDetailsVo {
    private Long cmpId;
    private Integer moduleId;
    private Date submissionDate;
    private Double cmpVersion;
    private String cmpStatus;
    private String remarks;
    private UserSetupVo userId;
    private Date insertDate;
    private Integer groupId;
    private Integer projectId;
    private UserSetupVo approvedBy;
    private Date approvedDate;
    private String releaseStatus;
    private Integer releaseBy;
    private Date releaseDate;
    private String deleteStatus;
}
