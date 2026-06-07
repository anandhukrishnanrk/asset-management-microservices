package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.CmmiProjectResources;

@Repository
public interface CmmiProjectResourcesRepository 
        extends JpaRepository<CmmiProjectResources, Integer> {

    @Query("SELECT cpr FROM CmmiProjectResources cpr " +
           "WHERE cpr.resourceAllocation.resourceRequest.resourceProject.projectDetails.projectId = :projectId")
    List<CmmiProjectResources> findByProjectId(@Param("projectId") Integer projectId);
}