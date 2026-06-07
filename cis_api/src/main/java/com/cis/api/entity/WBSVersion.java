package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(schema = "galaxy", catalog = "galaxy", name = "wbs_version")
@Data
public class WBSVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectDetails project;

    @Column(name = "version_name")
    private String versionName;

    @Column(name = "version_number")
    private String versionNumber;

    @Column(name = "wbs_description")
    private String wbsDescription;

    @Column(name = "active_status")
    private String activeStatus = "Y";

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Date updatedOn;
}
