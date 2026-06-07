package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "client", schema = "pts", catalog = "pts")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_Id")
    private Long clientId;

     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "client_Register_Id")
     private ClientRegister clientRegister;

    @Column(name = "project_Id")
    private Long projectId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "project_Id", insertable = false, updatable = false)
    private ProjectDetails project;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "client_Login_Id")
    private Long clientLoginId;
}
