package com.cis.api.repository;

import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.cis.api.entity.SDDEntries;

@Repository
	public interface SDDEntriesRepository extends JpaRepository<SDDEntries, Integer> {

	    @Query("SELECT s FROM SDDEntries s WHERE s.id = :id")
	    SDDEntries getEntry(@Param("id") Integer id);

//		Streamable<Order> getEntriesByIds(List<Integer> sddIds);
	    
	    Streamable<SDDEntries> getEntriesByIdIn(List<Integer> ids);
	}









