package com.cis.api.entity;

import java.time.LocalDate;

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
@Table(name = "change_log_base", schema = "pts", catalog = "pts")
public class ChangeLogBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ver_Id")
    private Long verId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails project;

    @Column(name = "base_Version", length = 20)
    private String baseVersion;

    @Column(name = "request_From", length = 300)
    private String requestFrom;

    // Stored as VARCHAR in DB — keeping as String
    @Column(name = "request_Date", length = 20)
    private String requestDate;

    @Column(name = "release_Type", nullable = false, length = 5)
    private String releaseType;

    @Column(name = "status", nullable = false, length = 5)
    private String status;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "approved_By")
    private Integer approvedBy;

    @Column(name = "approved_On")
    private LocalDate approvedOn;

    @Column(name = "approved_By_Client", length = 45)
    private String approvedByClient;
}
