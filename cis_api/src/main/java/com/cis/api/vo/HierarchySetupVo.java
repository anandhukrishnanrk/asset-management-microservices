package com.cis.api.vo;

import java.time.LocalDate;
import lombok.Data;

@Data
public class HierarchySetupVo {
    private Integer hierarchyId;
    private String hierarchyName;
    private String hierarchyCode;
    private Integer hierarchyLevel;
    private Integer parentHierarchy;
    private String hierarchyTree;
    private LocalDate activeDate;
    private LocalDate deactiveDate;
    private String activeStatus;
    private String category;
    private String allotStatus;
    private Integer hierarchyPostingId;
}
