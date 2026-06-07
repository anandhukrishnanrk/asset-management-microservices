package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmp_training_needs", schema = "pmp", catalog = "pts")
public class PmpTrainingNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmp_Id", nullable = false)
    private Integer pmpId;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "number_Of_Persons", nullable = false)
    private Integer numberOfPersons;
}
