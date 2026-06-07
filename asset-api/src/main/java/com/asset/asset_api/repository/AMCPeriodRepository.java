package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asset.asset_api.entity.AMCPeriod;

public interface AMCPeriodRepository extends JpaRepository<AMCPeriod, Integer>{

	
	@Query("""
			from AMCPeroid as a
			""")
	List<AMCPeriod> getAssetAMCPeriod();

}
