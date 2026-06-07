package com.cis.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "cis_project_role_privileges", catalog = "pts", schema = "pts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CisProjectRolePrivileges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    
    @Column(name = "role_name", nullable = false)
    private String roleName;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "tab_name", nullable = false)
    private String tabName;
    
    @Column(name = "subtab_name")
    private String subtabName;
    
    @Column(name = "can_read", nullable = false)
    private Boolean canRead = false;
    
    @Column(name = "can_write", nullable = false)
    private Boolean canWrite = false;
    
    @Column(name = "can_delete", nullable = false)
    private Boolean canDelete = false;
    
    @Column(name = "can_approve", nullable = false)
    private Boolean canApprove = false;
    
    @Column(name = "can_verify", nullable = false)
    private Boolean canVerify = false;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}
