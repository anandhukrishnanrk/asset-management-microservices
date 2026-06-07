package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmpresourceallocation", schema = "pts", catalog = "pts")
public class PMPResourceAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pmp_Resource_Id")
    private Integer pmpResourceId;

    @Column(name = "desigination", length = 50)
    private String desiginationR;

    @Column(name = "telno", length = 50)
    private String telnoR;

    @Column(name = "email", length = 100)
    private String emailR;

    @Column(name = "address", length = 100)
    private String addressR;

    @Column(name = "resourceRole", length = 100)
    private String resourceRole;

    // -------------------- Relationships --------------------

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_Id", nullable = true)
    private ProjectDetails project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_Id", nullable = false)
    private HierarchySetup seat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_Id", nullable = true)
    private UserSetup user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pmp_Id", nullable = true)
    private PmpGdReleased pmp;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "role", nullable = true)
//    private Rolesetup role;
}
