package com.cis.api.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cis_queries", schema = "pts", catalog = "pts")
@Data
public class CisQueries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "module_id")
    private Integer moduleId;

    @Column(name = "message")
    private String message;

    @Column(name = "tab_code")
    private String tabCode;

    @Column(name = "link_id")
    private Integer linkId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "attachment_path")
    private String attachmentPath;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "active_status")
    private String activeStatus;

}
