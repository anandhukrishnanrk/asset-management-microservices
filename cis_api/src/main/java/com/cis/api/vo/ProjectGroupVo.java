package com.cis.api.vo;

import lombok.Data;

@Data
public class ProjectGroupVo {
    private Integer id;
    private ProjectDetailsVo projectId;
    private String groupName;
    private String remarks;
    private String deleteStatus;
}
