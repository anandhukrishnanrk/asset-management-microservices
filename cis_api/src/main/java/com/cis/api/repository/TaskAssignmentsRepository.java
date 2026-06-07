package com.cis.api.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.TaskAssignments;


@Repository
public interface TaskAssignmentsRepository extends JpaRepository<TaskAssignments, Integer> {

    // Fetch ALL users for project (date range filter)
    @Query("""
        SELECT t FROM TaskAssignments t
        WHERE t.project.projectId = :projectId
          AND (
                (t.startDate BETWEEN :startDate AND :endDate)
                OR
                (t.endDate BETWEEN :startDate AND :endDate)
                OR
                (t.startDate <= :startDate AND t.endDate >= :endDate)
              )
        """)
    Page<TaskAssignments> findByProjectAndDateRange(
            @Param("projectId") Integer projectId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            Pageable pageable
    );

    // Fetch SPECIFIC USER for project (date range filter)
    @Query("""
        SELECT t FROM TaskAssignments t
        WHERE t.project.projectId = :projectId
          AND t.user.id = :userId
          AND (
                (t.startDate BETWEEN :startDate AND :endDate)
                OR
                (t.endDate BETWEEN :startDate AND :endDate)
                OR
                (t.startDate <= :startDate AND t.endDate >= :endDate)
              )
        """)
    Page<TaskAssignments> findByProjectUserAndDateRange(
            @Param("projectId") Integer projectId,
            @Param("userId") Long userId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            Pageable pageable
    );
}