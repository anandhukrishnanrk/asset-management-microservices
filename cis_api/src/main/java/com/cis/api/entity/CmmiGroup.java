package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema ="pts", name = "cmmi_group", catalog = "pts")
public class CmmiGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // native generator
    @Column(name = "id")
    private Integer id;

    @Column(name = "group_Name", nullable = false)
    private String groupName;

    @Column(name = "display_Order", nullable = false)
    private Integer displayOrder;
}
