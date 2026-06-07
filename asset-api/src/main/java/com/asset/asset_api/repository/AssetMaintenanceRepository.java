package com.asset.asset_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asset.asset_api.entity.AssetMaintenance;

public interface AssetMaintenanceRepository extends JpaRepository<AssetMaintenance, Integer> {

    @Query("""
    from AssetMaintenance a 
    where a.storeId = :storeId 
    and a.historyStatus = 'N'
    """)
    AssetMaintenance getAssetMaintanaceBasedonStoreId(@Param("storeId") Integer storeId);
    
    @Query("""
    	    from AssetMaintenance a 
    	    where a.storeId = :storeId 
    	    and a.historyStatus = 'N'
    	    """)

	List<AssetMaintenance> getAssetMaintanceBasedOnHistory(Integer storeId);

}