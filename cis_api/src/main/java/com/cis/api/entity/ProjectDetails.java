package com.cis.api.entity;

import java.util.Set;

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
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_details", schema = "pts", catalog = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_Id")
    private Long projectId;

    // @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "client_Register_Id")
    private Long clientRegisterId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_Visit_Id", nullable = true)
    private ClientVisitGeneralDetails clientVisit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_Contact_Person_Id", nullable = true)
    private ContactPersonDetails clientontactPerson;

    @Column(name = "project_Order_Date")
    private String projectOrderDate;

    @Column(name = "order_Project_Name")
    private String orderProjectName;

    @Column(name = "seat_Id")
    private Integer seatId;

    @Column(name = "type_Seat_Id")
    private Integer typeSeatId;

    @Column(name = "status_Seat_Id")
    private Integer statusSeatId;

    @Column(name = "project_Status", length = 3)
    private String projectStatus;

    @Column(name = "project_Order_No", length = 11)
    private String projectOrderNo;

    @Column(name = "project_Code", length = 11)
    private String projectCode;

    @Column(name = "production_Url")
    private String productionURL;

    @Column(name = "project_Category", length = 10)
    private String projectCategory;

    @Column(name = "project_Budget", length = 10)
    private String projectBudget;

    @Column(name = "estimated_Time_Initiation")
    private String estimatedTimeInitiation;

    @Column(name = "proposalversion")
    private String proposalVersion;

    @Column(name = "intiatestatus")
    private String intiateStatus;

    @Column(name = "project_Description")
    private String projectDescription;

    @Column(name = "project_Type")
    private String projectType;

    @Column(name = "project_Current_Status")
    private String projectCurrentStatus;

    @Column(name = "group_Add_Status")
    private String groupAddStatus;

    @Column(name = "project_Start_Date")
    private String projectStartDate;

    @Column(name = "project_End_Date")
    private String projectEndDate;

    @Column(name = "active_Status")
    private String activeStatus;

    @Column(name = "billable")
    private String billableStatus;

    @Column(name = "sla_Status")
    private String slaStatus;

    // one-to-many Project Group
     @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = {
     CascadeType.PERSIST, CascadeType.MERGE })
     @OrderBy("group_Id DESC")
     private Set<ProjectGroup> projectGroupList;
}
