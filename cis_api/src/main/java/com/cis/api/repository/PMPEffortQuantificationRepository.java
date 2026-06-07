package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.PMPEffortQuantification;
import com.cis.api.entity.PMPResourceAllocation;

public interface PMPEffortQuantificationRepository 
	
	  extends JpaRepository<PMPEffortQuantification, Integer> {

	 @Query("""
	           select b
	           from PMPEffortQuantification b
	           where b.pmp.id = :pmpId
	           """)
	    List<PMPEffortQuantification> findByPmpId(@Param("pmpId") Integer pmpId);

	
	

	

}
