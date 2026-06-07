package com.asset.asset_api.entity;


import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "asset_flow_table", schema = "asset")
public class AssetFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer assetFlowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_Id")
    private AssetStore store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allot_Id")
    private AssetAllotment allotment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_Id")
    private AssetTransfer transfer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_Id")
    private AssetMaintenance maintenance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disposal_Id")
    private AssetDisposal disposal;

    @Column(name = "action", length = 1)
    private String action;

    @Column(name = "action_Date_Time")
    private LocalDateTime actionDT;
}
