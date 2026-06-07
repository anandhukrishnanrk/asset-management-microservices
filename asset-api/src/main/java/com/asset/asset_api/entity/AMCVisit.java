package com.asset.asset_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "asset_AMC_visit", schema = "asset")
public class AMCVisit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amcPero_Id", nullable = false)
    @JsonIgnore
    private AMCPeriod amcPeroid;

    @Column(name = "remarks")
    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)   // ✅ CHANGE HERE
    @Column(name = "visit_Date")
    private Date visitDate;
}