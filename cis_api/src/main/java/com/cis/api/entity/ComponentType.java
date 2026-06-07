package com.cis.api.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import lombok.Data;

@Entity
@Table(schema = "galaxy", catalog = "galaxy", name = "wbs_component_type")
@Data
public class ComponentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "description")
    private String description;

    @Column(name = "active_status")
    private String activeStatus;

    // @ManyToOne
    // @JoinColumn(name = "created_by")
    // private UserLogin createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    // @ManyToOne
    // @JoinColumn(name = "updated_by")
    // private UserLogin updatedBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Transient
    private String idEncrypted;

    // public String getIdEncrypted() {
    // idEncrypted = new EncryptUsingDES().encrypt(id + "");
    // return idEncrypted;
    // }
}
