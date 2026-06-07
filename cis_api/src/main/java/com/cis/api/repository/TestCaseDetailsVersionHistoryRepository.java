package com.cis.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.TestCaseDetailsVersionHistory;


@Repository
public interface TestCaseDetailsVersionHistoryRepository 
        extends JpaRepository<TestCaseDetailsVersionHistory, Integer> {

    @Query("SELECT th.testCase.testCaseId FROM TestCaseDetailsVersionHistory th " +
           "WHERE th.version.versionId = :tstCaseVerId")
    List<Integer> findTestCaseIds(@Param("tstCaseVerId") Integer tstCaseVerId);
}