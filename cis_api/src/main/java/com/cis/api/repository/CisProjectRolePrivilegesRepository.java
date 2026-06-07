package com.cis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.CisProjectRolePrivileges;

import java.util.List;
import java.util.Optional;

@Repository
public interface CisProjectRolePrivilegesRepository extends JpaRepository<CisProjectRolePrivileges, Long> {
    Optional<CisProjectRolePrivileges> findByProjectIdAndRoleNameAndTabNameAndSubtabName(Long projectId,
            String roleName, String tabName, String subtabName);

    // Using IS NULL for subtab since it can be null
    Optional<CisProjectRolePrivileges> findByProjectIdAndRoleNameAndTabNameAndSubtabNameIsNull(Long projectId,
            String roleName, String tabName);

    List<CisProjectRolePrivileges> findByProjectId(Long projectId);
}
