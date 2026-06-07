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
@Table(name = "change_log_details", schema = "pts", catalog = "pts")
public class ChangeLogDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_id")
    private Long detId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "base_id", nullable = false)
    private ChangeLogBase base;

    @Column(name = "det_cat", length = 15)
    private String detCat;

    @Column(name = "detail_point", columnDefinition = "TEXT")
    private String detailPoint;

    @Column(name = "det_version", length = 50)
    private String detVersion;

    @Column(name = "impact_by")
    private Integer impactBy;

    // Stored as VARCHAR in DB — keeping as String
    @Column(name = "impact_date", length = 50)
    private String impactDate;

    @Column(name = "updated_On")
    private LocalDateTime updatedOn;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "saveStatus", nullable = false, length = 3)
    private String saveStatus;

    @Column(name = "initiatedStatus", length = 2)
    private String initiatedStatus;
}
