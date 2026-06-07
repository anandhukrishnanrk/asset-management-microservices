package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact_person_details", schema = "pts", catalog = "pts")
@Data
public class ContactPersonDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_Contact_Person_Id")  // ✔ match DB column
    private Long clientContactPersonId;
}
