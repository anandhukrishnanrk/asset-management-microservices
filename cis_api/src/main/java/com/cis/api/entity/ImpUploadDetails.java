package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "imp_upload_details", catalog = "pts", schema = "pts")
@Data
public class ImpUploadDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_Id")
    private Integer uploadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "imp_Id", nullable = false)
    private ImpDetails imp;

    @Column(name = "file_Name", length = 255)
    private String fileName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_Date")
    private Date uploadDate;
}
