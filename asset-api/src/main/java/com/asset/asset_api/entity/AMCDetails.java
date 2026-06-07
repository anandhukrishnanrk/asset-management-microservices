package com.asset.asset_api.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "asset_amc", schema = "asset")
public class AMCDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_Id", nullable = false)
    private AssetStore assetStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_Id", nullable = false)
    private AMCPeroid amcPeroid;
}
