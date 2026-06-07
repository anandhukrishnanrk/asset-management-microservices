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
@Table(name = "deliverableschedules", schema = "pts", catalog = "pts")
public class DeliverableSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "d_effort_Id")
    private Integer deffortId;

    @ManyToOne
    @JoinColumn(name = "project_Id")
    private ProjectDetails project;

    @Column(name = "project_Order_Id")
    private Integer projectOrderId;

    @Column(name = "deliverable_Item", length = 1000)
    private String deliverableItem;

    @Column(name = "activity", columnDefinition = "TEXT")
    private String activity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliverable_Type_Id")
    private DeliverableTypeSetup deliverableType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deliverable_Frequency")
    private DeliverableFrequencySetup deliverableFrequency;

    @Column(name = "delivered_Remarks", columnDefinition = "LONGTEXT")
    private String deliveredRemarks;

    @Column(name = "deliverable_Entered_On")
    private LocalDateTime deliverableEnteredOn;

    @Column(name = "deliverable_Entered_By")
    private Integer deliverableEnteredBy;

    @Column(name = "delete_Status")
    private String deleteStatus;
}
