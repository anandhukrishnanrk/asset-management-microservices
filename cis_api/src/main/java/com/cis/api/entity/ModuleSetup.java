package com.cis.api.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "module_setup", catalog = "pts", schema = "pts")
@Data
public class ModuleSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_Id")
    private Integer moduleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id")
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id")
    private ProjectGroup group;

    @Column(name = "module_Name", length = 20)
    private String moduleName;

    @Column(name = "module_Remark", length = 100)
    private String moduleRemark;

    @Column(name = "svn")
    private String svn;

    @Column(name = "login_Status")
    private String loginStatus;

    @Column(name = "commit_Needed")
    private String commitNeeded;

    @Column(name = "start_Date")
    private LocalDate startDate;

    @Column(name = "end_Date")
    private LocalDate endDate;

    @Column(name = "module_Code", length = 20)
    private String moduleCode;

    @Column(name = "delete_Status")
    private String deleteStatus;

    // ⭐ REQUIRED BY YOUR QUERY ⭐
    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("module")
    private List<Crs> crslist;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<SubModuleSetup> subModuleList;

  
}