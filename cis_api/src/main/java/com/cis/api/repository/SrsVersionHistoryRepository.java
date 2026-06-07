package com.cis.api.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.SrsVersionHistory;
import com.cis.api.vo.SrsVersionHistoryVo;

@Repository
public interface SrsVersionHistoryRepository extends JpaRepository<SrsVersionHistory, Integer> {

	int countBySrsVersionSrsVersionId(Integer srsverid);

	// Fetch list as VO
	@Query("SELECT new com.cis.api.vo.SrsVersionHistoryVo(" +
	       "s.srsVerHisId, s.srsDetails.srsNo, s.srsPoint, " +
	       "s.srsDetails.crs.crsNo, s.srsDetails.module.moduleName, s.srsDetails.srsRemarks) " +
	       "FROM SrsVersionHistory s " +
	       "WHERE s.srsVersion.srsVersionId = :srsVersionId")
	List<SrsVersionHistoryVo> findSrsVersionHistoryVosBySrsVersionId(@Param("srsVersionId") Integer srsverid);
	
	
    @Query("""
        SELECT new com.cis.api.vo.SrsVersionHistoryVo(
            s.srsVerHisId,
            s.srsDetails.srsNo,
            s.srsPoint,
            s.srsDetails.crs.crsNo,
            s.srsDetails.module.moduleName,
            s.srsDetails.srsRemarks
        )
        FROM SrsVersionHistory s
        WHERE s.srsVersion.srsVersionId = :srsVersionId
          AND s.srsDetails.task IS NULL
          AND s.status IS NULL
          AND (:moduleId = 0 OR s.srsDetails.module.moduleId = :moduleId)
        ORDER BY s.srsVerHisId DESC
    """)
    Page<SrsVersionHistoryVo> getUnassignedPointsVo(
            @Param("srsVersionId") Integer srsVersionId,
            @Param("moduleId") Long moduleId,
            Pageable pageable
    );
    
    
    @Query("""
    	    SELECT new com.cis.api.vo.SrsVersionHistoryVo(
    	        s.srsVerHisId,
    	        s.srsDetails.srsNo,
    	        s.srsPoint,
    	        s.srsDetails.crs.crsNo,
    	        s.srsDetails.module.moduleName,
    	        s.srsDetails.srsRemarks
    	    )
    	    FROM SrsVersionHistory s
    	    WHERE s.srsVersion.srsVersionId = :srsVersionId
    	      AND s.srsDetails.task IS NOT NULL
    	      AND s.status IS NULL
    	      AND (:moduleId = 0 OR s.srsDetails.module.moduleId = :moduleId)
    	    ORDER BY s.srsDetails.task.tAId DESC
    	""")
    	Page<SrsVersionHistoryVo> getAssignedPointsVo(
    	        @Param("srsVersionId") Integer srsVersionId,
    	        @Param("moduleId") Long moduleId,
    	        Pageable pageable
    	);

    

    @Query("""
    	    SELECT a
    	    FROM SrsVersionHistory a
    	    LEFT JOIN a.srsDetails sd
    	    LEFT JOIN sd.task t
    	    LEFT JOIN t.user u
    	    WHERE a.srsVersion.srsVersionId = :srsVersionId
    	      AND a.status IS NULL

    	      AND (
    	            (:assignStatus = 'UA' AND t IS NULL)
    	            OR
    	            (:assignStatus <> 'UA' AND t IS NOT NULL)
    	          )

    	      AND a.srsRefNo IN :srsNoList

    	      AND (
    	            :searchTerm IS NULL OR :searchTerm = ''
    	            OR a.srsPoint LIKE :searchTerm_1
    	            OR a.srsRefNo LIKE :searchTerm_1
    	            OR u.userName LIKE :searchTerm_1
    	          )

    	      AND (
    	            :moduleId IS NULL OR :moduleId = 0
    	            OR sd.module.moduleId = :moduleId
    	          )

    	    ORDER BY 
    	       CASE WHEN :assignStatus = 'UA' THEN a.srsVerHisId END DESC,
    	       CASE WHEN :assignStatus <> 'UA' THEN t.tAId END DESC
    	""")
    	Page<SrsVersionHistory> getSrsVersionHistoryList(
    	        @Param("srsVersionId") Integer srsVersionId,
    	        @Param("srsNoList") List<String> srsNoList,
    	        @Param("assignStatus") String assignStatus,
    	        @Param("searchTerm") String searchTerm,
    	        @Param("searchTerm_1") String searchTerm_1,
    	        @Param("moduleId") Integer moduleId,
    	        Pageable pageable
    	);
    
    
    @Query("""
    	    SELECT a
    	    FROM SrsVersionHistory a
    	    LEFT JOIN a.srsDetails sd
    	    LEFT JOIN sd.task t
    	    LEFT JOIN t.user u
    	    WHERE a.srsVersion.srsVersionId = :srsVersionId
    	      AND a.status IS NULL

    	      AND (
    	            (:assignStatus = 'UA' AND t IS NULL)
    	            OR
    	            (:assignStatus <> 'UA' AND t IS NOT NULL)
    	          )

    	      AND (
    	            :searchTerm IS NULL OR :searchTerm = ''
    	            OR a.srsPoint LIKE :searchTerm_1
    	            OR a.srsRefNo LIKE :searchTerm_1
    	            OR u.userName LIKE :searchTerm_1
    	          )

    	      AND (
    	            :moduleId IS NULL OR :moduleId = 0
    	            OR sd.module.moduleId = :moduleId
    	          )

    	    ORDER BY 
    	       CASE WHEN :assignStatus = 'UA' THEN a.srsVerHisId END DESC,
    	       CASE WHEN :assignStatus <> 'UA' THEN t.tAId END DESC
    	""")
    	Page<SrsVersionHistory> getSrsVersionHistoryListcoding(
    	        @Param("srsVersionId") Integer srsVersionId,
    	        @Param("assignStatus") String assignStatus,
    	        @Param("searchTerm") String searchTerm,
    	        @Param("searchTerm_1") String searchTerm_1,
    	        @Param("moduleId") Integer moduleId,
    	        Pageable pageable
    	);
    	
    }


    






