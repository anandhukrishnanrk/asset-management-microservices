package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.TestReportTaskDetails;

@Repository
public interface TestReportTaskDetailsRepository extends JpaRepository<TestReportTaskDetails, Integer> {

	@Query("""
		    SELECT ttd.testResultId
		    FROM TestReportTaskDetails ttd
		    WHERE ttd.task.project.projectId = :projectId
		      AND ttd.task.user.id = :userId
		""")
		List<Integer> findTestResultIdsByProjectAndUser(Integer projectId, Integer userId);

	@Query("""
		    SELECT ttd.testResultId
		    FROM TestReportTaskDetails ttd
		    WHERE ttd.task.project.projectId = :projectId
		      AND ttd.task.user.id = :userId
		""")
		List<Integer> findTestResultIds(@Param("projectId") Integer projectId,
		                                @Param("userId") Long userId);

}

