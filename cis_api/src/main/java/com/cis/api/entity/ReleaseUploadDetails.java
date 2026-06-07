package com.cis.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;

@Entity
@Table(name = "release_upload_details", schema = "pts", catalog = "pts")
@Data
public class ReleaseUploadDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_Id")
    private Long uploadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "release_Id", nullable = false)
    private ReleaseDetails release;

    @Column(name = "file_Name", nullable = false, length = 255)
    private String fileName;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
}
