package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.WBSSetup;

public interface WBSSetupRepository extends JpaRepository<WBSSetup, Long> {

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.activeStatus = 'Y' ORDER BY w.level, w.wbsCode")
    List<WBSSetup> findAllActiveByProjectId(@Param("projectId") Long projectId);

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.parentId = :parentId AND w.activeStatus = 'Y' ORDER BY w.sortOrder, w.wbsCode")
    List<WBSSetup> findByProjectIdAndParentId(@Param("projectId") Long projectId, @Param("parentId") Long parentId);

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.parentId = :parentId ORDER BY w.sortOrder, w.wbsCode")
    List<WBSSetup> findAllByProjectIdAndParentIdIncludingInactive(@Param("projectId") Long projectId,
            @Param("parentId") Long parentId);

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.parentId = :parentId ORDER BY w.sortOrder, w.wbsCode")
    List<WBSSetup> findAllByProjectIdAndParentId(@Param("projectId") Long projectId, @Param("parentId") Long parentId);

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.parentId IS NULL AND w.activeStatus = 'Y' ORDER BY w.sortOrder, w.wbsCode")
    List<WBSSetup> findRootWBSByProjectId(@Param("projectId") Long projectId);

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.parentId IS NULL ORDER BY w.sortOrder, w.wbsCode")
    List<WBSSetup> findRootWBSByProjectIdIncludingInactive(@Param("projectId") Long projectId);

    @Query(value = "SELECT COUNT(w) FROM WBSSetup w WHERE w.parentId = :parentId")
    Long countChildren(@Param("parentId") Long parentId);

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.isBillable = 'Y' AND w.activeStatus = 'Y' AND (w.level = 1 OR w.level = 2) ORDER BY w.wbsCode")
    List<WBSSetup> findBillableActivitiesByProjectId(@Param("projectId") Long projectId);

    @Query(value = "FROM WBSSetup w WHERE w.wbsCode LIKE %:search% OR w.description LIKE %:search% AND w.activeStatus = 'Y'")
    List<WBSSetup> searchWBS(@Param("search") String search);

    @Query(value = "FROM WBSSetup w WHERE w.project.projectId = :projectId AND w.wbsCode = :wbsCode")
    List<WBSSetup> findByProjectIdAndWbsCode(@Param("projectId") Long projectId, @Param("wbsCode") String wbsCode);
}
