package com.asset.asset_api.entity;

/**
 * Anand S V
 **/

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "asset_disposal", schema = "asset")
public class AssetDisposal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer assetDisposalId;

    @Column(name = "store_Id")
    private Integer storeId;

    @Column(name = "disposal_Type", length = 20)
    private String disposalType;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "disposed_Date")
    @Temporal(TemporalType.DATE)
    private Date disposedDate;

    @Column(name = "disposal_Cost", length = 10)
    private String disposalCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_SeatId", nullable = false)
    private SeatUserAlloted actionSeat;

    @Column(name = "sold_Amount")
    private Integer soldAmount;

    @Column(name = "tender", length = 1)
    private String tender;

    @Column(name = "tender_Number", length = 50)
    private String tenderNumber;

    @Column(name = "tender_Venue", length = 300)
    private String tenderVenue;

    @Column(name = "tender_Date")
    @Temporal(TemporalType.DATE)
    private Date tenderDate;

    @Transient
    private Date searchddate1;

    @Transient
    private Date searchddate2;

    @Transient
    private String subcatname;

    @Transient
    private String catname;

    @Transient
    private String assetCode;
}
