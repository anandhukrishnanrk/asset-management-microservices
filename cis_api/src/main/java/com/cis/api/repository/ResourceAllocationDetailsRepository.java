package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.ResourceAllocationDetails;
import com.cis.api.entity.SDDEntries;

@Repository
public interface ResourceAllocationDetailsRepository
        extends JpaRepository<ResourceAllocationDetails, Integer> {

    @Query("""
        SELECT rad 
        FROM ResourceAllocationDetails rad
        WHERE rad.resourceAllocation.resourceRequest.projectId = :projectId
        ORDER BY rad.resourceAllocation.seatUserAlloted.allotId ASC
    """)
    List<ResourceAllocationDetails> findByProjectId(@Param("projectId") Integer projectId);

    
    @Query("SELECT rad FROM ResourceAllocationDetails rad " +
            "WHERE rad.resourceAllocation.resourceRequest.resourceProject.projectDetails.projectId = :projectId " +
            "ORDER BY rad.resourceAllocation.seatUserAlloted.allotId ASC")
     List<ResourceAllocationDetails> findActiveResourceByProjectId(@Param("projectId") Integer projectId);
    
    // ── NEW: monthly count grouped by year/month ───────────────────────────
    /**
     * Returns one row per (year, month) that overlaps the allocation period.
     * A resource allocated from Nov to Dec counts in BOTH Nov and Dec.
     *
     * We expand the date range into months using a numbers trick via JPQL.
     * Because JPQL cannot generate sequences, we do this in a native query.
     */
    @Query(value = """
        SELECT
            YEAR(m.month_start)  AS year,
            MONTH(m.month_start) AS month,
            COUNT(DISTINCT rad.allocation_Id) AS cnt
        FROM  pts.resource_allocation_details rad
        JOIN  pts.resource_allocation ra
            ON ra.allot_id = rad.allocation_Id
        JOIN pts.resource_request rr
            ON rr.request_id = ra.request_id
        JOIN (
            /* generate every month from earliest startDate to today */
            SELECT DATE_FORMAT(
                       DATE_ADD('2000-01-01', INTERVAL (a.a + b.a*10 + c.a*100) MONTH),
                       '%Y-%m-01'
                   ) AS month_start
            FROM
                (SELECT 0 a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
                 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
                (SELECT 0 a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4
                 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b,
                (SELECT 0 a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3) c
            WHERE DATE_ADD('2000-01-01', INTERVAL (a.a + b.a*10 + c.a*100) MONTH)
                      <= CURDATE()
        ) m
            ON m.month_start BETWEEN DATE_FORMAT(rad.allocation_Start_Date, '%Y-%m-01')
                                 AND DATE_FORMAT(rad.allocation_End_Date,   '%Y-%m-01')
        WHERE rr.project_id = :projectId
          AND (:year IS NULL OR YEAR(m.month_start) = :year)
        GROUP BY YEAR(m.month_start), MONTH(m.month_start)
        ORDER BY YEAR(m.month_start), MONTH(m.month_start)
    """, nativeQuery = true)
    List<Object[]> findMonthlyResourceCountByProjectId(
            @Param("projectId") Integer projectId,
            @Param("year") Integer year);   // pass null to get all years

    // ── NEW: detail list for one specific month ────────────────────────────
    @Query("""
        SELECT rad
        FROM ResourceAllocationDetails rad
        WHERE rad.resourceAllocation.resourceRequest.projectId = :projectId
          AND YEAR(rad.startDate)  <= :year
          AND YEAR(rad.endDate)    >= :year
          AND MONTH(rad.startDate) <= :month
          AND MONTH(rad.endDate)   >= :month
        ORDER BY rad.resourceAllocation.seatUserAlloted.allotId ASC
    """)
    List<ResourceAllocationDetails> findByProjectIdAndMonth(
            @Param("projectId") Integer projectId,
            @Param("year")      int year,
            @Param("month")     int month);
    
   
}
