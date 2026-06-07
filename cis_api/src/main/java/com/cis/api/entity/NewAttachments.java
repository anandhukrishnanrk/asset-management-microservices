package com.cis.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "new_Attachments", schema = "pts", catalog = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewAttachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnew_Attachments")
    private Integer id;

    @Column(name = "cmmi_Project_Id")
    private Integer cmmiProjectId;

    @Column(name = "submission_Date")
    @Temporal(TemporalType.DATE)
    private Date submissionDate;

    @Column(name = "attachment_Name")
    private String upFileName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "doc_Type")
    private String docType;

    @Column(name = "delete_Status")
    private String deleteStatus;

    @Column(name = "Attachment_Type")
    private String attachmentType;

    @ManyToOne(fetch = FetchType.EAGER)       // same as lazy="false"
    @JoinColumn(name = "submittedBy")
    private SeatUserAlloted submittedBy;
}