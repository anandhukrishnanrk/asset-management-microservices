package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "test_plan_details", schema = "pts",  catalog = "pts")
@Data
public class TestPlanDetails {

    @Id
    @Column(name = "test_Plan_Id")
    private Integer testPlanId;

    @ManyToOne
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails projectDetails;

    @ManyToOne
    @JoinColumn(name = "group_Id")
    private ProjectGroup group;

    @Column(name = "testplan_No", length = 12)
    private String testplanNo;

    @Column(name = "obj_Type_Enh", length = 5)
    private String objTypeEnh;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "obj_Type")
    private String objType;

    @Column(name = "obj_Descr")
    private String objDescr;

    @Column(name = "test_Scope")
    private String testScope;

    @Column(name = "exclusion")
    private String exclusion;
}
