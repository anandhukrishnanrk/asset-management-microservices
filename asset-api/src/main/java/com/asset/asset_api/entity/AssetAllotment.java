package com.asset.asset_api.entity;


import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "asset_allotment", schema = "asset", catalog = "asset")
public class AssetAllotment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /* Relationship with AssetStore */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_Id", nullable = false)
    private AssetStore store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_Id")
    private HierarchySetup department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "allotted_To")
    private SeatUserAlloted allottedTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "allotted_By")
    private SeatUserAlloted allottedBy;

    @Column(name = "remarks", length = 400)
    private String remarks;

    @Column(name = "return_Remarks", length = 400)
    private String returnRemarks;

    @Temporal(TemporalType.DATE)
    @Column(name = "allotted_On")
    private Date allottedOn;

    @Temporal(TemporalType.DATE)
    @Column(name = "returned_On")
    private Date returnedOn;

    @Column(name = "status")
    private String status;

    @Column(name = "issued_Quantity")
    private Integer issuedQuantity;

    @Column(name = "office_Id")
    private Integer officeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "custodian")
    private SeatUserAlloted custodian;

}
