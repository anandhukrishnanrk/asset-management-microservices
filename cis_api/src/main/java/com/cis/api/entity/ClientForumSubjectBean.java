package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "client_forum_subject", schema = "pts", catalog = "pts")
@Data
public class ClientForumSubjectBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_Id")
    private Long id;
}
