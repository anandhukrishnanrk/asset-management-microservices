package com.cis.api.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cis.api.entity.TestPlanUploads;

@Repository
public interface TestPlanUploadsRepository extends JpaRepository<TestPlanUploads, Integer> {

    // METHOD 1 — Use @Param on ALL parameters
    Collection<TestPlanUploads> findAllByProjectDetailsProjectIdAndDeleteStatus(
            @Param("projectId") Integer projectId,
            @Param("deleteStatus") String deleteStatus
    );

    // METHOD 2 (unchanged)
    @Query("SELECT t FROM TestPlanUploads t WHERE t.projectDetails.projectId = :projectId AND t.deleteStatus = :status")
    Collection<TestPlanUploads> findAllByProjectIdAndStatus(
            @Param("projectId") Integer projectId,
            @Param("status") String status
    );
}