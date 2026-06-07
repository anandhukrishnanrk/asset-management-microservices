package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resource_role_setup", schema = "pts", catalog = "pts")
@Data
public class Rolesetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_Id")
    private Integer roleId;

    @Column(name = "role_Name", length = 50)
    private String roleName;

    @Column(name = "role_Category", length = 50)
    private String roleCategory;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "role_Cost")
    private Double roleCost = 0D;

    @Column(name = "delete_Status", length = 2)
    private String deleteStatus = "N";

}
