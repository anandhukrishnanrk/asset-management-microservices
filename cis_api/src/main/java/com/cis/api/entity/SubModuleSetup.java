package com.cis.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "submodulesetup", schema = "pts", catalog = "pts")
public class SubModuleSetup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_m_Id")
    private Integer subMId;

    @Column(name = "submodName", length = 45)
    private String submodName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moduleId", nullable = false)
    private ModuleSetup module;

    // --- Getters & Setters ---

    public Integer getSubMId() {
        return subMId;
    }

    public void setSubMId(Integer subMId) {
        this.subMId = subMId;
    }

    public String getSubmodName() {
        return submodName;
    }

    public void setSubmodName(String submodName) {
        this.submodName = submodName;
    }

    public ModuleSetup getModule() {
        return module;
    }

    public void setModule(ModuleSetup module) {
        this.module = module;
    }
}

