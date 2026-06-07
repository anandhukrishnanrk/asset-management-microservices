package com.cis.api.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.CmmiProject;


@Repository
public interface CmmiProjectRepository extends JpaRepository<CmmiProject, Long> {

    @Query("SELECT c FROM CmmiProject c WHERE c.id = :cmmiProjectId")
    CmmiProject findProject(@Param("cmmiProjectId") Long cmmiProjectId);
    
	@Query("SELECT c.id FROM CmmiProject c WHERE c.project.projectId = :projectId")
	Long findIdByProjectId(@Param("projectId") Long projectId);

}


