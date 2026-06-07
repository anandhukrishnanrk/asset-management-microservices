package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.PMPResourceAllocation;
import com.cis.api.entity.PmpReusables;

public interface PMPResourceAllocationRepository 
	
	
	
    extends JpaRepository<PMPResourceAllocation, Integer> {

@Query("""
       select b
       from PMPResourceAllocation b
       where b.pmp.id = :pmpId
       """)
List<PMPResourceAllocation> findByPmpId(
       @Param("pmpId") Integer pmpId);

}

