package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asset.asset_api.entity.AssetAllotment;
import com.asset.asset_api.entity.AssetStore;


	@Repository
	public interface AssetAllotmentRepository extends JpaRepository<AssetAllotment, Integer> {


		    @Query(value = "SELECT * FROM asset_allotment WHERE store_id = :storeId ORDER BY id DESC LIMIT 1", nativeQuery = true)
		    AssetAllotment getAssetAllotment(@Param("storeId") Integer storeId);

			AssetAllotment findByStore_StoreId(Integer storeId);

			
			@Query("""
					from AssetStore as a where a.asset.amc=:status
					""")
			List<AssetStore> getAssetStoreForAMC();

		

	}

