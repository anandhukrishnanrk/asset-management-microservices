package com.asset.asset_api.entity;

/**
 * Anand S V
 **/

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "asset_maintenance", schema = "asset")
public class AssetMaintenance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer assetMaintenanceId;

    @Column(name = "store_Id")
    private Integer storeId;

    @Column(name = "office_Id")
    private Integer officeId;

    @Column(name = "invoice_Number", length = 20)
    private String invoiceNumber;

    @Column(name = "invoice_Date")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @Column(name = "maintenance_Date")
    @Temporal(TemporalType.DATE)
    private Date maintenanceDate;

    @Column(name = "add_Or_Delete", length = 1)
    private String addOrDelete;

    @Column(name = "add_Or_Delete_Amount")
    private Double addOrDeleteAmount;

    @Column(name = "amount", length = 20)
    private String amount;

    @Column(name = "vendor_Name", length = 100)
    private String vendorName;

    @Column(name = "vendor_Address", length = 400)
    private String vendorAddress;

    @Column(name = "vendor_Phone", length = 15)
    private String vendorPhone;

    @Column(name = "complaint_Date")
    @Temporal(TemporalType.DATE)
    private Date complaintDate;

    @Column(name = "complaint")
    private String complaint;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "history_Status", length = 1)
    private String historyStatus = "N";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_SeatId", nullable = false)
    @JsonIgnore
    private HierarchySetup actionSeat;

    @Column(name = "asset_Code", length = 25)
    private String assetCode;

    /* ---------- Non Persistent Fields ---------- */

    @Transient
    private Date searchmdate1;

    @Transient
    private Date searchmdate2;

    @Transient
    private String subcatname;

    @Transient
    private String catname;
}
