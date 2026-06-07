package com.asset.asset_api.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "setup_sub_category", schema = "asset")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SetupSubCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer subCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_Id", nullable = false)
    private SetupCategory category;

    @Column(name = "sub_Category", length = 100)
    private String subCategory;

    @Column(name = "code", length = 25)
    private String code;

    @Column(name = "delete_Status", length = 1)
    private String deleteStatus;

}