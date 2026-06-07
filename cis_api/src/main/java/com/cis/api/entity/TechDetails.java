package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "techdetails", schema = "pts", catalog = "pts")
public class TechDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_h_Id")
    private Integer t_h_Id;

    @Column(name = "techtype", length = 10)
    private String techtype;

    @Column(name = "techname", length = 500)
    private String techname;

    @Column(name = "techversion", length = 500)
    private String techversion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pmp_Id")
    private PmpGdReleased pmp;
}
