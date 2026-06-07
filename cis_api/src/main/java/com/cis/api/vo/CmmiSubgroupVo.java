package com.cis.api.vo;

import lombok.Data;

@Data
public class CmmiSubgroupVo {
    private Long id;
    private CmmiGroupVo group;
    private String itemName;
    private String shortName;
    private Integer displayOrder;
    private String componentType;
    private Integer auditId;
}
