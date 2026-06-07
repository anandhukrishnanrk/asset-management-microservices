package com.cis.api.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeliverablePaymentDetailsVo {
    private Integer id;
    private DeliverableSchedulesDetailsVo deliverableSchedulesDetails;
    private DeliverableBillingDetailsVo deliverableBillingDetails;
    private LocalDateTime paymentDate;
    private Double receivedAmountIncTax;
    private Double taxRate;
    private Double receivedAmountExcTax;
    private String receivedRemarks;
    private Double pendingAmount;
    private String pendingRemarks;
    private String paymentStatus;
    private LocalDateTime paymentEnteredOn;
    private Integer paymentEnteredBy;
    private String deleteStatus;
    private String partialPayment;
    private Double tdsAmount;
    private String tdsRemarks;
    private Double gstAmount;
    private String gstRemarks;
    private Double otherAmount;
    private String otherRemarks;
}
