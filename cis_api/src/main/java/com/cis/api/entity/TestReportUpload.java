package com.cis.api.entity;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "test_report_upload", schema = "pts", catalog = "pts")
public class TestReportUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // native → AUTO
    @Column(name = "upload_Id")
    private Integer uploadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uploaded_By")
    private SeatUserAlloted uploadedBy;

    @Column(name = "file_Name", length = 45)
    private String fileName;

    @Column(name = "remarks", length = 150)
    private String remarks;

    @Column(name = "active_Status", length = 2)
    private String activeStatus;

    @Column(name = "testing_Type", length = 2)
    private String testingType;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "project_Id")
    private Integer projectId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_Id")
    private ProjectGroup group;

    // Default Constructor
    public TestReportUpload() {
    }

    // Getters and Setters

    public Integer getUploadId() {
        return uploadId;
    }

    public void setUploadId(Integer uploadId) {
        this.uploadId = uploadId;
    }

    public SeatUserAlloted getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(SeatUserAlloted uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getTestingType() {
        return testingType;
    }

    public void setTestingType(String testingType) {
        this.testingType = testingType;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ProjectGroup getGroup() {
        return group;
    }

    public void setGroup(ProjectGroup group) {
        this.group = group;
    }
}
