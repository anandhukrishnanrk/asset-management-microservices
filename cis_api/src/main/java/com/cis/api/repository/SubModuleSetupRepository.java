package com.cis.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cis.api.entity.SubModuleSetup;

public interface SubModuleSetupRepository extends JpaRepository<SubModuleSetup, Integer>{
	
	@Query("""
		    SELECT sm 
		    FROM SubModuleSetup sm 
		    WHERE sm.module.moduleId = :moduleId 
		    AND sm.module.deleteStatus = 'N'
		""")
		List<SubModuleSetup> findByModuleId(Integer moduleId);

}
