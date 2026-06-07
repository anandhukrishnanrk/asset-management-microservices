package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.asset_api.entity.AMCDetails;

public interface AmcDetailsRepository extends JpaRepository<AMCDetails, Integer> {
	
	@Query("""
		       from AMCDetails a where a.assetStore.storeId = :storeId
		       """)
		List<AMCDetails> getAMCListBasedOnStoreId(@Param("storeId") Integer storeId);

}
