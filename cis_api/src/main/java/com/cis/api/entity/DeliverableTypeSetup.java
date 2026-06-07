package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "deliverable_type_setup", schema = "pts", catalog = "pts")
public class DeliverableTypeSetup {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deliverable_Type", nullable = false, length = 45)
    private String deliverableType;

    @Column(name = "remarks", columnDefinition = "LONGTEXT")
    private String remarks;

    @Column(name = "delete_Status")
    private String deleteStatus;
}
