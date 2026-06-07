package com.cis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.TestPlanUploads;
import com.cis.api.entity.TestReportHistory;

public interface TestReportHistoryRepository extends JpaRepository<TestReportHistory, Integer>{

	@Query("SELECT h.testcasedetails.testCaseNo " +
	           "FROM TestReportHistory h " +
	           "WHERE h.tHisId = :historyId")
	    String findTestCaseNo(@Param("historyId") Integer historyId);

}
