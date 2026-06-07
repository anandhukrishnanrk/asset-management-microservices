package com.cis.api.vo;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ModuleSetupVo {
    private Integer moduleId;
    private ProjectDetailsVo project;
    private ProjectGroupVo group;
    private String moduleName;
    private String moduleRemark;
    private String svn;
    private String loginStatus;
    private String commitNeeded;
    private LocalDate startDate;
    private LocalDate endDate;
    private String moduleCode;
    private String deleteStatus;
}
