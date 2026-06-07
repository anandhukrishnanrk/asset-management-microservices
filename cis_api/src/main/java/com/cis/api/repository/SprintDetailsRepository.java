package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cis.api.entity.SprintDetails;

	public interface SprintDetailsRepository extends JpaRepository<SprintDetails, Integer> {

	    @Query("SELECT sd FROM SprintDetails sd WHERE sd.sprintProjectId = :projectId")
	    List<SprintDetails> findByProjectId(@Param("projectId") Integer projectId);
	}


