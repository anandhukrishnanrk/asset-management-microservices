package com.cis.api.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "path_traversal", schema = "pts", catalog = "pts")
@Data
public class PathTraversal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_Id", nullable = false)
    private Long projectId;

    @Column(name = "group_Id", nullable = false)
    private Long groupId;

    @Column(name = "traversal_Activity", length = 1000)
    private String traversalActivity;

    @Column(name = "traversal_Date")
    private LocalDate traversalDate;

    @Column(name = "delete_status", length = 1)
    private String deleteStatus;
}
