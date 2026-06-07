package com.cis.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.ModuleSetup;
import com.cis.api.entity.UserSetup;

@Repository
public interface ModuleSetupRepository extends JpaRepository<ModuleSetup, Integer> {

    // Correct path: module.project.projectId + filter deleteStatus
    List<ModuleSetup> findByProject_ProjectIdAndDeleteStatus(Long projectId, String deleteStatus);

    @Query("""
        SELECT DISTINCT m FROM ModuleSetup m
        LEFT JOIN FETCH m.crslist c
        LEFT JOIN FETCH c.srslist s
        LEFT JOIN FETCH s.testcaselist t
        WHERE m.project.projectId = :pId
          AND m.group.id = :gId
    """)
    Page<ModuleSetup> loadRtmData(
            @Param("pId") Long pId,
            @Param("gId") Integer gId,
            Pageable pageable
    );

    @Query("""
        SELECT m FROM ModuleSetup m
        WHERE m.project.projectId = :pId
          AND m.group.id = :gId
        ORDER BY m.moduleId ASC
    """)
    List<ModuleSetup> getModules(@Param("pId") Long pId,
                                 @Param("gId") Integer gId);

    @Query("""
    	    SELECT b 
    	    FROM ModuleSetup b 
    	    WHERE b.project.projectId = :projectId
    	      AND b.deleteStatus = 'N'
    	""")
	List<UserSetup> findModuleForPmp(Integer projectId);
    
    @Query("""
        SELECT b 
        FROM ModuleSetup b 
        WHERE b.project.projectId = :projectId
          AND b.group.id = :minGroupId
          AND b.deleteStatus = 'N'
    """)
    List<ModuleSetup> findModuleForProjectPmp(@Param("projectId") Long projectId,
                                              @Param("minGroupId") Integer minGroupId);

    // Corrected method — Spring Data automatically resolves this path.
    @Query("""
            SELECT b 
            FROM ModuleSetup b 
            WHERE b.project.projectId = :projectId
    """)
    List<ModuleSetup> findByProject_ProjectId(@Param("projectId") Long projectId);
}