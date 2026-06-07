package com.asset.asset_api.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "hierarchy_setup", schema = "kranmaster", catalog = "kranmaster")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HierarchySetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hierarchy_Id")
    private Integer hierarchyId;

    @Column(name = "hierarchy_Name", length = 200, nullable = false)
    private String hierarchyName;

    @Column(name = "hierarchy_Code", length = 45, nullable = false)
    private String hierarchyCode;

    @Column(name = "hierarchy_Level", nullable = false)
    private Integer hierarchyLevel;

    @Column(name = "parent_hierarchy", nullable = false)
    private Integer parentHierarchy;

    @Column(name = "hierarchy_Tree", length = 255, nullable = false)
    private String hierarchyTree;

    @Column(name = "active_Date")
    private LocalDate activeDate;

    @Column(name = "deactive_Date")
    private LocalDate deactiveDate;

    @Column(name = "active_Status", length = 1, nullable = false)
    private String activeStatus;

    @Column(name = "category", length = 1, nullable = false)
    private String category;

    @Column(name = "allot_Status", length = 1, nullable = false)
    private String allotStatus;

    @Column(name = "posting_Id", nullable = false)
    private Integer hierarchyPostingId;
}