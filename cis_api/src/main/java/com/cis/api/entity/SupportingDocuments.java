package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "supporting_documents", catalog = "pts", schema = "pts")
public class SupportingDocuments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supporting_doc_id")
	private Long supportingDocId;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cmmi_Project_Id")
	private CmmiProject cmmiProject;

	@Column(name = "submission_Date", length = 15)
	private String submissionDate;

	@Column(name = "attachment_Name", length = 200)
	private String attachmentName;

	@Lob
	@Column(name = "remarks")
	private String remarks;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submittedBy")
	private UserSetup submittedBy;

	@Column(name = "temp_Date", length = 45)
	private String tempDate;

	@Column(name = "doc_Type", length = 45)
	private String docType;

	@Column(name = "delete_Status", length = 1)
	private String deleteStatus;
}
