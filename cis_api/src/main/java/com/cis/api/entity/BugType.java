package com.cis.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bug_type", schema = "pts" , catalog = "pts")
public class BugType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer bugTypeId;

    @Column(name = "bug_Type")
    private String bugType;

    @Column(name = "delete_Status")
    private String deleteStatus;

    // Constructors
    public BugType() {
    }

    public BugType(String bugType, String deleteStatus) {
        this.bugType = bugType;
        this.deleteStatus = deleteStatus;
    }

    // Getters and Setters

    public Integer getBugTypeId() {
        return bugTypeId;
    }

    public void setBugTypeId(Integer bugTypeId) {
        this.bugTypeId = bugTypeId;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
}
