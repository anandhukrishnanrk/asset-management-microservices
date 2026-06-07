package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "deliverable_frequency_setup", schema = "pts", catalog = "pts")
@Data
public class DeliverableFrequencySetup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "frequency_type")
    private String frequencyType;

    @Column(name = "description")
    private String description;

    @Column(name = "delete_status")
    private String deleteStatus;
}
