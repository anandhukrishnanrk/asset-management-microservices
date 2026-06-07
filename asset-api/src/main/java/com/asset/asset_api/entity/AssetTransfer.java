package com.asset.asset_api.entity;


import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "asset_transfer", schema = "asset")
public class AssetTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer assetTransferId;

    /* Relationship with AssetStore */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_Id", nullable = false)
    private AssetStore store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "office_Id", nullable = false)
    private HierarchySetup office;

    @Column(name = "remarks")
    private String remarks;

    @Temporal(TemporalType.DATE)
    @Column(name = "transfered_On")
    private Date transferedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transfered_By")
    private SeatUserAlloted transferedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transfered_From", nullable = false)
    private HierarchySetup transferedFrom;

    @Column(name = "acknowledgement_Status", length = 1)
    private String acknowledgementStatus;

    @Column(name = "status", length = 1)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "returned_On")
    private Date returnedOn;

    @Column(name = "return_Remarks", columnDefinition = "TEXT")
    private String returnRemarks;

    @Temporal(TemporalType.DATE)
    @Column(name = "received_On")
    private Date receivedOn;

    @Column(name = "receive_Remarks", columnDefinition = "TEXT")
    private String receiveRemarks;

    @Column(name = "transfered_Quantity")
    private Integer transferedQuantity;

}
