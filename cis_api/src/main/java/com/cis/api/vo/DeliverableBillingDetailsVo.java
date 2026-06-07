package com.cis.api.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeliverableBillingDetailsVo {
    private Integer id;
    private DeliverableSchedulesDetailsVo deliverableScheduleDetails;
    private LocalDateTime plannedBillDate;
    private LocalDateTime actualBillDate;
    private Double billAmountIncTax;
    private Double taxRate;
    private Double billAmountExcTax;
    private String billRemarks;
    private String billStatus;
    private LocalDateTime billEnteredOn;
    private Integer billEnteredBy;
    private String deleteStatus;
}
