package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asset.asset_api.entity.AssetOffice;
import com.asset.asset_api.entity.HierarchySetup;

public interface HierarchySetupRepository extends JpaRepository<HierarchySetup, Integer> {

	List<HierarchySetup> findAll();

	
	@Query(""" 
			from  HierarchySetup as hs where hs.category in ('G')
			""")
	List<HierarchySetup> getOfficeListforAssets();

}
