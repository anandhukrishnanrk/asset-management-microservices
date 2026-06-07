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
@Table(name = "deliverable_schedules_details", schema = "pts", catalog = "pts")
public class DeliverableSchedulesDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliverable_Schedules_Id")
    private DeliverableSchedules deliverableSchedules;

    @Column(name = "phase_Id")
    private Integer phaseId;

    @Column(name = "billable_Status")
    private String billableStatus;

    @Column(name = "delivery_Date")
    private LocalDateTime deliveryDate;

    @Column(name = "delivered_Date")
    private LocalDateTime deliveredDate;

    @Column(name = "delete_Status")
    private String deleteStatus;

    @Column(name = "schedule_Amount")
    private Double scheduleAmount;
}
