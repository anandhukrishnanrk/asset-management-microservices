package com.cis.api.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cmmi_project", schema = "pts", catalog = "pts")
@Data
public class CmmiProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;   // XML: id

    // XML: many-to-one → project_Id → Project_details
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails project;

    @Temporal(TemporalType.DATE)
    @Column(name = "added_On", nullable = false)
    private LocalDate addedOn;

    @Column(name = "added_By", nullable = false)
    private Integer addedBy;

    @Column(name = "dashboard_Status")
    private String dashboardStatus;

    @Column(name = "email")
    private String email;

    @Column(name = "email_Provider")
    private String emailProvider;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile")
    private String mobile;

    // XML: <set name="projectComponents" key column="cmmi_Project_Id">
//    @OneToMany(mappedBy = "cmmiProject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<CmmiProjectComponents> projectComponents;
}
