package com.asset.asset_api.entity;


import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "asset_register", schema = "asset")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Asset implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer assetId;

    @Column(name = "supplier_Name")
    private String supplierName;

    @Column(name = "invoice_Number")
    private String invoiceNumber;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "invoice_Date")
    private Date invoiceDate;

    @Column(name = "value")
    private String value;

    @Column(name = "delete_Status")
    private String deleteStatus;

    @Column(name = "allot_Status")
    private String allotStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_Id")
    private SetupGroup group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_Id")
    private SetupCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_Category_Id")
    private SetupSubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_Seat_User_Id")
    private SeatUserAlloted actionSeatUser;

    
    @Column(name = "office_Id")
    private Integer officeId;
    
		
    
    @Column(name = "total_Value")
    private String totalValue;
    
    @Column(name = "depreciation")
    private String depreciation;
    
    @Column(name = "quantity_Allotted")
    private Integer quantityAllotted;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    
    
    @Column(name = "amc")
    private String amc;
	

}