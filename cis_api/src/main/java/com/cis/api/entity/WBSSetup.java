package com.cis.api.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(schema = "galaxy", catalog = "galaxy", name = "wbs_setup")
@Data
public class WBSSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "wbs_code")
    private String wbsCode;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectDetails project;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "level")
    private Integer level;

    @Column(name = "wbs_type")
    private String wbsType; // "COMPONENT" or "ACTIVITY"

    @ManyToOne
    @JoinColumn(name = "component_type_id")
    private ComponentType componentType;

    @ManyToOne
    @JoinColumn(name = "activity_type_id")
    private ActivityType activityType;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "progress")
    private Integer progress; // 0-100

    @Column(name = "status")
    private String status; // New, In Progress, On Hold, Closed, etc.

    @Column(name = "priority")
    private String priority; // Low, Normal, High, Critical

    @Column(name = "estimated_hours")
    private Double estimatedHours;

    @Column(name = "actual_hours")
    private Double actualHours;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "active_status")
    private String activeStatus;

    // @ManyToOne
    // @JoinColumn(name = "created_by")
    // private UserLogin createdBy;

    // @ManyToOne
    // @JoinColumn(name = "updated_by")
    // private UserLogin updatedBy;

    // @ManyToOne
    // @JoinColumn(name = "assigned_to")
    // private UserLogin assignedTo;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_deliverable")
    private String isDeliverable; // "Y" or "N"

    @Column(name = "client_approval_needed")
    private String clientApprovalNeeded; // "Y" or "N"

    @Column(name = "is_billable")
    private String isBillable; // "Y" or "N"

    @Column(name = "billable_amount")
    private Double billableAmount; // Billable amount excluding tax

    @Column(name = "billable_amount_inc_tax")
    private Double billableAmountIncTax; // Billable amount including tax

    @Column(name = "tax_rate")
    private Double taxRate; // Tax rate percentage

    @ManyToOne
    @JoinColumn(name = "deliverable_type_id")
    private SetupDeliverableType deliverableType;

    @ManyToOne
    @JoinColumn(name = "deliverable_frequency_id")
    private SetupDeliverableFrequency deliverableFrequency;

    @ManyToOne
    @JoinColumn(name = "wbs_version_id")
    private WBSVersion wbsVersion;

    @Transient
    private String idEncrypted;

    @Transient
    private List<WBSSetup> children;

    @Transient
    private Boolean hasChildren;

    // public String getIdEncrypted() {
    // if (id != null) {
    // idEncrypted = new EncryptUsingDES().encrypt(id + "");
    // }
    // return idEncrypted;
    // }

    public List<WBSSetup> getChildren() {
        return children;
    }

    public void setChildren(List<WBSSetup> children) {
        this.children = children;
        this.hasChildren = children != null && !children.isEmpty();
    }

    public Boolean getHasChildren() {
        return hasChildren != null ? hasChildren : false;
    }
}
