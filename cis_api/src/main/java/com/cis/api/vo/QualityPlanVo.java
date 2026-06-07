package com.cis.api.vo;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class QualityPlanVo {
    private Integer id;
    private Integer cmmiProjectId;
    private LocalDate qpDate;
    private String attachmentName;
    private String versionNumber;
    private String remarks;
    private SeatUserAllottedVo addedBy;
    private SeatUserAllottedVo approvedBy;
    private String deleteStatus;
    private Date approvedOn;
}
