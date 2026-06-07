package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "project_group", schema = "pts", catalog = "pts")
@Data
public class ProjectGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails project;   // ✔ FIXED (must be 'project')

    @Column(name = "group_Name")
    private String groupName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "delete_Status")
    private String deleteStatus;
}