package com.cis.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.TestReportResult;

@Repository
public interface TestReportResultRepository extends JpaRepository<TestReportResult, Integer> {

	@Query("SELECT a FROM TestReportResult a " +
		       "WHERE a.types LIKE 'Unit' AND a.projectId = :pid " +
		       "ORDER BY a.id DESC")
		Page<TestReportResult> findAllUnitTests(@Param("pid") Integer projectId, Pageable pageable);

	
	  @Query("""
	           SELECT a FROM TestReportResult a
	           WHERE a.projectId = :pid
	             AND a.types NOT LIKE 'Unit'
	           ORDER BY a.id DESC
	           """)
	    Page<TestReportResult> findBugReportPage(
	            @Param("pid") Integer pid,
	            Pageable pageable);
	  
	


	  @Query("""
		        SELECT trr FROM TestReportResult trr
		        WHERE trr.projectId = :projectId 
		          AND trr.types LIKE 'Unit'
		          AND (:moduleId IS NULL OR trr.module.moduleId = :moduleId)
		          AND (:taskStatus IS NULL OR trr.taskStatus = :taskStatus)
		          AND (:bugTypeId IS NULL OR trr.bugType.bugTypeId = :bugTypeId)
		          AND (
		                (:fromDate IS NULL AND :toDate IS NULL)
		                OR (trr.updatedOn BETWEEN :fromDate AND :toDate)
		                OR (:fromDate IS NOT NULL AND trr.updatedOn >= :fromDate)
		                OR (:toDate IS NOT NULL AND trr.updatedOn <= :toDate)
		              )
		          AND (:testResultIds IS NULL OR trr.id IN :testResultIds)
		        ORDER BY trr.id DESC
		""")
		Page<TestReportResult> filterUnitTestResults(
		        @Param("projectId") Integer projectId,
		        @Param("moduleId") Integer moduleId,
		        @Param("taskStatus") String taskStatus,
		        @Param("bugTypeId") Integer bugTypeId,
		        @Param("fromDate") LocalDate fromDate,
		        @Param("toDate") LocalDate toDate,
		        @Param("testResultIds") List<Integer> testResultIds,
		        Pageable pageable
		);


	  @Query("""
			    SELECT trr FROM TestReportResult trr
			    WHERE trr.projectId = :projectId
			      AND trr.types <> 'Unit'
			      AND (:moduleId IS NULL OR trr.module.moduleId = :moduleId)
			      AND (:taskStatus IS NULL OR trr.taskStatus = :taskStatus)
			      AND (:versionId IS NULL OR trr.svnVersion = :versionId)
			      AND (:types IS NULL OR trr.types = :types)
			      AND (:bugTypeId IS NULL OR trr.bugType.bugTypeId = :bugTypeId)
			      AND (:testResultIds IS NULL OR trr.id IN :testResultIds)
			    ORDER BY trr.id DESC
			""")
			Page<TestReportResult> filterTestReportBugs(
			        @Param("projectId") Integer projectId,
			        @Param("moduleId") Integer moduleId,
			        @Param("taskStatus") String taskStatus,
			        @Param("versionId") Integer versionId,
			        @Param("types") String types,
			        @Param("bugTypeId") Integer bugTypeId,
			        @Param("testResultIds") List<Integer> testResultIds,
			        Pageable pageable
			);


	  @Query("SELECT t.testCaseDetails FROM TestReportHistory t WHERE t.tHisId = :id")
	    String findTestCaseNo(@Param("id") Integer id);

}


