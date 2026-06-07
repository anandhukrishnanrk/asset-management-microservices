package com.cis.api.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChangeLogDetailsVo {
    private Long detId;
    private ChangeLogBaseVo base;
    private String detCat;
    private String detailPoint;
    private String detVersion;
    private Integer impactBy;
    private String impactDate;
    private LocalDateTime updatedOn;
    private Integer groupId;
    private String saveStatus;
    private String initiatedStatus;
}
