package com.cis.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "deliverable_payment_details", schema = "pts", catalog = "pts")
public class DeliverablePaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliverable_Schedule_Details_Id")
    private DeliverableSchedulesDetails deliverableSchedulesDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliverable_Billing_Id")
    private DeliverableBillingDetails deliverableBillingDetails;

    @Column(name = "payment_Date")
    private LocalDateTime paymentDate;

    @Column(name = "received_Amount_Inc_Tax")
    private Double receivedAmountIncTax;

    @Column(name = "tax_Rate")
    private Double taxRate;

    @Column(name = "received_Amount_Exc_Tax")
    private Double receivedAmountExcTax;

    @Column(name = "received_Remarks", columnDefinition = "LONGTEXT")
    private String receivedRemarks;

    @Column(name = "pending_Amount")
    private Double pendingAmount;

    @Column(name = "pending_Remarks", columnDefinition = "LONGTEXT")
    private String pendingRemarks;

    @Column(name = "payment_Status")
    private String paymentStatus;

    @Column(name = "payment_Entered_On")
    private LocalDateTime paymentEnteredOn;

    @Column(name = "payment_Entered_By")
    private Integer paymentEnteredBy;

    @Column(name = "delete_Status")
    private String deleteStatus;

    @Column(name = "partial_Payment")
    private String partialPayment;

    @Column(name = "tds_Amount")
    private Double tdsAmount;

    @Column(name = "tds_Remarks", columnDefinition = "MEDIUMTEXT")
    private String tdsRemarks;

    @Column(name = "gst_Amount")
    private Double gstAmount;

    @Column(name = "gst_Remarks", columnDefinition = "MEDIUMTEXT")
    private String gstRemarks;

    @Column(name = "other_Amount")
    private Double otherAmount;

    @Column(name = "other_Remarks", columnDefinition = "LONGTEXT")
    private String otherRemarks;
}
