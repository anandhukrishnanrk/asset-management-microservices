package com.cis.api.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class DeliverySchedule {
    private int id;
    private String deliverableItem;
    private String activity;
    private String deliveredRemarks;
    private LocalDate deliverableEnteredOn;
    private String deleteStatus;
    public ProjectOrderDetailsVo projectOrderDetails;
    public ProjectDetailsVo project;
    public SeatUserAllottedVo deliverableEnteredBy;
    public String deliverableFrequency;
    public String deliverableType;
    private Double billAmount;
    private Double paymentAmount;
    private LocalDate submitDateAsPerContract;
    private LocalDate actualSubmitDate;
    private LocalDate approvalDateAsPerContract;
    private LocalDate actualApprovalDate;
    private String paymentStatus;
    private LocalDate paymentDate;
    private LocalDate billedDate;
    private LocalDate deliverydate;
}
