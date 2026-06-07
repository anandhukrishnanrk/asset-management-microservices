package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.asset.asset_api.entity.AssetFlow;

public interface AssetFlowRepository extends JpaRepository<AssetFlow, Integer>{

	
	@Query("""
			from AssetFlow as a where a.store.storeId=:storeId
			""")

	List<AssetFlow> findAllBystoreId(Integer storeId);

}
