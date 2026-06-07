package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "resource_project", schema = "pts", catalog = "pts")
@Data
public class ResourceProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_Id")
    private Integer resourceId;

    // ===============================
    // Relationships
    // ===============================

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "project_Id", nullable = false)
    private ProjectDetails projectDetails;

//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "role_Id", nullable = false)
//    private pts.documents.bean.Rolesetup rolesetup;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "responsibility_Id", nullable = false)
//    private ResourceResponsibilitySetup resourceResponsibilitySetup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currently_Alloted")
    private SeatUserAlloted seatUserAlloted;

    // ===============================
    // Column Mappings
    // ===============================

    @Column(name = "alloted_Status")
    private String allotedStatus = "N";

    @Column(name = "delete_Status")
    private String deleteStatus = "N";

    @Column(name = "responsibility_Details")
    private String responsibilityDetails;

    // ===============================
    // Not mapped in Hibernate XML
    // ===============================

    @Transient
    private String responsibility;

    @Transient
    private String name;

    @Transient
    private String category;

    @Transient
    private String roleName;

   
}
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(schema = "pts", name = "resource_project", catalog = "pts")
//public class ResourceProject {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "resource_Id")
//	private Integer resourceId;
//
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = ResponsibilitySetup.class)
//	@JoinColumn(name = "responsibility_id")
//	public ResponsibilitySetup responsibility;
//
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = ProjectDetails.class)
//	@JoinColumn(name = "project_Id")
//	public ProjectDetails projectDetails;
//
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = RoleSetup.class)
//	@JoinColumn(name = "role_Id")
//	public RoleSetup roleSetup;
//
//	@ManyToOne(fetch = FetchType.EAGER, optional = true, targetEntity = SeatUserAlloted.class)
//	@JoinColumn(name = "currently_Alloted")
//	public SeatUserAlloted currentlyAlloted;
//	
//	@Column(name = "alloted_Status")
//	public String allotedStatus;
//	
//	@Column(name = "responsibility_Details")
//	public String responsibilityDetails;
//	
//	@Column(name = "delete_Status")
//	public String deleteStatus;
//	
//}
//>>>>>>> branch 'Development' of https://git.kran.co.in/product/galaxy/cis/cis_api.git
