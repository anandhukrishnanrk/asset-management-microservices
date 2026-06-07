package com.cis.api.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sdd_entries", schema ="pts", catalog = "pts")
public class SDDEntries {

    @Id
    @Column(name = "id")
    private Integer id;   // assigned manually (no @GeneratedValue)

    @Column(name = "module_Id")
    private Integer moduleId;

    @Column(name = "activity")
    private String activity;

    @Column(name = "entry_No")
    private String entryNo;

    @Column(name = "description")
    private String description;

    // Constructors
    public SDDEntries() {
    }

    public SDDEntries(Integer id, Integer moduleId, String activity, String entryNo, String description) {
        this.id = id;
        this.moduleId = moduleId;
        this.activity = activity;
        this.entryNo = entryNo;
        this.description = description;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getEntryNo() {
        return entryNo;
    }

    public void setEntryNo(String entryNo) {
        this.entryNo = entryNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
