package com.cis.api.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pmp_reusables", schema = "pts", catalog = "pts")
public class PmpReusables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pmp_Id", nullable = false)
    private Integer pmpId;

    @Column(name = "utility_Id", nullable = false)
    private Integer utilityId;

    @Column(name = "utility_Attachment_Id", nullable = false)
    private Integer utilityAttachmentId;

    @Column(name = "utility_Name", nullable = false)
    private String utilityName;

    @Column(name = "remarks", nullable = false)
    private String remarks;
}
