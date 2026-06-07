package com.asset.asset_api.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "asset_AMC_Period", schema = "asset")
public class AMCPeroid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 1)
    private String name;

    @Column(name = "address", length = 1)
    private String address;



    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "period_From")
    @Temporal(TemporalType.DATE)
    private Date periodFrom;

    @Column(name = "period_To")
    @Temporal(TemporalType.DATE)
    private Date periodTo;

    @Column(name = "status", length = 1)
    private String status;

    /* ---------- Not mapped in Hibernate XML ---------- */

    @Transient
    private Asset asset;

    @Transient
    private AssetStore store;

    @Transient
    private String serialNumber;
}
