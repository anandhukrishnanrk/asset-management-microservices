package com.cis.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.CisQueries;

@Repository
public interface CisQueriesRepository extends JpaRepository<CisQueries, Integer> {

    @Query("SELECT c FROM CisQueries c WHERE " +
            "(:projectId IS NULL OR c.projectId = :projectId) AND " +
            "(:moduleId IS NULL OR c.moduleId = :moduleId) AND " +
            "(:tabCode IS NULL OR c.tabCode = :tabCode) AND " +
            "(:linkId IS NULL OR c.linkId = :linkId) AND " +
            "c.activeStatus = 'Y' ORDER BY c.updatedOn DESC")
    List<CisQueries> findQueries(@Param("projectId") Integer projectId,
            @Param("moduleId") Integer moduleId,
            @Param("tabCode") String tabCode,
            @Param("linkId") Integer linkId);
}
