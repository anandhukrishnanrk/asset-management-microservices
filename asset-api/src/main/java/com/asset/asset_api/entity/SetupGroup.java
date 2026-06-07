package com.asset.asset_api.entity;


import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "setup_group", schema = "asset", catalog = "asset")
public class SetupGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer groupId;

    @Column(name = "name")
    private String name;

}
