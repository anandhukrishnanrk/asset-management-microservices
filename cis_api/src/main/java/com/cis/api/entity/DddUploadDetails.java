package com.cis.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ddd_upload_details", schema = "pts", catalog = "pts")
@Data
public class DddUploadDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_Id")
    private Long uploadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ddd_Id")
    private DddDetails ddd;

    @Column(name = "file_Name")
    private String fileName;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
    
}
