package com.asset.asset_api.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "asset_AMC_Period", schema = "asset")
public class AMCPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "amount")
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "period_From")
    private Date periodFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "period_To")
    private Date periodTo;

    @Column(name = "status")
    private String status;

}