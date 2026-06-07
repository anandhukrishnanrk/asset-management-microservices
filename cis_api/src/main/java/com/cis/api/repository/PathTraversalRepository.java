package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.PathTraversal;

@Repository
public interface PathTraversalRepository extends JpaRepository<PathTraversal, Long> {

    @Query(value = "SELECT * FROM pts.path_traversal where project_Id = :projectId and delete_Status = :deleteStatus order by traversal_Date desc", nativeQuery = true)
    List<PathTraversal> findByProjectIdAndDeleteStatusOrderByTraversalDateDesc(@Param("projectId") Long projectId,
            @Param("deleteStatus") String deleteStatus);
}
