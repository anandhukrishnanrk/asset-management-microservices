package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(schema = "galaxy", catalog = "galaxy", name = "setup_deliverable_type")
@Data
public class SetupDeliverableType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deliverable_type")
    private String deliverableType;

    @Column(name = "remarks", columnDefinition = "LONGTEXT")
    private String remarks;

    @Column(name = "active_status")
    private String activeStatus;

    @Transient
    private String idEncrypted;

    // public String getIdEncrypted() {
    // idEncrypted = new EncryptUsingDES().encrypt(id + "");
    // return idEncrypted;
    // }

}
