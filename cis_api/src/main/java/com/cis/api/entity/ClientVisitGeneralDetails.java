package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "client_visit_generaldetails", schema = "pts", catalog = "pts")
@Data
public class ClientVisitGeneralDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_Visit_Id")
    private Integer clientVisitId;
}
