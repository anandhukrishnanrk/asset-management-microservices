package com.cis.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(schema ="pts", name = "cmmi_subgroup", catalog = "pts")
public class CmmiSubgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // native generator
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id", nullable = true)
    private CmmiGroup group;

    @Column(name = "item_Name", nullable = false)
    private String itemName;

    @Column(name = "short_Name", nullable = false)
    private String shortName;

    @Column(name = "display_Order", nullable = false)
    private Integer displayOrder;

    @Column(name = "component_Type", nullable = false)
    private String componentType;

    @Column(name = "audit_Id")
    private Integer auditId;
}
