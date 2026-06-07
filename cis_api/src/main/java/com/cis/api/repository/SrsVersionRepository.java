package com.cis.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.SrsVersion;

@Repository
public interface SrsVersionRepository extends JpaRepository<SrsVersion, Integer> {
    Collection<SrsVersion> findAllByProjectIdAndSrsActiveStatus(Integer projectId, String srsActiveStatus);

    Collection<SrsVersion> findAllByProjectId(Integer projectId);

    @Query("SELECT s FROM SrsVersion s WHERE s.projectId = :projectId ORDER BY s.srsVersionId DESC")
    SrsVersion getLatestSrsVersion(@Param("projectId") Long projectId);

}
