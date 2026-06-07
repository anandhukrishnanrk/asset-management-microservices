package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "test_plan_uploads", schema = "pts", catalog = "pts")
@Data
public class TestPlanUploads {

    @Id
    @Column(name = "upload_Id")
    private Integer uploadId;

    @ManyToOne
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails projectDetails;

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "group_Id")
    // private ProjectGroup group;

    @Column(name = "atchd_Date", length = 12)
    private String attachmentDate;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "attachement")
    private String attachement;

    @Column(name = "attached_By")
    private String attachedBy;

    @Column(name = "test_Plan_No", length = 45)
    private String testPlanNo;

    @Column(name = "release_Date")
    private String releaseDate;

    @Column(name = "released_By", length = 12)
    private String releaseBy;

    @Column(name = "test_Plan_Ver")
    private Double testPlanVersion;

    @Column(name = "release_Status", length = 2)
    private String releaseStatus;

    @Column(name = "client_View_Status", length = 1)
    private String clientViewStatus;

    @Column(name = "delete_Status", length = 2)
    private String deleteStatus;
}
