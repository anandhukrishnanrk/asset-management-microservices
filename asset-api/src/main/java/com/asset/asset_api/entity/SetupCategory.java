package com.asset.asset_api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "setup_category", schema = "asset")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SetupCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_Id", nullable = false)
    private SetupGroup group;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "code", length = 25)
    private String code;

    @Column(name = "delete_Status", length = 1)
    private String deleteStatus;

    /* -------- Non DB Fields -------- */

    @Transient
    private String categoryName;

    @Transient
    private Double value;

    @Transient
    private BigInteger count;

}