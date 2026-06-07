package com.cis.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cis.api.entity.ResourceRequest;

import java.util.Collection;

@Repository
public interface ResourceRequestRepository extends JpaRepository<ResourceRequest, Integer> {

    @Query(value = "SELECT * FROM pts.resource_request " +
                   "WHERE project_Id = :pId " +
                   "AND status != 'R' " +
                   "AND pmo_Status IS NULL " +
                   "AND (rmg_Status = 'A' OR rmg_Status = 'P') " +
                   "ORDER BY request_Id DESC", nativeQuery = true)
    Collection<ResourceRequest> findAllByProjectId(@Param("pId") Integer pId);
}