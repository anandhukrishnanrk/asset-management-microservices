package com.asset.asset_api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "asset_store", schema = "asset")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class AssetStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer storeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_Id", nullable = false)
    @JsonIgnore
    private Asset asset;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_Id")
    private HierarchySetup office;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_SeatId")
    private HierarchySetup actionSeat;
    
    @Column(name = "asset_Code")
    private String assetCode;

    @Column(name = "status", length = 1)
    private String status;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("allottedOn DESC")
    @JsonIgnore
    private List<AssetAllotment> allotments;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    @JsonIgnore
    private List<AssetTransfer> transfers;

    @Column(name = "transfered_To")
    private Integer transferedTo;

    @Column(name = "serial_Number")
    private String serialNumber;

    
    
    /* -------- Utility Fields -------- */

    @Transient
    private Integer officeId;

    @Transient
    private Date searchrdate1;

    @Transient
    private Date searchrdate2;

    @Transient
    private Date disposalDate;

    @Transient
    private Date maintenanceDate;

    @Transient
    private String complaint;

    @Transient
    private Date compantDate;

    @Transient
    private String vendorPhone;

    @Transient
    private String vendorName;

    @Transient
    private float amount;

    @Transient
    private String name;

    @Transient
    private Double value;

    @Transient
    private BigInteger count;

    @Transient
    private Integer sgid;

    @Transient
    private String custodian;

    @Transient
    private String allotedTo;

    @Transient
    private String categoryName;

    @Transient
    private Date startDate;

    @Transient
    private Date endDate;

    @Transient
    private String officeName;

    @Transient
    private Integer maturityDaysRemaining;

    @Transient
    private Date allottedOn;

    @Transient
    private String disposalAmount;
}