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
@Table(name = "deliverable_billing_details", schema = "pts", catalog = "pts")
public class DeliverableBillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliverable_Schedule_Details_Id")
    private DeliverableSchedulesDetails deliverableScheduleDetails;

    @Column(name = "planned_Bill_Date")
    private LocalDateTime plannedBillDate;

    @Column(name = "actual_Bill_Date")
    private LocalDateTime actualBillDate;

    @Column(name = "bill_Amount_Inc_Tax")
    private Double billAmountIncTax;

    @Column(name = "tax_Rate")
    private Double taxRate;

    @Column(name = "bill_Amount_Exc_Tax")
    private Double billAmountExcTax;

    @Column(name = "bill_Remarks", columnDefinition = "LONGTEXT")
    private String billRemarks;

    @Column(name = "bill_Status")
    private String billStatus;

    @Column(name = "bill_Entered_On")
    private LocalDateTime billEnteredOn;

    @Column(name = "bill_Entered_By")
    private Integer billEnteredBy;

    @Column(name = "delete_Status")
    private String deleteStatus;
}