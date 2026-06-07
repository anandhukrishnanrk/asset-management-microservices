package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "cmp_upload_details", schema = "pts", catalog = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmpUploadDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_Id")
    private Long uploadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cmp_Id", nullable = false)
    private CmpDetails cmpDetails;

    @Column(name = "file_Name", nullable = false)
    private String fileName;

    @Column(name = "upload_date")
    private Date uploadDate;
}
