package com.cis.api.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeliverableSchedulesDetailsVo {
    private Integer id;
    private DeliverableSchedulesVo deliverableSchedules;
    private Integer phaseId;
    private String billableStatus;
    private LocalDateTime deliveryDate;
    private LocalDateTime deliveredDate;
    private String deleteStatus;
    private Double scheduleAmount;
}
