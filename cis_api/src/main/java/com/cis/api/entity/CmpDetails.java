package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "cmp_details", schema = "pts", catalog = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmp_Id")
    private Long cmpId;

    @Column(name = "module_Id")
    private Integer moduleId;

    @Column(name = "submission_Date", nullable = false)
    private Date submissionDate;

    @Column(name = "cmp_Version", nullable = false)
    private Double cmpVersion;

    @Column(name = "cmp_Status", length = 1, nullable = false)
    private String cmpStatus;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id")
    private UserSetup userId;

    @Column(name = "insert_Date")
    private Date insertDate;

    @Column(name = "group_Id")
    private Integer groupId;

    @Column(name = "project_Id", nullable = false)
    private Integer projectId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_By")
    private UserSetup approvedBy;

    @Column(name = "approved_Date")
    private Date approvedDate;

    @Column(name = "release_Status", length = 1)
    private String releaseStatus;

    @Column(name = "release_By")
    private Integer releaseBy;

    @Column(name = "release_Date")
    private Date releaseDate;

    @Column(name = "delete_status", length = 1)
    private String deleteStatus;
}
