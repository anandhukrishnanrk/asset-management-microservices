package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.SprintHistoryDetails;

public interface SprintHistoryDetailsRepository 
        extends JpaRepository<SprintHistoryDetails, Integer> {

    @Query("""
            FROM SprintHistoryDetails 
            WHERE sprintDetailId = :sprintId
           """)
    List<SprintHistoryDetails> getSprintHistorylistBySprintId(
            @Param("sprintId") Integer sprintId
    );
}