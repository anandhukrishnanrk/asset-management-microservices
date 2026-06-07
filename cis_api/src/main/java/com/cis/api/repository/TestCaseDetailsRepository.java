package com.cis.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.TestCaseDetails;

@Repository
public interface TestCaseDetailsRepository extends JpaRepository<TestCaseDetails, Integer> {

    @Query("SELECT t FROM TestCaseDetails t " +
           "WHERE t.srs.srsId = :srsId " )
    List<TestCaseDetails> getTestCases(@Param("srsId") Integer srsId);
    
    @Query("SELECT t FROM TestCaseDetails t WHERE t.testCaseId = :id")
    Optional<TestCaseDetails> findByTestCaseId(@Param("id") Integer id);

//	List<TestCaseDetails> getTestCasesBySrsIds(List<Integer> srsIdsWithTC);
    
//    List<TestCaseDetails> getTestCasesBySrsIdIn(List<Integer> srsIds);
    
    @Query("SELECT t FROM TestCaseDetails t WHERE t.srs.srsId IN :ids")
    List<TestCaseDetails> getTestCasesBySrsIds(@Param("ids") List<Integer> ids);

}
