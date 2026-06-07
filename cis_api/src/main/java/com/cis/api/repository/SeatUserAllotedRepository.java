package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.SeatUserAlloted;

@Repository
public interface SeatUserAllotedRepository extends JpaRepository<SeatUserAlloted, Integer> {

    @Query("""
        SELECT DISTINCT sua
        FROM SeatUserAlloted sua
        WHERE sua.user.id IN (
            SELECT trtd.task.user.id
            FROM TestReportTaskDetails trtd
            WHERE trtd.testResultId IN (
                SELECT tr.id
                FROM TestReportResult tr
                WHERE tr.projectId = :projectId
            )
        )
    """)
    List<SeatUserAlloted> findUsersAssignedForUnitTests(@Param("projectId") Integer projectId);
    
    
    
    @Query("""
            select s 
            from SeatUserAlloted s
            where s.activeStatus = 'Y' 
              and s.seat.activeStatus = 'Y' 
              and s.user.activeStatus = 'Y'
            order by s.user.userName asc
            """)
     List<SeatUserAlloted> findAllActiveSeatUserAllotments();
 }




