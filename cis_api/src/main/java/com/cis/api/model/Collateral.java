package com.cis.api.model;

import com.cis.api.entity.ProjectDetails;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Collateral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // e.g., "SRS", "Design Doc"

    @Column(length = 1000)
    private String description;

    private String url; // Could be a file path or external link

    @Enumerated(EnumType.STRING)
    private CollateralStatus status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectDetails project;
}
