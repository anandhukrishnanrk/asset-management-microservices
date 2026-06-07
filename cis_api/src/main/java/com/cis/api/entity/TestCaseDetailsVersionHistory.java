package com.cis.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "test_case_ver_history", schema = "pts" , catalog = "pts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestCaseDetailsVersionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ver_history_Id")
    private Integer verHistoryId;

    @Column(name = "status")
    private String status;

    @Column(name = "activeStatus")
    private String activeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_Id", nullable = false)
    @JsonIgnoreProperties({"project", "versionHistoryList"})
    private TestCaseDetailsVersion version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_Case_Id", nullable = false)
    @JsonIgnoreProperties({"versionHistoryList"})
    private TestCaseDetails testCase;
}